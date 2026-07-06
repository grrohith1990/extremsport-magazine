import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '../../../../core/services/article.service';
import { Article } from '../../../../core/models/models';

@Component({
  selector: 'app-author-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './author-dashboard.component.html',
  styleUrls: ['./author-dashboard.component.scss']
})
export class AuthorDashboardComponent implements OnInit {
  myArticles: Article[] = [];

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    // TODO: Get current user ID from auth service
    const authorId = 'current-user-id';
    this.articleService.getArticlesByAuthor(authorId).subscribe(articles => {
      this.myArticles = articles;
    });
  }
}

