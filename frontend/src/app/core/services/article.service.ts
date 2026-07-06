import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../models/models';
import { environment } from '../../../environments/environment';

/**
 * Article Service - communicates with the Article microservice via API Gateway.
 */
@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private readonly baseUrl = `${environment.apiGatewayUrl}/api/v1/articles`;

  constructor(private http: HttpClient) {}

  // === Public Access ===

  getPublicArticles(page = 0, size = 6): Observable<Article[]> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Article[]>(`${this.baseUrl}/public`, { params });
  }

  getArticleById(id: string): Observable<Article> {
    return this.http.get<Article>(`${this.baseUrl}/${id}`);
  }

  searchArticles(query: string, page = 0, size = 20): Observable<Article[]> {
    const params = new HttpParams().set('q', query).set('page', page).set('size', size);
    return this.http.get<Article[]>(`${this.baseUrl}/search`, { params });
  }

  // === Premium Access ===

  getPremiumArticles(page = 0, size = 20): Observable<Article[]> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Article[]>(`${this.baseUrl}/premium`, { params });
  }

  // === Archive ===

  getArchivedArticles(page = 0, size = 20): Observable<Article[]> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<Article[]>(`${this.baseUrl}/archive`, { params });
  }

  // === Author Operations ===

  createArticle(article: Partial<Article>): Observable<Article> {
    return this.http.post<Article>(this.baseUrl, article);
  }

  updateArticle(id: string, article: Partial<Article>): Observable<Article> {
    return this.http.put<Article>(`${this.baseUrl}/${id}`, article);
  }

  publishArticle(id: string): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/${id}/publish`, {});
  }

  archiveArticle(id: string): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/${id}/archive`, {});
  }

  getArticlesByAuthor(authorId: string): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.baseUrl}/author/${authorId}`);
  }
}

