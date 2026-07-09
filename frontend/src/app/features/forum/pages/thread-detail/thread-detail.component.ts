import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { KeycloakService } from 'keycloak-angular';
import { ForumService } from '@core/services/forum.service';
import { ForumThread, ForumPost } from '@core/models/models';

@Component({
  selector: 'app-thread-detail',
  standalone: true,
  imports: [DatePipe, RouterModule, FormsModule],
  templateUrl: './thread-detail.component.html',
  styleUrls: ['./thread-detail.component.scss']
})
export class ThreadDetailComponent implements OnInit {
  threadId: string | null = null;
  thread: ForumThread | null = null;
  posts: ForumPost[] = [];
  isLoading = true;
  errorMessage = '';

  // New post
  newPostContent = '';
  isSubmitting = false;
  isLoggedIn = false;

  constructor(
    private route: ActivatedRoute,
    private forumService: ForumService,
    private keycloakService: KeycloakService
  ) {
    this.threadId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    if (this.threadId) {
      this.loadThread();
      this.loadPosts();
    }
    try {
      this.isLoggedIn = this.keycloakService.isLoggedIn();
    } catch {
      this.isLoggedIn = false;
    }
  }

  loadThread(): void {
    this.forumService.getThreadById(this.threadId!).subscribe({
      next: (thread) => {
        this.thread = thread;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading thread:', err);
        this.errorMessage = 'Thread konnte nicht geladen werden.';
        this.isLoading = false;
      }
    });
  }

  loadPosts(): void {
    this.forumService.getPostsByThread(this.threadId!).subscribe({
      next: (posts) => {
        this.posts = posts;
      },
      error: (err) => {
        console.error('Error loading posts:', err);
      }
    });
  }

  async submitPost(): Promise<void> {
    if (!this.newPostContent.trim()) return;

    this.isSubmitting = true;
    try {
      const userProfile = await this.keycloakService.loadUserProfile();
      const userId = this.keycloakService.getKeycloakInstance().subject;

      this.forumService.createPost(this.threadId!, {
        authorId: userId || '',
        authorName: userProfile.firstName
          ? `${userProfile.firstName} ${userProfile.lastName || ''}`.trim()
          : userProfile.username || 'Unbekannt',
        content: this.newPostContent
      }).subscribe({
        next: (post) => {
          this.posts.push(post);
          this.newPostContent = '';
          this.isSubmitting = false;
        },
        error: (err) => {
          console.error('Error creating post:', err);
          this.isSubmitting = false;
        }
      });
    } catch (err) {
      console.error('Error getting user info:', err);
      this.isSubmitting = false;
    }
  }

  async login(): Promise<void> {
    await this.keycloakService.login({ redirectUri: window.location.href });
  }
}
