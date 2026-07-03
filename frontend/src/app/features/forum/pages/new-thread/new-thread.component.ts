import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-thread',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <section class="new-thread">
      <h1>Neues Thema erstellen</h1>
      <form (ngSubmit)="createThread()">
        <div class="form-group">
          <label for="title">Titel</label>
          <input id="title" type="text" [(ngModel)]="title" name="title" required />
        </div>
        <div class="form-group">
          <label for="content">Inhalt</label>
          <textarea id="content" [(ngModel)]="content" name="content" rows="10" required></textarea>
        </div>
        <button type="submit">Thema erstellen</button>
      </form>
    </section>
  `
})
export class NewThreadComponent {
  title = '';
  content = '';

  constructor(private router: Router) {}

  createThread(): void {
    // TODO: Call forum service to create thread
    console.log('Creating thread:', this.title);
    this.router.navigate(['/forum']);
  }
}

