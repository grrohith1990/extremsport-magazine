import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-thread-detail',
  standalone: true,
  imports: [],
  templateUrl: './thread-detail.component.html',
  styleUrls: ['./thread-detail.component.scss']
})
export class ThreadDetailComponent {
  threadId: string | null = null;

  constructor(private route: ActivatedRoute) {
    this.threadId = this.route.snapshot.paramMap.get('id');
  }
}
