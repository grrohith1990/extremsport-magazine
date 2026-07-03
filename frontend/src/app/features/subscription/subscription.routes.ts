import { Routes } from '@angular/router';

export const SUBSCRIPTION_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./pages/portal-dashboard/portal-dashboard.component').then(m => m.PortalDashboardComponent),
    title: 'Customer Portal'
  },
  {
    path: 'manage',
    loadComponent: () => import('./pages/manage-subscription/manage-subscription.component').then(m => m.ManageSubscriptionComponent),
    title: 'Manage Subscription'
  }
];

