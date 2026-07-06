import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-list',
  standalone: true,
  imports: [DatePipe, FormsModule, RouterModule],
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {
  articles: Article[] = [];
  hasMore = true;
  searchQuery = '';
  isSearching = false;
  private page = 0;
  private readonly pageSize = 6;

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  search(): void {
    if (this.searchQuery.trim()) {
      this.isSearching = true;
      this.articleService.searchArticles(this.searchQuery).subscribe(articles => {
        this.articles = articles;
        this.hasMore = false;
      });
    }
  }

  onSearchInput(): void {
    if (!this.searchQuery.trim()) {
      this.clearSearch();
    }
  }

  clearSearch(): void {
    this.searchQuery = '';
    this.isSearching = false;
    this.articles = [];
    this.page = 0;
    this.hasMore = true;
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
