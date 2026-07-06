import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-thread',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './new-thread.component.html',
  styleUrls: ['./new-thread.component.scss']
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
