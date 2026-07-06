import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArticleArchiveComponent } from './article-archive.component';
import { ArticleService } from '@core/services/article.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('ArticleArchiveComponent', () => {
  let component: ArticleArchiveComponent;
  let fixture: ComponentFixture<ArticleArchiveComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;

  const mockArticles = [
    { id: '1', title: 'Archived Article', publishedAt: '2025-01-01', category: 'Klettern' },
  ];

  beforeEach(async () => {
    articleServiceMock = jasmine.createSpyObj('ArticleService', ['getArchivedArticles']);
    articleServiceMock.getArchivedArticles.and.returnValue(of(mockArticles as any));

    await TestBed.configureTestingModule({
      imports: [ArticleArchiveComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ArticleArchiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load archived articles on init', () => {
    expect(articleServiceMock.getArchivedArticles).toHaveBeenCalled();
    expect(component.articles.length).toBe(1);
    expect(component.articles[0].title).toBe('Archived Article');
  });
});

