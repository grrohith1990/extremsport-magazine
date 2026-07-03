import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <article *ngIf="article" class="article-detail">
      <header>
        <span class="category">{{ article.category }}</span>
        <span *ngIf="article.accessType !== 'PUBLIC'" class="badge premium">Premium</span>
        <h1>{{ article.title }}</h1>
        <p class="subtitle" *ngIf="article.subtitle">{{ article.subtitle }}</p>
        <div class="meta">
          <span>Von {{ article.authorName }}</span>
          <time>{{ article.publishedAt | date:'dd. MMMM yyyy' }}</time>
        </div>
      </header>
      <img *ngIf="article.coverImageUrl" [src]="article.coverImageUrl" [alt]="article.title" class="cover" (error)="onImageError($event)" />
      <div class="content" [innerHTML]="article.content"></div>
      <footer>
        <div class="tags">
          <span *ngFor="let tag of article.tags" class="tag">#{{ tag }}</span>
        </div>
      </footer>
    </article>
    <div *ngIf="!article" class="loading">Artikel wird geladen...</div>
  `,
  styles: [`
    .article-detail { max-width: 800px; margin: 0 auto; padding: 2rem; }
    .cover { width: 100%; border-radius: 8px; margin: 1.5rem 0; }
    .category { color: #e65100; font-weight: bold; text-transform: uppercase; }
    .badge.premium { background: gold; padding: 2px 8px; border-radius: 4px; margin-left: 1rem; }
    .meta { color: #666; margin: 1rem 0; }
    .tag { background: #f0f0f0; padding: 4px 8px; border-radius: 4px; margin-right: 0.5rem; }
  `]
})
export class ArticleDetailComponent implements OnInit {
  article: Article | null = null;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.articleService.getArticleById(id).subscribe(article => {
        this.article = article;
      });
    }
  }

  onImageError(event: Event): void {
    (event.target as HTMLImageElement).src = 'https://placehold.co/800x400/1a1a2e/e65100?text=Extremsport';
  }
}



