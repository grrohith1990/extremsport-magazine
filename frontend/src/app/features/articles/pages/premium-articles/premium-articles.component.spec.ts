import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PremiumArticlesComponent } from './premium-articles.component';
import { ArticleService } from '../../../../core/services/article.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('PremiumArticlesComponent', () => {
  let component: PremiumArticlesComponent;
  let fixture: ComponentFixture<PremiumArticlesComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;

  const mockArticles = [
    { id: '1', title: 'Premium Article', summary: 'Premium content', category: 'Kajak', coverImageUrl: '', accessType: 'PREMIUM', tags: [] },
  ];

  beforeEach(async () => {
    articleServiceMock = jasmine.createSpyObj('ArticleService', ['getPremiumArticles']);
    articleServiceMock.getPremiumArticles.and.returnValue(of(mockArticles as any));

    await TestBed.configureTestingModule({
      imports: [PremiumArticlesComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(PremiumArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load premium articles on init', () => {
    expect(articleServiceMock.getPremiumArticles).toHaveBeenCalled();
    expect(component.articles.length).toBe(1);
    expect(component.articles[0].title).toBe('Premium Article');
  });
});

