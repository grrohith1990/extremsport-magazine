import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthorDashboardComponent } from './author-dashboard.component';
import { ArticleService } from '../../../../core/services/article.service';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

describe('AuthorDashboardComponent', () => {
  let component: AuthorDashboardComponent;
  let fixture: ComponentFixture<AuthorDashboardComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;

  const mockArticles = [
    { id: '1', title: 'My Article', status: 'PUBLISHED', accessType: 'PUBLIC', createdAt: '2026-01-01' },
    { id: '2', title: 'Draft Article', status: 'DRAFT', accessType: 'PREMIUM', createdAt: '2026-02-01' },
  ];

  beforeEach(async () => {
    articleServiceMock = jasmine.createSpyObj('ArticleService', ['getArticlesByAuthor']);
    articleServiceMock.getArticlesByAuthor.and.returnValue(of(mockArticles as any));

    await TestBed.configureTestingModule({
      imports: [AuthorDashboardComponent, RouterTestingModule],
      providers: [
        { provide: ArticleService, useValue: articleServiceMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AuthorDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load author articles on init', () => {
    expect(articleServiceMock.getArticlesByAuthor).toHaveBeenCalledWith('current-user-id');
    expect(component.myArticles.length).toBe(2);
  });

  it('should render the dashboard title', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Autorenbereich');
  });

  it('should have a link to create new article', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const link = compiled.querySelector('a.btn-new');
    expect(link?.textContent).toContain('Neuen Artikel erstellen');
  });

  it('should render articles in table', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const rows = compiled.querySelectorAll('tbody tr');
    expect(rows.length).toBe(2);
  });
});

