import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-forum-overview',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <section class="forum">
      <h1>Community Forum</h1>
      <p>Diskutiere mit anderen Extremsport-Enthusiasten</p>
      <a routerLink="new-thread" class="btn-new">Neues Thema erstellen</a>
      <div class="thread-list">
        <p>Forum-Threads werden hier angezeigt...</p>
      </div>
    </section>
  `
})
export class ForumOverviewComponent {}

