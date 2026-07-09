import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { ForumService } from '../../../../core/services/forum.service';

@Component({
  selector: 'app-new-thread',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './new-thread.component.html',
  styleUrls: ['./new-thread.component.scss']
})
export class NewThreadComponent {
  title = '';
  description = '';
  category = 'GENERAL';
  isSubmitting = false;
  errorMessage = '';

  categories = ['GENERAL', 'SKIING', 'SNOWBOARDING', 'SURFING', 'CLIMBING', 'MOUNTAIN_BIKING', 'PARAGLIDING', 'OTHER'];

  constructor(
    private router: Router,
    private forumService: ForumService,
    private keycloakService: KeycloakService
  ) {}

  async createThread(): Promise<void> {
    if (!this.title.trim() || !this.description.trim()) {
      this.errorMessage = 'Bitte Titel und Beschreibung ausfüllen.';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';

    try {
      const userProfile = await this.keycloakService.loadUserProfile();
      const userId = await this.keycloakService.getKeycloakInstance().subject;

      this.forumService.createThread({
        title: this.title,
        description: this.description,
        authorId: userId || '',
        authorName: userProfile.firstName
          ? `${userProfile.firstName} ${userProfile.lastName || ''}`.trim()
          : userProfile.username || 'Unbekannt',
        category: this.category
      }).subscribe({
        next: () => {
          this.router.navigate(['/forum']);
        },
        error: (err) => {
          console.error('Error creating thread:', err);
          this.errorMessage = 'Thema konnte nicht erstellt werden. Bitte versuche es erneut.';
          this.isSubmitting = false;
        }
      });
    } catch (err) {
      console.error('Error getting user info:', err);
      this.errorMessage = 'Benutzerinformationen konnten nicht geladen werden.';
      this.isSubmitting = false;
    }
  }
}
