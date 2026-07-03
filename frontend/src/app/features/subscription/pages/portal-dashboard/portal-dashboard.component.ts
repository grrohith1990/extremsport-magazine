import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-portal-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <section class="portal-dashboard">
      <h1>Kundenportal</h1>
      <div class="dashboard-grid">
        <div class="card">
          <h3>Mein Abo</h3>
          <p>Status: <strong>Aktiv</strong></p>
          <a routerLink="manage">Abo verwalten</a>
        </div>
        <div class="card">
          <h3>Meine Käufe</h3>
          <p>Einzelartikel und Bestellungen</p>
        </div>
        <div class="card">
          <h3>Premium Inhalte</h3>
          <a routerLink="/premium">Alle Premium Artikel ansehen</a>
        </div>
      </div>
    </section>
  `,
  styles: [`
    .dashboard-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 1.5rem; margin-top: 1.5rem; }
    .card { padding: 1.5rem; border: 1px solid #ddd; border-radius: 8px; }
  `]
})
export class PortalDashboardComponent {}

