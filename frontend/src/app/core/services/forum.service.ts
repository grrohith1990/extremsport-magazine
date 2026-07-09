import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ForumThread, ForumPost } from '../models/models';
import { environment } from '@env/environment';

export interface CreateThreadRequest {
  title: string;
  description: string;
  authorId: string;
  authorName: string;
  category: string;
}

export interface CreatePostRequest {
  authorId: string;
  authorName: string;
  content: string;
  parentPostId?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  private readonly baseUrl = `${environment.apiGatewayUrl}/api/v1/forum`;

  constructor(private http: HttpClient) {}

  getRecentThreads(page = 0, size = 20): Observable<ForumThread[]> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<ForumThread[]>(`${this.baseUrl}/threads`, { params });
  }

  getThreadById(id: string): Observable<ForumThread> {
    return this.http.get<ForumThread>(`${this.baseUrl}/threads/${id}`);
  }

  getThreadsByCategory(category: string, page = 0, size = 20): Observable<ForumThread[]> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<ForumThread[]>(`${this.baseUrl}/threads/category/${category}`, { params });
  }

  searchThreads(query: string, page = 0, size = 20): Observable<ForumThread[]> {
    const params = new HttpParams().set('q', query).set('page', page).set('size', size);
    return this.http.get<ForumThread[]>(`${this.baseUrl}/threads/search`, { params });
  }

  createThread(request: CreateThreadRequest): Observable<ForumThread> {
    return this.http.post<ForumThread>(`${this.baseUrl}/threads`, request);
  }

  getPostsByThread(threadId: string, page = 0, size = 50): Observable<ForumPost[]> {
    const params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<ForumPost[]>(`${this.baseUrl}/threads/${threadId}/posts`, { params });
  }

  createPost(threadId: string, request: CreatePostRequest): Observable<ForumPost> {
    return this.http.post<ForumPost>(`${this.baseUrl}/threads/${threadId}/posts`, request);
  }
}

