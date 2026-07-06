import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ManageSubscriptionComponent } from './manage-subscription.component';

describe('ManageSubscriptionComponent', () => {
  let component: ManageSubscriptionComponent;
  let fixture: ComponentFixture<ManageSubscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageSubscriptionComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(ManageSubscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render subscription title', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Abo verwalten');
  });

  it('should display monthly plan', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Monatlich');
    expect(compiled.textContent).toContain('9,99 € / Monat');
  });

  it('should display yearly plan', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Jährlich');
    expect(compiled.textContent).toContain('89,99 € / Jahr');
  });

  it('should have two plan cards', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const planCards = compiled.querySelectorAll('.plan-card');
    expect(planCards.length).toBe(2);
  });
});

