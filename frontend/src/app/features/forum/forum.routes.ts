import { Routes } from '@angular/router';
import { AuthGuard } from '../../core/guards/auth.guard';

export const FORUM_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/forum-overview/forum-overview.component').then(m => m.ForumOverviewComponent),
    title: 'Forum'
  },
  {
    path: 'thread/:id',
    loadComponent: () => import('./pages/thread-detail/thread-detail.component').then(m => m.ThreadDetailComponent),
    title: 'Thread'
  },
  {
    path: 'new-thread',
    canActivate: [AuthGuard],
    data: { roles: ['READER', 'SUBSCRIBER', 'ADMIN'] },
    loadComponent: () => import('./pages/new-thread/new-thread.component').then(m => m.NewThreadComponent),
    title: 'New Thread'
  }
];

