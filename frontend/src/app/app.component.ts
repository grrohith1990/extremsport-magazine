import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule],
  template: `
    <header class="main-header">
      <nav>
        <a routerLink="/" class="logo">🏔️ Extremsport Magazine</a>
        <div class="nav-links">
          <a routerLink="/">Home</a>
          <a routerLink="/premium">Premium</a>
          <a routerLink="/archive">Archiv</a>
          <a routerLink="/forum">Forum</a>
          <a routerLink="/portal" class="btn-portal">Mein Konto</a>
        </div>
      </nav>
    </header>
    <main>
      <router-outlet></router-outlet>
    </main>
    <footer class="main-footer">
      <p>&copy; 2026 Extremsport Magazine. Alle Rechte vorbehalten.</p>
    </footer>
  `,
  styles: [`
    .main-header { background: #1a1a2e; color: white; padding: 1rem 2rem; }
    nav { display: flex; justify-content: space-between; align-items: center; max-width: 1200px; margin: 0 auto; }
    .logo { color: white; text-decoration: none; font-size: 1.3rem; font-weight: bold; }
    .nav-links { display: flex; gap: 1.5rem; align-items: center; }
    .nav-links a { color: #ccc; text-decoration: none; }
    .nav-links a:hover { color: white; }
    .btn-portal { background: #e65100; color: white !important; padding: 0.5rem 1rem; border-radius: 6px; }
    main { min-height: calc(100vh - 140px); max-width: 1200px; margin: 0 auto; padding: 2rem; }
    .main-footer { background: #1a1a2e; color: #999; text-align: center; padding: 1.5rem; }
  `]
})
export class AppComponent {
  title = 'Extremsport Magazine';
}

