import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NewThreadComponent } from './new-thread.component';
import { Router } from '@angular/router';

describe('NewThreadComponent', () => {
  let component: NewThreadComponent;
  let fixture: ComponentFixture<NewThreadComponent>;
  let routerMock: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    routerMock = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      imports: [NewThreadComponent],
      providers: [
        { provide: Router, useValue: routerMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(NewThreadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have empty initial values', () => {
    expect(component.title).toBe('');
    expect(component.content).toBe('');
  });

  it('should navigate to forum after creating thread', () => {
    component.title = 'Test Thread';
    component.content = 'Test Content';
    component.createThread();

    expect(routerMock.navigate).toHaveBeenCalledWith(['/forum']);
  });

  it('should render the form', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Neues Thema erstellen');
    expect(compiled.querySelector('input#title')).toBeTruthy();
    expect(compiled.querySelector('textarea#content')).toBeTruthy();
  });
});

