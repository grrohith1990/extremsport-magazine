import { Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';

/**
 * Application Routes - maps to the system's functional areas:
 * - Public Area (articles, search, archive)
 * - Customer Portal (subscription management)
 * - Author Area (article creation/editing)
 * - Forum (moderated discussions)
 */
export const routes: Routes = [
  // === PUBLIC AREA (no auth required) ===
  {
    path: '',
    loadChildren: () => import('./features/articles/articles.routes').then(m => m.ARTICLE_ROUTES)
  },

  // === FORUM (partially protected) ===
  {
    path: 'forum',
    loadChildren: () => import('./features/forum/forum.routes').then(m => m.FORUM_ROUTES)
  },

  // === CUSTOMER PORTAL (auth required - SUBSCRIBER role) ===
  {
    path: 'portal',
    canActivate: [AuthGuard],
    data: { roles: ['SUBSCRIBER', 'ADMIN'] },
    loadChildren: () => import('./features/subscription/subscription.routes').then(m => m.SUBSCRIPTION_ROUTES)
  },

  // === AUTHOR AREA (auth required - AUTHOR role) ===
  {
    path: 'author',
    canActivate: [AuthGuard],
    data: { roles: ['AUTHOR', 'EDITOR', 'ADMIN'] },
    loadChildren: () => import('./features/author/author.routes').then(m => m.AUTHOR_ROUTES)
  },

  // === FALLBACK ===
  {
    path: 'unauthorized',
    loadComponent: () => import('./shared/components/unauthorized.component').then(m => m.UnauthorizedComponent)
  },
  {
    path: '**',
    redirectTo: ''
  }
];

