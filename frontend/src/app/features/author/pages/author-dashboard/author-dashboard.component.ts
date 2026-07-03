import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ArticleService } from '../../../../core/services/article.service';
import { Article } from '../../../../core/models/models';

@Component({
  selector: 'app-author-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <section class="author-dashboard">
      <h1>Autorenbereich</h1>
      <a routerLink="new-article" class="btn-new">Neuen Artikel erstellen</a>
      <h2>Meine Artikel</h2>
      <table class="articles-table">
        <thead>
          <tr>
            <th>Titel</th>
            <th>Status</th>
            <th>Zugang</th>
            <th>Erstellt</th>
            <th>Aktionen</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let article of myArticles">
            <td>{{ article.title }}</td>
            <td><span class="status" [class]="article.status.toLowerCase()">{{ article.status }}</span></td>
            <td>{{ article.accessType }}</td>
            <td>{{ article.createdAt | date:'dd.MM.yyyy' }}</td>
            <td><a [routerLink]="['edit', article.id]">Bearbeiten</a></td>
          </tr>
        </tbody>
      </table>
    </section>
  `,
  styles: [`
    .articles-table { width: 100%; border-collapse: collapse; margin-top: 1rem; }
    .articles-table th, .articles-table td { padding: 0.75rem; border-bottom: 1px solid #eee; text-align: left; }
    .status { padding: 2px 8px; border-radius: 4px; font-size: 0.8rem; }
    .status.draft { background: #fff3e0; }
    .status.published { background: #e8f5e9; }
    .status.archived { background: #eceff1; }
    .btn-new { display: inline-block; margin-bottom: 2rem; padding: 0.75rem 1.5rem; background: #e65100; color: white; border-radius: 6px; text-decoration: none; }
  `]
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

