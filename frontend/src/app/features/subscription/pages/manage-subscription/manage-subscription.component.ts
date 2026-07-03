import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-manage-subscription',
  standalone: true,
  imports: [CommonModule],
  template: `
    <section class="manage-subscription">
      <h1>Abo verwalten</h1>
      <div class="plans">
        <div class="plan-card">
          <h3>Monatlich</h3>
          <p class="price">9,99 € / Monat</p>
          <ul>
            <li>Zugang zu allen Premium-Artikeln</li>
            <li>Archiv-Zugang</li>
            <li>Jederzeit kündbar</li>
          </ul>
          <button>Auswählen</button>
        </div>
        <div class="plan-card featured">
          <h3>Jährlich</h3>
          <p class="price">89,99 € / Jahr</p>
          <ul>
            <li>Alle Vorteile des Monatsabos</li>
            <li>25% Ersparnis</li>
            <li>Exklusive Inhalte</li>
          </ul>
          <button>Auswählen</button>
        </div>
      </div>
    </section>
  `,
  styles: [`
    .plans { display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 2rem; margin-top: 2rem; }
    .plan-card { padding: 2rem; border: 1px solid #ddd; border-radius: 12px; text-align: center; }
    .plan-card.featured { border-color: gold; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
    .price { font-size: 1.5rem; font-weight: bold; color: #e65100; }
  `]
})
export class ManageSubscriptionComponent {}

