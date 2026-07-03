import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from '../../../../core/services/article.service';
import { Article, AccessType } from '../../../../core/models/models';

@Component({
  selector: 'app-article-editor',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <section class="article-editor">
      <h1>{{ isEdit ? 'Artikel bearbeiten' : 'Neuer Artikel' }}</h1>
      <form (ngSubmit)="save()">
        <div class="form-group">
          <label for="title">Titel *</label>
          <input id="title" type="text" [(ngModel)]="article.title" name="title" required />
        </div>
        <div class="form-group">
          <label for="subtitle">Untertitel</label>
          <input id="subtitle" type="text" [(ngModel)]="article.subtitle" name="subtitle" />
        </div>
        <div class="form-group">
          <label for="category">Kategorie</label>
          <select id="category" [(ngModel)]="article.category" name="category">
            <option value="climbing">Klettern</option>
            <option value="surfing">Surfen</option>
            <option value="skydiving">Fallschirmspringen</option>
            <option value="mountainbiking">Mountainbiking</option>
            <option value="snowboarding">Snowboarding</option>
            <option value="other">Sonstiges</option>
          </select>
        </div>
        <div class="form-group">
          <label for="accessType">Zugangsart</label>
          <select id="accessType" [(ngModel)]="article.accessType" name="accessType">
            <option [value]="AccessType.PUBLIC">Öffentlich (kostenlos)</option>
            <option [value]="AccessType.PREMIUM">Premium (Einzelkauf oder Abo)</option>
            <option [value]="AccessType.EXCLUSIVE">Exklusiv (nur Abo)</option>
          </select>
        </div>
        <div class="form-group">
          <label for="summary">Zusammenfassung</label>
          <textarea id="summary" [(ngModel)]="article.summary" name="summary" rows="3"></textarea>
        </div>
        <div class="form-group">
          <label for="content">Inhalt *</label>
          <textarea id="content" [(ngModel)]="article.content" name="content" rows="20" required></textarea>
        </div>
        <div class="form-group">
          <label for="tags">Tags (kommagetrennt)</label>
          <input id="tags" type="text" [(ngModel)]="tagsInput" name="tags" />
        </div>
        <div class="actions">
          <button type="submit">{{ isEdit ? 'Speichern' : 'Erstellen' }}</button>
          <button type="button" *ngIf="isEdit" (click)="publish()">Veröffentlichen</button>
        </div>
      </form>
    </section>
  `,
  styles: [`
    .article-editor { max-width: 900px; margin: 0 auto; padding: 2rem; }
    .form-group { margin-bottom: 1.5rem; }
    .form-group label { display: block; font-weight: bold; margin-bottom: 0.5rem; }
    .form-group input, .form-group textarea, .form-group select { width: 100%; padding: 0.75rem; border: 1px solid #ddd; border-radius: 6px; font-size: 1rem; }
    .actions { display: flex; gap: 1rem; }
    .actions button { padding: 0.75rem 1.5rem; border-radius: 6px; cursor: pointer; }
  `]
})
export class ArticleEditorComponent implements OnInit {
  article: Partial<Article> = { accessType: AccessType.PUBLIC };
  tagsInput = '';
  isEdit = false;
  AccessType = AccessType;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private articleService: ArticleService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.articleService.getArticleById(id).subscribe(article => {
        this.article = article;
        this.tagsInput = article.tags?.join(', ') || '';
      });
    }
  }

  save(): void {
    this.article.tags = this.tagsInput.split(',').map(t => t.trim()).filter(t => t);

    if (this.isEdit && this.article.id) {
      this.articleService.updateArticle(this.article.id, this.article).subscribe(() => {
        this.router.navigate(['/author']);
      });
    } else {
      this.articleService.createArticle(this.article).subscribe(() => {
        this.router.navigate(['/author']);
      });
    }
  }

  publish(): void {
    if (this.article.id) {
      this.articleService.publishArticle(this.article.id).subscribe(() => {
        this.router.navigate(['/author']);
      });
    }
  }
}

