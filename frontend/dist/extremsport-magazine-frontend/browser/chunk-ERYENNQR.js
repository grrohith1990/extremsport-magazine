import {
  ArticleService
} from "./chunk-FCC2N2KL.js";
import {
  RouterLink,
  RouterModule
} from "./chunk-3WQAWOKT.js";
import {
  CommonModule,
  DatePipe,
  NgForOf,
  ɵsetClassDebugInfo,
  ɵɵStandaloneFeature,
  ɵɵadvance,
  ɵɵdefineComponent,
  ɵɵdirectiveInject,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵpipe,
  ɵɵpipeBind2,
  ɵɵproperty,
  ɵɵpureFunction1,
  ɵɵtemplate,
  ɵɵtext,
  ɵɵtextInterpolate
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/articles/pages/article-archive/article-archive.component.ts
var _c0 = (a0) => ["/article", a0];
function ArticleArchiveComponent_article_4_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "article", 3)(1, "time");
    \u0275\u0275text(2);
    \u0275\u0275pipe(3, "date");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(4, "h3")(5, "a", 4);
    \u0275\u0275text(6);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(7, "span", 5);
    \u0275\u0275text(8);
    \u0275\u0275elementEnd()();
  }
  if (rf & 2) {
    const article_r1 = ctx.$implicit;
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(\u0275\u0275pipeBind2(3, 4, article_r1.publishedAt, "dd.MM.yyyy"));
    \u0275\u0275advance(3);
    \u0275\u0275property("routerLink", \u0275\u0275pureFunction1(7, _c0, article_r1.id));
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(article_r1.title);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(article_r1.category);
  }
}
var ArticleArchiveComponent = class _ArticleArchiveComponent {
  constructor(articleService) {
    this.articleService = articleService;
    this.articles = [];
  }
  ngOnInit() {
    this.articleService.getArchivedArticles().subscribe((articles) => {
      this.articles = articles;
    });
  }
  static {
    this.\u0275fac = function ArticleArchiveComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ArticleArchiveComponent)(\u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ArticleArchiveComponent, selectors: [["app-article-archive"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 5, vars: 1, consts: [[1, "archive"], [1, "archive-list"], ["class", "archive-item", 4, "ngFor", "ngForOf"], [1, "archive-item"], [3, "routerLink"], [1, "category"]], template: function ArticleArchiveComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Archiv");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "div", 1);
        \u0275\u0275template(4, ArticleArchiveComponent_article_4_Template, 9, 9, "article", 2);
        \u0275\u0275elementEnd()();
      }
      if (rf & 2) {
        \u0275\u0275advance(4);
        \u0275\u0275property("ngForOf", ctx.articles);
      }
    }, dependencies: [CommonModule, NgForOf, DatePipe, RouterModule, RouterLink], encapsulation: 2 });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ArticleArchiveComponent, { className: "ArticleArchiveComponent" });
})();
export {
  ArticleArchiveComponent
};
//# sourceMappingURL=chunk-ERYENNQR.js.map
