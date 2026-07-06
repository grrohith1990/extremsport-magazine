import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ThreadDetailComponent } from './thread-detail.component';
import { ActivatedRoute } from '@angular/router';

describe('ThreadDetailComponent', () => {
  let component: ThreadDetailComponent;
  let fixture: ComponentFixture<ThreadDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ThreadDetailComponent],
      providers: [
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => 'thread-123' } } } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ThreadDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should extract thread id from route', () => {
    expect(component.threadId).toBe('thread-123');
  });

  it('should render thread id', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('thread-123');
  });
});

