import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-unauthorized',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="unauthorized-container">
      <h1>403 - Zugriff verweigert</h1>
      <p>Sie haben keine Berechtigung, auf diese Seite zuzugreifen.</p>
      <a routerLink="/">Zurück zur Startseite</a>
    </div>
  `
})
export class UnauthorizedComponent {}

