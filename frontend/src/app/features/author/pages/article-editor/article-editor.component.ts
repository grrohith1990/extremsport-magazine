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
  templateUrl: './article-editor.component.html',
  styleUrls: ['./article-editor.component.scss']
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

