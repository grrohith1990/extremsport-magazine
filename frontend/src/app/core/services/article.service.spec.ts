import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ArticleService } from './article.service';
import { Article, ArticleStatus, AccessType } from '../models/models';
import { environment } from '../../../environments/environment';

describe('ArticleService', () => {
  let service: ArticleService;
  let httpMock: HttpTestingController;
  const baseUrl = `${environment.apiGatewayUrl}/api/v1/articles`;

  const mockArticle: Article = {
    id: '123',
    title: 'Extreme Skiing Guide',
    subtitle: 'Master the slopes',
    content: 'Full content here...',
    summary: 'A skiing guide',
    authorId: 'author-1',
    authorName: 'Jane Doe',
    status: ArticleStatus.PUBLISHED,
    accessType: AccessType.PUBLIC,
    tags: ['skiing', 'winter'],
    category: 'Winter Sports',
    coverImageUrl: 'https://example.com/skiing.jpg',
    createdAt: '2026-01-01T00:00:00',
    updatedAt: '2026-01-02T00:00:00',
    publishedAt: '2026-01-02T00:00:00',
    archived: false
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ArticleService]
    });
    service = TestBed.inject(ArticleService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getPublicArticles', () => {
    it('should fetch public articles with default pagination', () => {
      service.getPublicArticles().subscribe(articles => {
        expect(articles).toEqual([mockArticle]);
      });

      const req = httpMock.expectOne(`${baseUrl}/public?page=0&size=20`);
      expect(req.request.method).toBe('GET');
      req.flush([mockArticle]);
    });

    it('should fetch public articles with custom pagination', () => {
      service.getPublicArticles(2, 10).subscribe(articles => {
        expect(articles.length).toBe(0);
      });

      const req = httpMock.expectOne(`${baseUrl}/public?page=2&size=10`);
      expect(req.request.method).toBe('GET');
      req.flush([]);
    });
  });

  describe('getArticleById', () => {
    it('should fetch a single article by id', () => {
      service.getArticleById('123').subscribe(article => {
        expect(article).toEqual(mockArticle);
      });

      const req = httpMock.expectOne(`${baseUrl}/123`);
      expect(req.request.method).toBe('GET');
      req.flush(mockArticle);
    });
  });

  describe('searchArticles', () => {
    it('should search articles with query and default pagination', () => {
      service.searchArticles('skiing').subscribe(articles => {
        expect(articles).toEqual([mockArticle]);
      });

      const req = httpMock.expectOne(`${baseUrl}/search?q=skiing&page=0&size=20`);
      expect(req.request.method).toBe('GET');
      req.flush([mockArticle]);
    });

    it('should search articles with custom pagination', () => {
      service.searchArticles('surfing', 1, 5).subscribe();

      const req = httpMock.expectOne(`${baseUrl}/search?q=surfing&page=1&size=5`);
      expect(req.request.method).toBe('GET');
      req.flush([]);
    });
  });

  describe('getPremiumArticles', () => {
    it('should fetch premium articles', () => {
      service.getPremiumArticles().subscribe(articles => {
        expect(articles).toEqual([mockArticle]);
      });

      const req = httpMock.expectOne(`${baseUrl}/premium?page=0&size=20`);
      expect(req.request.method).toBe('GET');
      req.flush([mockArticle]);
    });
  });

  describe('getArchivedArticles', () => {
    it('should fetch archived articles', () => {
      service.getArchivedArticles(0, 15).subscribe(articles => {
        expect(articles).toEqual([]);
      });

      const req = httpMock.expectOne(`${baseUrl}/archive?page=0&size=15`);
      expect(req.request.method).toBe('GET');
      req.flush([]);
    });
  });

  describe('createArticle', () => {
    it('should create a new article', () => {
      const newArticle: Partial<Article> = {
        title: 'New Article',
        content: 'New content',
        accessType: AccessType.PUBLIC
      };

      service.createArticle(newArticle).subscribe(article => {
        expect(article).toEqual(mockArticle);
      });

      const req = httpMock.expectOne(baseUrl);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual(newArticle);
      req.flush(mockArticle);
    });
  });

  describe('updateArticle', () => {
    it('should update an existing article', () => {
      const updates: Partial<Article> = { title: 'Updated Title' };

      service.updateArticle('123', updates).subscribe(article => {
        expect(article).toEqual(mockArticle);
      });

      const req = httpMock.expectOne(`${baseUrl}/123`);
      expect(req.request.method).toBe('PUT');
      expect(req.request.body).toEqual(updates);
      req.flush(mockArticle);
    });
  });

  describe('publishArticle', () => {
    it('should publish an article', () => {
      service.publishArticle('123').subscribe();

      const req = httpMock.expectOne(`${baseUrl}/123/publish`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual({});
      req.flush(null);
    });
  });

  describe('archiveArticle', () => {
    it('should archive an article', () => {
      service.archiveArticle('123').subscribe();

      const req = httpMock.expectOne(`${baseUrl}/123/archive`);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toEqual({});
      req.flush(null);
    });
  });

  describe('getArticlesByAuthor', () => {
    it('should fetch articles by author id', () => {
      service.getArticlesByAuthor('author-1').subscribe(articles => {
        expect(articles).toEqual([mockArticle]);
      });

      const req = httpMock.expectOne(`${baseUrl}/author/author-1`);
      expect(req.request.method).toBe('GET');
      req.flush([mockArticle]);
    });
  });
});

