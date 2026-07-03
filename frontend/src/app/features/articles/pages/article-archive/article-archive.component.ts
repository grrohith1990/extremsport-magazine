import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '../../../../core/services/article.service';
import { Article } from '../../../../core/models/models';

@Component({
  selector: 'app-article-archive',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <section class="archive">
      <h1>Archiv</h1>
      <div class="archive-list">
        <article *ngFor="let article of articles" class="archive-item">
          <time>{{ article.publishedAt | date:'dd.MM.yyyy' }}</time>
          <h3><a [routerLink]="['/article', article.id]">{{ article.title }}</a></h3>
          <span class="category">{{ article.category }}</span>
        </article>
      </div>
    </section>
  `
})
export class ArticleArchiveComponent implements OnInit {
  articles: Article[] = [];

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.articleService.getArchivedArticles().subscribe(articles => {
      this.articles = articles;
    });
  }
}

