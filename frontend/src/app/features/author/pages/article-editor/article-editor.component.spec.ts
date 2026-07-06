import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArticleEditorComponent } from './article-editor.component';
import { ArticleService } from '@core/services/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';

describe('ArticleEditorComponent', () => {
  let component: ArticleEditorComponent;
  let fixture: ComponentFixture<ArticleEditorComponent>;
  let articleServiceMock: jasmine.SpyObj<ArticleService>;
  let routerMock: jasmine.SpyObj<Router>;

  describe('create mode', () => {
    beforeEach(async () => {
      articleServiceMock = jasmine.createSpyObj('ArticleService', ['createArticle', 'updateArticle', 'getArticleById', 'publishArticle']);
      articleServiceMock.createArticle.and.returnValue(of({} as any));
      routerMock = jasmine.createSpyObj('Router', ['navigate']);

      await TestBed.configureTestingModule({
        imports: [ArticleEditorComponent],
        providers: [
          { provide: ArticleService, useValue: articleServiceMock },
          { provide: Router, useValue: routerMock },
          { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => null } } } }
        ]
      }).compileComponents();

      fixture = TestBed.createComponent(ArticleEditorComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should be in create mode when no id in route', () => {
      expect(component.isEdit).toBeFalse();
    });

    it('should call createArticle on save in create mode', () => {
      component.article = { title: 'New Article', content: 'Content' };
      component.tagsInput = 'tag1, tag2';
      component.save();

      expect(articleServiceMock.createArticle).toHaveBeenCalled();
      expect(routerMock.navigate).toHaveBeenCalledWith(['/author']);
    });

    it('should parse tags from comma-separated input', () => {
      component.tagsInput = 'tag1, tag2, tag3';
      component.save();

      expect(component.article.tags).toEqual(['tag1', 'tag2', 'tag3']);
    });
  });

  describe('edit mode', () => {
    const mockArticle = {
      id: '1',
      title: 'Existing Article',
      content: 'Content',
      tags: ['tag1', 'tag2'],
      accessType: 'PUBLIC'
    };

    beforeEach(async () => {
      articleServiceMock = jasmine.createSpyObj('ArticleService', ['createArticle', 'updateArticle', 'getArticleById', 'publishArticle']);
      articleServiceMock.getArticleById.and.returnValue(of(mockArticle as any));
      articleServiceMock.updateArticle.and.returnValue(of({} as any));
      articleServiceMock.publishArticle.and.returnValue(of(undefined as any));
      routerMock = jasmine.createSpyObj('Router', ['navigate']);

      await TestBed.configureTestingModule({
        imports: [ArticleEditorComponent],
        providers: [
          { provide: ArticleService, useValue: articleServiceMock },
          { provide: Router, useValue: routerMock },
          { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => '1' } } } }
        ]
      }).compileComponents();

      fixture = TestBed.createComponent(ArticleEditorComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

    it('should be in edit mode when id in route', () => {
      expect(component.isEdit).toBeTrue();
    });

    it('should load article data', () => {
      expect(articleServiceMock.getArticleById).toHaveBeenCalledWith('1');
      expect(component.article.title).toBe('Existing Article');
      expect(component.tagsInput).toBe('tag1, tag2');
    });

    it('should call updateArticle on save in edit mode', () => {
      component.save();

      expect(articleServiceMock.updateArticle).toHaveBeenCalledWith('1', jasmine.any(Object));
      expect(routerMock.navigate).toHaveBeenCalledWith(['/author']);
    });

    it('should publish article', () => {
      component.publish();

      expect(articleServiceMock.publishArticle).toHaveBeenCalledWith('1');
      expect(routerMock.navigate).toHaveBeenCalledWith(['/author']);
    });
  });
});

