import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-archive',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './article-archive.component.html',
  styleUrls: ['./article-archive.component.scss']
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

