import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ForumOverviewComponent } from './forum-overview.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('ForumOverviewComponent', () => {
  let component: ForumOverviewComponent;
  let fixture: ComponentFixture<ForumOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForumOverviewComponent, RouterTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(ForumOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the forum title', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Community Forum');
  });

  it('should have a link to create new thread', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const link = compiled.querySelector('a.btn-new');
    expect(link?.textContent).toContain('Neues Thema erstellen');
  });
});

