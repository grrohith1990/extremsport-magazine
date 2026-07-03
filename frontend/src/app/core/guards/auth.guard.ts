import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

/**
 * Auth Guard - protects routes that require authentication.
 * Integrates with Keycloak for OAuth2/OIDC authentication.
 *
 * KEY AGILITY POINT: Uses Keycloak Angular adapter.
 * If auth system changes, only this guard and the Keycloak config need updating.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private keycloakService: KeycloakService,
    private router: Router
  ) {}

  async canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    const isLoggedIn = await this.keycloakService.isLoggedIn();

    if (!isLoggedIn) {
      await this.keycloakService.login({
        redirectUri: window.location.origin + route.url.toString()
      });
      return false;
    }

    // Check required roles
    const requiredRoles = route.data['roles'] as string[];
    if (requiredRoles && requiredRoles.length > 0) {
      const userRoles = await this.keycloakService.getUserRoles();
      const hasRole = requiredRoles.some(role => userRoles.includes(role));
      if (!hasRole) {
        this.router.navigate(['/unauthorized']);
        return false;
      }
    }

    return true;
  }
}

