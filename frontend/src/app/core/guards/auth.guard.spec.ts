import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { ActivatedRouteSnapshot } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { AuthGuard } from './auth.guard';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let keycloakServiceMock: jasmine.SpyObj<KeycloakService>;
  let routerMock: jasmine.SpyObj<Router>;

  beforeEach(() => {
    keycloakServiceMock = jasmine.createSpyObj('KeycloakService', [
      'isLoggedIn',
      'login',
      'getUserRoles'
    ]);
    routerMock = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        AuthGuard,
        { provide: KeycloakService, useValue: keycloakServiceMock },
        { provide: Router, useValue: routerMock }
      ]
    });

    guard = TestBed.inject(AuthGuard);
  });

  function createRoute(roles?: string[]): ActivatedRouteSnapshot {
    const route = {
      data: roles ? { roles } : {},
      url: [{ path: 'test-path' }]
    } as unknown as ActivatedRouteSnapshot;
    return route;
  }

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should allow access when user is logged in and no roles required', async () => {
    keycloakServiceMock.isLoggedIn.and.returnValue(await Promise.resolve(true));

    const result = await guard.canActivate(createRoute());

    expect(result).toBeTrue();
  });

  it('should redirect to login when user is not logged in', async () => {
    keycloakServiceMock.isLoggedIn.and.returnValue(await Promise.resolve(false));
    keycloakServiceMock.login.and.returnValue(Promise.resolve());

    const result = await guard.canActivate(createRoute());

    expect(result).toBeFalse();
    expect(keycloakServiceMock.login).toHaveBeenCalled();
  });

  it('should allow access when user has required role', async () => {
    keycloakServiceMock.isLoggedIn.and.returnValue(await Promise.resolve(true));
    keycloakServiceMock.getUserRoles.and.returnValue(await Promise.resolve(['AUTHOR', 'READER']));

    const result = await guard.canActivate(createRoute(['AUTHOR']));

    expect(result).toBeTrue();
  });

  it('should deny access and navigate to unauthorized when user lacks required role', async () => {
    keycloakServiceMock.isLoggedIn.and.returnValue(await Promise.resolve(true));
    keycloakServiceMock.getUserRoles.and.returnValue(await Promise.resolve(['READER']));

    const result = await guard.canActivate(createRoute(['ADMIN']));

    expect(result).toBeFalse();
    expect(routerMock.navigate).toHaveBeenCalledWith(['/unauthorized']);
  });

  it('should allow access when user has at least one of the required roles', async () => {
    keycloakServiceMock.isLoggedIn.and.returnValue(await Promise.resolve(true));
    keycloakServiceMock.getUserRoles.and.returnValue(await Promise.resolve(['EDITOR']));

    const result = await guard.canActivate(createRoute(['ADMIN', 'EDITOR']));

    expect(result).toBeTrue();
  });

  it('should deny access when user has none of the required roles', async () => {
    keycloakServiceMock.isLoggedIn.and.returnValue(await Promise.resolve(true));
    keycloakServiceMock.getUserRoles.and.returnValue(await Promise.resolve(['READER', 'SUBSCRIBER']));

    const result = await guard.canActivate(createRoute(['ADMIN', 'EDITOR']));

    expect(result).toBeFalse();
    expect(routerMock.navigate).toHaveBeenCalledWith(['/unauthorized']);
  });
});

