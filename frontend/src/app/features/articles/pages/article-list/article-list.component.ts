import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <section class="article-list">
      <h1>Extremsport Magazine</h1>
      <div class="articles-grid">
        <article *ngFor="let article of articles" class="article-card">
          <img *ngIf="article.coverImageUrl" [src]="article.coverImageUrl" [alt]="article.title" (error)="onImageError($event)" />
          <div class="article-card-content">
            <span class="category">{{ article.category }}</span>
            <h2><a [routerLink]="['/article', article.id]">{{ article.title }}</a></h2>
            <p>{{ article.summary }}</p>
            <div class="meta">
              <span>{{ article.authorName }}</span>
              <time>{{ article.publishedAt | date:'dd.MM.yyyy' }}</time>
            </div>
          </div>
        </article>
      </div>
      <button (click)="loadMore()" *ngIf="articles.length > 0">Mehr laden</button>
    </section>
  `,
  styles: [`
    .articles-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 1.5rem; }
    .article-card { border: 1px solid #eee; border-radius: 8px; overflow: hidden; }
    .article-card img { width: 100%; height: 200px; object-fit: cover; }
    .article-card-content { padding: 1rem; }
    .category { color: #e65100; font-weight: bold; text-transform: uppercase; font-size: 0.8rem; }
    .meta { display: flex; justify-content: space-between; color: #666; font-size: 0.85rem; margin-top: 0.5rem; }
  `]
})
export class ArticleListComponent implements OnInit {
  articles: Article[] = [];
  private page = 0;

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  loadMore(): void {
    this.page++;
    this.loadArticles();
  }

  private loadArticles(): void {
    this.articleService.getPublicArticles(this.page).subscribe(articles => {
      this.articles = [...this.articles, ...articles];
    });
  }

  onImageError(event: Event): void {
    (event.target as HTMLImageElement).src = 'https://placehold.co/600x400/1a1a2e/e65100?text=Extremsport';
  }
}



