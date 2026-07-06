import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-unauthorized',
  standalone: true,
  imports: [RouterModule],
  template: `
    <div class="unauthorized-container">
      <h1>🔒 Zugriff verweigert</h1>
      <p>Du musst angemeldet sein, um auf diese Seite zuzugreifen.</p>
      <a routerLink="/">Zurück zur Startseite</a>
    </div>
  `,
  styles: [`
    .unauthorized-container { text-align: center; padding: 4rem 2rem; }
    .unauthorized-container h1 { font-size: 2rem; margin-bottom: 1rem; }
    .unauthorized-container a { color: #e65100; text-decoration: none; font-weight: bold; }
  `]
})
export class UnauthorizedComponent {}
