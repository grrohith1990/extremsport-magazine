import { Routes } from '@angular/router';

export const AUTHOR_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/author-dashboard/author-dashboard.component').then(m => m.AuthorDashboardComponent),
    title: 'Author Dashboard'
  },
  {
    path: 'new-article',
    loadComponent: () => import('./pages/article-editor/article-editor.component').then(m => m.ArticleEditorComponent),
    title: 'New Article'
  },
  {
    path: 'edit/:id',
    loadComponent: () => import('./pages/article-editor/article-editor.component').then(m => m.ArticleEditorComponent),
    title: 'Edit Article'
  }
];

