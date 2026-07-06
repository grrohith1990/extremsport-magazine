import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PortalDashboardComponent } from './portal-dashboard.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('PortalDashboardComponent', () => {
  let component: PortalDashboardComponent;
  let fixture: ComponentFixture<PortalDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortalDashboardComponent, RouterTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(PortalDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render portal dashboard title', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Kundenportal');
  });

  it('should display subscription status', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Aktiv');
  });

  it('should have dashboard cards', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const cards = compiled.querySelectorAll('.card');
    expect(cards.length).toBe(3);
  });
});

