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
  templateUrl: './article-search.component.html',
  styleUrls: ['./article-search.component.scss']
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


