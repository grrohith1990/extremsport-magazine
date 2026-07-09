import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ForumService } from '../../../../core/services/forum.service';
import { ForumThread } from '../../../../core/models/models';

@Component({
  selector: 'app-forum-overview',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './forum-overview.component.html',
  styleUrls: ['./forum-overview.component.scss']
})
export class ForumOverviewComponent implements OnInit {
  threads: ForumThread[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(private forumService: ForumService) {}

  ngOnInit(): void {
    this.loadThreads();
  }

  loadThreads(): void {
    this.isLoading = true;
    this.forumService.getRecentThreads().subscribe({
      next: (threads) => {
        this.threads = threads;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading threads:', err);
        this.errorMessage = 'Forum-Threads konnten nicht geladen werden.';
        this.isLoading = false;
      }
    });
  }
}
