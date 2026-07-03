import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-search',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <section class="search-page">
      <h1>Alle Artikel</h1>
      <div class="search-bar">
        <input type="text" [(ngModel)]="query" placeholder="Artikel durchsuchen..." (keyup.enter)="search()" (input)="onInputChange()" />
        <button (click)="search()">Suchen</button>
      </div>

      <div class="article-count" *ngIf="displayedArticles.length > 0">
        <span>{{ displayedArticles.length }} Artikel gefunden</span>
      </div>

      <div class="results" *ngIf="displayedArticles.length > 0">
        <article *ngFor="let article of displayedArticles" class="result-item">
          <div class="result-image" *ngIf="article.coverImageUrl">
            <img [src]="article.coverImageUrl" [alt]="article.title" (error)="onImageError($event)" />
          </div>
          <div class="result-content">
            <div class="result-meta">
              <span class="category">{{ article.category }}</span>
              <span class="access-badge" [class.premium]="article.accessType !== 'PUBLIC'">
                {{ article.accessType === 'PUBLIC' ? 'Kostenlos' : 'Premium' }}
              </span>
            </div>
            <h3><a [routerLink]="['/article', article.id]">{{ article.title }}</a></h3>
            <p class="subtitle" *ngIf="article.subtitle">{{ article.subtitle }}</p>
            <p class="summary">{{ article.summary }}</p>
            <div class="result-footer">
              <span class="author" *ngIf="article.authorName">Von {{ article.authorName }}</span>
              <time *ngIf="article.createdAt">{{ article.createdAt | date:'dd.MM.yyyy' }}</time>
              <div class="tags">
                <span *ngFor="let tag of article.tags" class="tag">#{{ tag }}</span>
              </div>
            </div>
          </div>
        </article>
      </div>

      <p class="no-results" *ngIf="searched && displayedArticles.length === 0">
        Keine Artikel gefunden für "{{ query }}".
      </p>

      <button *ngIf="hasMore" (click)="loadMore()" class="load-more">Mehr Artikel laden</button>
    </section>
  `,
  styles: [`
    .search-page { max-width: 900px; margin: 0 auto; }
    .search-bar { display: flex; gap: 0.75rem; margin-bottom: 1.5rem; }
    .search-bar input { flex: 1; padding: 0.75rem 1rem; border: 2px solid #ddd; border-radius: 8px; font-size: 1rem; }
    .search-bar input:focus { border-color: #e65100; outline: none; }
    .search-bar button { padding: 0.75rem 1.5rem; }
    .article-count { color: #666; margin-bottom: 1rem; font-size: 0.9rem; }
    .results { display: flex; flex-direction: column; gap: 1.5rem; }
    .result-item { display: flex; gap: 1.5rem; padding: 1.5rem; border: 1px solid #eee; border-radius: 12px; transition: box-shadow 0.2s; }
    .result-item:hover { box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
    .result-image { flex-shrink: 0; width: 200px; height: 140px; border-radius: 8px; overflow: hidden; }
    .result-image img { width: 100%; height: 100%; object-fit: cover; }
    .result-content { flex: 1; }
    .result-meta { display: flex; gap: 0.75rem; align-items: center; margin-bottom: 0.5rem; }
    .category { color: #e65100; font-weight: 600; text-transform: uppercase; font-size: 0.75rem; letter-spacing: 0.5px; }
    .access-badge { font-size: 0.7rem; padding: 2px 8px; border-radius: 4px; background: #e8f5e9; color: #2e7d32; font-weight: 600; }
    .access-badge.premium { background: #fff8e1; color: #f57f17; }
    .result-content h3 { margin: 0.25rem 0; }
    .result-content h3 a { color: #1a1a2e; text-decoration: none; }
    .result-content h3 a:hover { color: #e65100; }
    .subtitle { color: #555; font-style: italic; margin: 0.25rem 0; }
    .summary { color: #666; font-size: 0.9rem; line-height: 1.5; }
    .result-footer { display: flex; flex-wrap: wrap; gap: 0.75rem; align-items: center; margin-top: 0.75rem; color: #888; font-size: 0.8rem; }
    .tags { display: flex; gap: 0.4rem; flex-wrap: wrap; }
    .tag { background: #f5f5f5; padding: 2px 6px; border-radius: 4px; font-size: 0.75rem; color: #666; }
    .no-results { text-align: center; color: #999; padding: 2rem; }
    .load-more { display: block; margin: 2rem auto; padding: 0.75rem 2rem; }
    @media (max-width: 600px) {
      .result-item { flex-direction: column; }
      .result-image { width: 100%; height: 180px; }
    }
  `]
})
export class ArticleSearchComponent implements OnInit {
  query = '';
  allArticles: Article[] = [];
  displayedArticles: Article[] = [];
  searched = false;
  hasMore = true;
  private page = 0;

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  search(): void {
    if (this.query.trim()) {
      this.articleService.searchArticles(this.query).subscribe(articles => {
        this.displayedArticles = articles;
        this.searched = true;
        this.hasMore = false;
      });
    } else {
      this.displayedArticles = this.allArticles;
      this.searched = false;
      this.hasMore = true;
    }
  }

  onInputChange(): void {
    if (!this.query.trim()) {
      this.displayedArticles = this.allArticles;
      this.searched = false;
      this.hasMore = true;
    }
  }

  loadMore(): void {
    this.page++;
    this.loadArticles();
  }

  onImageError(event: Event): void {
    (event.target as HTMLImageElement).src = 'https://placehold.co/400x280/1a1a2e/e65100?text=Extremsport';
  }

  private loadArticles(): void {
    this.articleService.getPublicArticles(this.page).subscribe(articles => {
      this.allArticles = [...this.allArticles, ...articles];
      this.displayedArticles = this.allArticles;
      if (articles.length < 20) {
        this.hasMore = false;
      }
    });
  }
}


