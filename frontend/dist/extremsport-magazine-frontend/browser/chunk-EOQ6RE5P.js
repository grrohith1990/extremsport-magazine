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
  NgIf,
  ɵsetClassDebugInfo,
  ɵɵStandaloneFeature,
  ɵɵadvance,
  ɵɵdefineComponent,
  ɵɵdirectiveInject,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵgetCurrentView,
  ɵɵlistener,
  ɵɵnextContext,
  ɵɵpipe,
  ɵɵpipeBind2,
  ɵɵproperty,
  ɵɵpureFunction1,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵsanitizeUrl,
  ɵɵtemplate,
  ɵɵtext,
  ɵɵtextInterpolate
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/articles/pages/article-list/article-list.component.ts
var _c0 = (a0) => ["/article", a0];
function ArticleListComponent_article_4_img_1_Template(rf, ctx) {
  if (rf & 1) {
    const _r1 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "img", 10);
    \u0275\u0275listener("error", function ArticleListComponent_article_4_img_1_Template_img_error_0_listener($event) {
      \u0275\u0275restoreView(_r1);
      const ctx_r1 = \u0275\u0275nextContext(2);
      return \u0275\u0275resetView(ctx_r1.onImageError($event));
    });
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const article_r3 = \u0275\u0275nextContext().$implicit;
    \u0275\u0275property("src", article_r3.coverImageUrl, \u0275\u0275sanitizeUrl)("alt", article_r3.title);
  }
}
function ArticleListComponent_article_4_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "article", 4);
    \u0275\u0275template(1, ArticleListComponent_article_4_img_1_Template, 1, 2, "img", 5);
    \u0275\u0275elementStart(2, "div", 6)(3, "span", 7);
    \u0275\u0275text(4);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(5, "h2")(6, "a", 8);
    \u0275\u0275text(7);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(8, "p");
    \u0275\u0275text(9);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(10, "div", 9)(11, "span");
    \u0275\u0275text(12);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(13, "time");
    \u0275\u0275text(14);
    \u0275\u0275pipe(15, "date");
    \u0275\u0275elementEnd()()()();
  }
  if (rf & 2) {
    const article_r3 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", article_r3.coverImageUrl);
    \u0275\u0275advance(3);
    \u0275\u0275textInterpolate(article_r3.category);
    \u0275\u0275advance(2);
    \u0275\u0275property("routerLink", \u0275\u0275pureFunction1(10, _c0, article_r3.id));
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(article_r3.title);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(article_r3.summary);
    \u0275\u0275advance(3);
    \u0275\u0275textInterpolate(article_r3.authorName);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(\u0275\u0275pipeBind2(15, 7, article_r3.publishedAt, "dd.MM.yyyy"));
  }
}
function ArticleListComponent_button_5_Template(rf, ctx) {
  if (rf & 1) {
    const _r4 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "button", 11);
    \u0275\u0275listener("click", function ArticleListComponent_button_5_Template_button_click_0_listener() {
      \u0275\u0275restoreView(_r4);
      const ctx_r1 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r1.loadMore());
    });
    \u0275\u0275text(1, "Mehr laden");
    \u0275\u0275elementEnd();
  }
}
var ArticleListComponent = class _ArticleListComponent {
  constructor(articleService) {
    this.articleService = articleService;
    this.articles = [];
    this.page = 0;
  }
  ngOnInit() {
    this.loadArticles();
  }
  loadMore() {
    this.page++;
    this.loadArticles();
  }
  loadArticles() {
    this.articleService.getPublicArticles(this.page).subscribe((articles) => {
      this.articles = [...this.articles, ...articles];
    });
  }
  onImageError(event) {
    event.target.src = "https://placehold.co/600x400/1a1a2e/e65100?text=Extremsport";
  }
  static {
    this.\u0275fac = function ArticleListComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ArticleListComponent)(\u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ArticleListComponent, selectors: [["app-article-list"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 6, vars: 2, consts: [[1, "article-list"], [1, "articles-grid"], ["class", "article-card", 4, "ngFor", "ngForOf"], [3, "click", 4, "ngIf"], [1, "article-card"], [3, "src", "alt", "error", 4, "ngIf"], [1, "article-card-content"], [1, "category"], [3, "routerLink"], [1, "meta"], [3, "error", "src", "alt"], [3, "click"]], template: function ArticleListComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Extremsport Magazine");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "div", 1);
        \u0275\u0275template(4, ArticleListComponent_article_4_Template, 16, 12, "article", 2);
        \u0275\u0275elementEnd();
        \u0275\u0275template(5, ArticleListComponent_button_5_Template, 2, 0, "button", 3);
        \u0275\u0275elementEnd();
      }
      if (rf & 2) {
        \u0275\u0275advance(4);
        \u0275\u0275property("ngForOf", ctx.articles);
        \u0275\u0275advance();
        \u0275\u0275property("ngIf", ctx.articles.length > 0);
      }
    }, dependencies: [CommonModule, NgForOf, NgIf, DatePipe, RouterModule, RouterLink], styles: ["\n\n.articles-grid[_ngcontent-%COMP%] {\n  display: grid;\n  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));\n  gap: 1.5rem;\n}\n.article-card[_ngcontent-%COMP%] {\n  border: 1px solid #eee;\n  border-radius: 8px;\n  overflow: hidden;\n}\n.article-card[_ngcontent-%COMP%]   img[_ngcontent-%COMP%] {\n  width: 100%;\n  height: 200px;\n  object-fit: cover;\n}\n.article-card-content[_ngcontent-%COMP%] {\n  padding: 1rem;\n}\n.category[_ngcontent-%COMP%] {\n  color: #e65100;\n  font-weight: bold;\n  text-transform: uppercase;\n  font-size: 0.8rem;\n}\n.meta[_ngcontent-%COMP%] {\n  display: flex;\n  justify-content: space-between;\n  color: #666;\n  font-size: 0.85rem;\n  margin-top: 0.5rem;\n}\n/*# sourceMappingURL=article-list.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ArticleListComponent, { className: "ArticleListComponent" });
})();
export {
  ArticleListComponent
};
//# sourceMappingURL=chunk-EOQ6RE5P.js.map
