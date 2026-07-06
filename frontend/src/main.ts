import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { APP_INITIALIZER, Provider } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { AppComponent } from './app/app.component';
import { routes } from './app/app.routes';
import { environment } from './environments/environment';

function initializeKeycloak(keycloak: KeycloakService) {
  return async () => {
    // First check if Keycloak is reachable before attempting init
    try {
      const controller = new AbortController();
      const timeout = setTimeout(() => controller.abort(), 3000);
      await fetch(
        `${environment.keycloak.url}/realms/${environment.keycloak.realm}`,
        { signal: controller.signal }
      );
      clearTimeout(timeout);
    } catch (err) {
      console.warn('Keycloak server not reachable, running without authentication.');
      return false;
    }

    // Keycloak is reachable, proceed with init
    try {
      return await keycloak.init({
        config: {
          url: environment.keycloak.url,
          realm: environment.keycloak.realm,
          clientId: environment.keycloak.clientId
        },
        initOptions: {
          onLoad: 'check-sso',
          checkLoginIframe: false,
          enableLogging: !environment.production
        },
        enableBearerInterceptor: true,
        bearerPrefix: 'Bearer'
      });
    } catch (err) {
      console.warn('Keycloak init failed:', err);
      return false;
    }
  };
}

const keycloakProvider: Provider = {
  provide: APP_INITIALIZER,
  useFactory: initializeKeycloak,
  multi: true,
  deps: [KeycloakService]
};

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimations(),
    KeycloakService,
    keycloakProvider
  ]
}).catch(err => console.error(err));






