import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ArticleService } from '../../../../core/services/article.service';
import { Article } from '../../../../core/models/models';

@Component({
  selector: 'app-premium-articles',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './premium-articles.component.html',
  styleUrls: ['./premium-articles.component.scss']
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
