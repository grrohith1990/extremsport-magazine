import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArticleDetailComponent } from './article-detail.component';
import { ArticleService } from '@core/services/article.service';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('ArticleDetailComponent', () => {
  let component: ArticleDetailComponent;
  let fixture: ComponentFixture<ArticleDetailComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;

  const mockArticle = {
    id: '1',
    title: 'Test Article',
    subtitle: 'Test Subtitle',
    content: '<p>Content</p>',
    summary: 'Summary',
    category: 'Klettern',
    authorName: 'Marco Berger',
    publishedAt: '2026-01-01',
    coverImageUrl: 'https://example.com/img.jpg',
    accessType: 'PUBLIC',
    tags: ['Klettern', 'Alpen']
  };

  beforeEach(async () => {
    articleServiceMock = jasmine.createSpyObj('ArticleService', ['getArticleById']);
    articleServiceMock.getArticleById.and.returnValue(of(mockArticle as any));

    await TestBed.configureTestingModule({
      imports: [ArticleDetailComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock },
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => '1' } } } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ArticleDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load article on init', () => {
    expect(articleServiceMock.getArticleById).toHaveBeenCalledWith('1');
    expect(component.article).toEqual(mockArticle as any);
  });

  it('should not call service if no id in route', () => {
    TestBed.resetTestingModule();
    TestBed.configureTestingModule({
      imports: [ArticleDetailComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock },
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => null } } } }
      ]
    }).compileComponents();

    const fix = TestBed.createComponent(ArticleDetailComponent);
    const comp = fix.componentInstance;
    articleServiceMock.getArticleById.calls.reset();
    fix.detectChanges();

    expect(articleServiceMock.getArticleById).not.toHaveBeenCalled();
    expect(comp.article).toBeNull();
  });

  it('should set fallback image on image error', () => {
    const img = document.createElement('img');
    const event = { target: img } as unknown as Event;
    component.onImageError(event);

    expect(img.src).toContain('placehold.co');
  });
});

