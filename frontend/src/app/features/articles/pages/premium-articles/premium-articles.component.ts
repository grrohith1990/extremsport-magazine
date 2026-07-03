import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '../../../../core/services/article.service';
import { Article } from '../../../../core/models/models';

@Component({
  selector: 'app-premium-articles',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <section class="premium">
      <h1>Premium Inhalte</h1>
      <p class="intro">Exklusive Artikel für Abonnenten</p>
      <div class="articles-grid">
        <article *ngFor="let article of articles" class="article-card premium-card">
          <img *ngIf="article.coverImageUrl" [src]="article.coverImageUrl" [alt]="article.title" />
          <div class="article-card-content">
            <span class="badge">Premium</span>
            <h2><a [routerLink]="['/article', article.id]">{{ article.title }}</a></h2>
            <p>{{ article.summary }}</p>
          </div>
        </article>
      </div>
    </section>
  `,
  styles: [`
    .articles-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 1.5rem; }
    .premium-card { border: 2px solid gold; border-radius: 8px; overflow: hidden; }
    .badge { background: gold; padding: 2px 8px; border-radius: 4px; font-weight: bold; }
  `]
})
export class PremiumArticlesComponent implements OnInit {
  articles: Article[] = [];

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.articleService.getPremiumArticles().subscribe(articles => {
      this.articles = articles;
    });
  }
}

