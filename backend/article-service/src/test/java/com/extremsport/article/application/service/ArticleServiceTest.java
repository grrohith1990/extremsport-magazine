package com.extremsport.article.application.service;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.in.ArticleUseCase.CreateArticleCommand;
import com.extremsport.article.domain.port.in.ArticleUseCase.UpdateArticleCommand;
import com.extremsport.article.domain.port.out.ArticleEventPublisher;
import com.extremsport.article.domain.port.out.ArticleRepository;
import com.extremsport.article.domain.port.out.CmsPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CmsPort cmsPort;

    @Mock
    private ArticleEventPublisher eventPublisher;

    @InjectMocks
    private ArticleService articleService;

    private UUID articleId;
    private UUID authorId;
    private Article existingArticle;

    @BeforeEach
    void setUp() {
        articleId = UUID.randomUUID();
        authorId = UUID.randomUUID();
        existingArticle = Article.builder()
                .id(articleId)
                .title("Existing Article")
                .subtitle("Subtitle")
                .content("Content")
                .summary("Summary")
                .authorId(authorId)
                .status(Article.ArticleStatus.DRAFT)
                .accessType(Article.AccessType.PUBLIC)
                .tags(List.of("skiing"))
                .category("Winter Sports")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .archived(false)
                .build();
    }

    @Test
    void createArticle_shouldSaveAndPublishEvent() {
        CreateArticleCommand command = new CreateArticleCommand(
                "New Article", "Subtitle", "Content", "Summary",
                authorId, Article.AccessType.PUBLIC,
                List.of("surfing", "extreme"), "Water Sports",
                "https://example.com/cover.jpg"
        );

        when(articleRepository.save(any(Article.class))).thenAnswer(inv -> inv.getArgument(0));
        when(cmsPort.isAvailable()).thenReturn(false);

        Article result = articleService.createArticle(command);

        assertThat(result.getTitle()).isEqualTo("New Article");
        assertThat(result.getAuthorId()).isEqualTo(authorId);
        assertThat(result.getStatus()).isEqualTo(Article.ArticleStatus.DRAFT);
        assertThat(result.getId()).isNotNull();

        verify(articleRepository).save(any(Article.class));
        verify(eventPublisher).publishArticleCreated(any(Article.class));
        verify(cmsPort, never()).syncArticleToCms(any());
    }

    @Test
    void createArticle_shouldSyncToCmsWhenAvailable() {
        CreateArticleCommand command = new CreateArticleCommand(
                "New Article", "Subtitle", "Content", "Summary",
                authorId, Article.AccessType.PREMIUM,
                List.of("climbing"), "Mountain Sports", null
        );

        when(articleRepository.save(any(Article.class))).thenAnswer(inv -> inv.getArgument(0));
        when(cmsPort.isAvailable()).thenReturn(true);

        articleService.createArticle(command);

        verify(cmsPort).syncArticleToCms(any(Article.class));
    }

    @Test
    void updateArticle_shouldUpdateFieldsAndSave() {
        UpdateArticleCommand command = new UpdateArticleCommand(
                "Updated Title", "Updated Subtitle", "Updated Content",
                "Updated Summary", Article.AccessType.PREMIUM,
                List.of("updated-tag"), "New Category", "https://example.com/new-cover.jpg"
        );

        when(articleRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));
        when(articleRepository.save(any(Article.class))).thenAnswer(inv -> inv.getArgument(0));

        Article result = articleService.updateArticle(articleId, command);

        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getSubtitle()).isEqualTo("Updated Subtitle");
        assertThat(result.getContent()).isEqualTo("Updated Content");
        assertThat(result.getAccessType()).isEqualTo(Article.AccessType.PREMIUM);
        assertThat(result.getTags()).containsExactly("updated-tag");
        assertThat(result.getUpdatedAt()).isAfterOrEqualTo(existingArticle.getCreatedAt());

        verify(articleRepository).save(existingArticle);
    }

    @Test
    void updateArticle_shouldThrowWhenArticleNotFound() {
        UUID unknownId = UUID.randomUUID();
        UpdateArticleCommand command = new UpdateArticleCommand(
                "Title", null, "Content", "Summary",
                Article.AccessType.PUBLIC, List.of(), "Category", null
        );

        when(articleRepository.findById(unknownId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.updateArticle(unknownId, command))
                .isInstanceOf(ArticleNotFoundException.class)
                .hasMessageContaining(unknownId.toString());
    }

    @Test
    void publishArticle_shouldPublishAndSaveAndEmitEvent() {
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));
        when(articleRepository.save(any(Article.class))).thenAnswer(inv -> inv.getArgument(0));

        articleService.publishArticle(articleId);

        assertThat(existingArticle.getStatus()).isEqualTo(Article.ArticleStatus.PUBLISHED);
        assertThat(existingArticle.getPublishedAt()).isNotNull();

        verify(articleRepository).save(existingArticle);
        verify(eventPublisher).publishArticlePublished(existingArticle);
    }

    @Test
    void publishArticle_shouldThrowWhenArticleNotFound() {
        UUID unknownId = UUID.randomUUID();
        when(articleRepository.findById(unknownId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.publishArticle(unknownId))
                .isInstanceOf(ArticleNotFoundException.class);
    }

    @Test
    void archiveArticle_shouldArchiveAndSaveAndEmitEvent() {
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));
        when(articleRepository.save(any(Article.class))).thenAnswer(inv -> inv.getArgument(0));

        articleService.archiveArticle(articleId);

        assertThat(existingArticle.getStatus()).isEqualTo(Article.ArticleStatus.ARCHIVED);
        assertThat(existingArticle.isArchived()).isTrue();

        verify(articleRepository).save(existingArticle);
        verify(eventPublisher).publishArticleArchived(existingArticle);
    }

    @Test
    void archiveArticle_shouldThrowWhenArticleNotFound() {
        UUID unknownId = UUID.randomUUID();
        when(articleRepository.findById(unknownId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.archiveArticle(unknownId))
                .isInstanceOf(ArticleNotFoundException.class);
    }

    @Test
    void getArticleById_shouldReturnArticleWhenExists() {
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));

        Optional<Article> result = articleService.getArticleById(articleId);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(articleId);
    }

    @Test
    void getArticleById_shouldReturnEmptyWhenNotExists() {
        UUID unknownId = UUID.randomUUID();
        when(articleRepository.findById(unknownId)).thenReturn(Optional.empty());

        Optional<Article> result = articleService.getArticleById(unknownId);

        assertThat(result).isEmpty();
    }

    @Test
    void getPublicArticles_shouldDelegateToRepository() {
        List<Article> articles = List.of(existingArticle);
        when(articleRepository.findByAccessType(Article.AccessType.PUBLIC, 0, 10)).thenReturn(articles);

        List<Article> result = articleService.getPublicArticles(0, 10);

        assertThat(result).hasSize(1);
        verify(articleRepository).findByAccessType(Article.AccessType.PUBLIC, 0, 10);
    }

    @Test
    void getPremiumArticles_shouldDelegateToRepository() {
        when(articleRepository.findByAccessType(Article.AccessType.PREMIUM, 0, 20)).thenReturn(List.of());

        List<Article> result = articleService.getPremiumArticles(0, 20);

        assertThat(result).isEmpty();
        verify(articleRepository).findByAccessType(Article.AccessType.PREMIUM, 0, 20);
    }

    @Test
    void searchArticles_shouldDelegateToRepository() {
        when(articleRepository.search("surfing", 0, 10)).thenReturn(List.of(existingArticle));

        List<Article> result = articleService.searchArticles("surfing", 0, 10);

        assertThat(result).hasSize(1);
        verify(articleRepository).search("surfing", 0, 10);
    }

    @Test
    void getArticlesByAuthor_shouldDelegateToRepository() {
        when(articleRepository.findByAuthorId(authorId)).thenReturn(List.of(existingArticle));

        List<Article> result = articleService.getArticlesByAuthor(authorId);

        assertThat(result).hasSize(1);
        verify(articleRepository).findByAuthorId(authorId);
    }
}

