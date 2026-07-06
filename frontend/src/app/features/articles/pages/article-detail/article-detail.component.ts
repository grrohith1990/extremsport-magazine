import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ArticleService } from '@core/services/article.service';
import { Article } from '@core/models/models';

@Component({
  selector: 'app-article-detail',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
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
