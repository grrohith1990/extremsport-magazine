import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Extremsport Magazine';
  isLoggedIn = false;
  displayName = '';
  dropdownOpen = false;

  constructor(private keycloakService: KeycloakService) {}

  ngOnInit(): void {
    try {
      this.isLoggedIn = this.keycloakService.isLoggedIn();
      if (this.isLoggedIn) {
        const instance = this.keycloakService.getKeycloakInstance();
        this.displayName = instance.tokenParsed?.['name']
          || instance.tokenParsed?.['preferred_username']
          || '';
      }
    } catch {
      this.isLoggedIn = false;
    }
  }

  login(): void {
    this.keycloakService.login({ redirectUri: window.location.href });
  }

  logout(): void {
    this.keycloakService.logout(window.location.origin);
  }
}

