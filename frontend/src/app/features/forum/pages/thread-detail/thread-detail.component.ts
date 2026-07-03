import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-thread-detail',
  standalone: true,
  imports: [CommonModule],
  template: `
    <section class="thread-detail">
      <h1>Thread Detail</h1>
      <p>Thread ID: {{ threadId }}</p>
      <div class="posts">
        <p>Posts werden hier angezeigt...</p>
      </div>
    </section>
  `
})
export class ThreadDetailComponent {
  threadId: string | null = null;

  constructor(private route: ActivatedRoute) {
    this.threadId = this.route.snapshot.paramMap.get('id');
  }
}

