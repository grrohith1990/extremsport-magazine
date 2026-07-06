import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArticleListComponent } from './article-list.component';
import { ArticleService } from '@core/services/article.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('ArticleListComponent', () => {
  let component: ArticleListComponent;
  let fixture: ComponentFixture<ArticleListComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;

  const mockArticles = [
    { id: '1', title: 'Test Article 1', summary: 'Summary 1', category: 'Ski', authorName: 'Author', publishedAt: '2026-01-01', coverImageUrl: '', accessType: 'PUBLIC', tags: [] },
    { id: '2', title: 'Test Article 2', summary: 'Summary 2', category: 'Surf', authorName: 'Author', publishedAt: '2026-01-02', coverImageUrl: '', accessType: 'PUBLIC', tags: [] },
  ];

  beforeEach(async () => {
    articleServiceMock = jasmine.createSpyObj('ArticleService', ['getPublicArticles']);
    articleServiceMock.getPublicArticles.and.returnValue(of(mockArticles as any));

    await TestBed.configureTestingModule({
      imports: [ArticleListComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ArticleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load articles on init', () => {
    expect(articleServiceMock.getPublicArticles).toHaveBeenCalledWith(0, 6);
    expect(component.articles.length).toBe(2);
  });

  it('should set hasMore to false when fewer articles than pageSize are returned', () => {
    expect(component.hasMore).toBeFalse();
  });

  it('should set hasMore to true when articles equal pageSize', () => {
    const sixArticles = Array(6).fill(mockArticles[0]);
    articleServiceMock.getPublicArticles.and.returnValue(of(sixArticles as any));

    component.articles = [];
    component['page'] = 0;
    component['loadArticles']();

    expect(component.hasMore).toBeTrue();
  });

  it('should increment page and load more articles on loadMore', () => {
    component.loadMore();

    expect(articleServiceMock.getPublicArticles).toHaveBeenCalledWith(1, 6);
    expect(component.articles.length).toBe(4);
  });

  it('should set fallback image on image error', () => {
    const img = document.createElement('img');
    const event = { target: img } as unknown as Event;
    component.onImageError(event);

    expect(img.src).toContain('placehold.co');
  });
});

