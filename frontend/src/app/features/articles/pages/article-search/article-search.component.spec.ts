import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArticleSearchComponent } from './article-search.component';
import { ArticleService } from '@core/services/article.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('ArticleSearchComponent', () => {
  let component: ArticleSearchComponent;
  let fixture: ComponentFixture<ArticleSearchComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;

  const mockArticles = [
    { id: '1', title: 'Article 1', summary: 'Summary', category: 'Ski', authorName: 'Author', accessType: 'PUBLIC', tags: ['tag1'] },
    { id: '2', title: 'Article 2', summary: 'Summary', category: 'Surf', authorName: 'Author', accessType: 'PUBLIC', tags: ['tag2'] },
  ];

  beforeEach(async () => {
    articleServiceMock = jasmine.createSpyObj('ArticleService', ['getPublicArticles', 'searchArticles']);
    articleServiceMock.getPublicArticles.and.returnValue(of(mockArticles as any));
    articleServiceMock.searchArticles.and.returnValue(of([mockArticles[0]] as any));

    await TestBed.configureTestingModule({
      imports: [ArticleSearchComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ArticleSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load public articles on init', () => {
    expect(articleServiceMock.getPublicArticles).toHaveBeenCalledWith(0);
    expect(component.displayedArticles.length).toBe(2);
  });

  it('should search articles when query is provided', () => {
    component.query = 'Ski';
    component.search();

    expect(articleServiceMock.searchArticles).toHaveBeenCalledWith('Ski');
    expect(component.displayedArticles.length).toBe(1);
    expect(component.searched).toBeTrue();
    expect(component.hasMore).toBeFalse();
  });

  it('should reset to all articles when query is empty', () => {
    component.query = '';
    component.search();

    expect(component.displayedArticles).toEqual(component.allArticles);
    expect(component.searched).toBeFalse();
  });

  it('should reset on input change when query is cleared', () => {
    component.query = '';
    component.onInputChange();

    expect(component.displayedArticles).toEqual(component.allArticles);
    expect(component.searched).toBeFalse();
    expect(component.hasMore).toBeTrue();
  });

  it('should load more articles on loadMore', () => {
    component.loadMore();

    expect(articleServiceMock.getPublicArticles).toHaveBeenCalledWith(1);
  });

  it('should set fallback image on error', () => {
    const img = document.createElement('img');
    const event = { target: img } as unknown as Event;
    component.onImageError(event);

    expect(img.src).toContain('placehold.co');
  });
});

