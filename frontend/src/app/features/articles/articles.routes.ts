import { Routes } from '@angular/router';

export const ARTICLE_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/article-list/article-list.component').then(m => m.ArticleListComponent),
    title: 'Extremsport Magazine - Home'
  },
  {
    path: 'article/:id',
    loadComponent: () => import('./pages/article-detail/article-detail.component').then(m => m.ArticleDetailComponent),
    title: 'Article'
  },
  {
    path: 'search',
    loadComponent: () => import('./pages/article-search/article-search.component').then(m => m.ArticleSearchComponent),
    title: 'Search'
  },
  {
    path: 'archive',
    loadComponent: () => import('./pages/article-archive/article-archive.component').then(m => m.ArticleArchiveComponent),
    title: 'Archive'
  },
  {
    path: 'premium',
    loadComponent: () => import('./pages/premium-articles/premium-articles.component').then(m => m.PremiumArticlesComponent),
    title: 'Premium Content'
  }
];

