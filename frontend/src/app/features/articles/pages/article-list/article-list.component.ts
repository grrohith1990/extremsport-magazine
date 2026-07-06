import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {
  articles: Article[] = [];
  hasMore = true;
  private page = 0;
  private readonly pageSize = 6;

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  loadMore(): void {
    this.page++;
    this.loadArticles();
  }

  private loadArticles(): void {
    this.articleService.getPublicArticles(this.page, this.pageSize).subscribe(articles => {
      this.articles = [...this.articles, ...articles];
      this.hasMore = articles.length === this.pageSize;
    });
  }

  onImageError(event: Event): void {
    (event.target as HTMLImageElement).src = 'https://placehold.co/600x400/1a1a2e/e65100?text=Extremsport';
  }
}



