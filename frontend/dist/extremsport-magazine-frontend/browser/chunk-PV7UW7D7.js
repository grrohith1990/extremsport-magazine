import {
  DefaultValueAccessor,
  FormsModule,
  NgControlStatus,
  NgModel
} from "./chunk-YNG4O4AC.js";
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
  ɵɵclassProp,
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
  ɵɵtextInterpolate,
  ɵɵtextInterpolate1,
  ɵɵtwoWayBindingSet,
  ɵɵtwoWayListener,
  ɵɵtwoWayProperty
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/articles/pages/article-search/article-search.component.ts
var _c0 = (a0) => ["/article", a0];
function ArticleSearchComponent_div_7_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div", 8)(1, "span");
    \u0275\u0275text(2);
    \u0275\u0275elementEnd()();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate1("", ctx_r0.displayedArticles.length, " Artikel gefunden");
  }
}
function ArticleSearchComponent_div_8_article_1_div_1_Template(rf, ctx) {
  if (rf & 1) {
    const _r2 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "div", 25)(1, "img", 26);
    \u0275\u0275listener("error", function ArticleSearchComponent_div_8_article_1_div_1_Template_img_error_1_listener($event) {
      \u0275\u0275restoreView(_r2);
      const ctx_r0 = \u0275\u0275nextContext(3);
      return \u0275\u0275resetView(ctx_r0.onImageError($event));
    });
    \u0275\u0275elementEnd()();
  }
  if (rf & 2) {
    const article_r3 = \u0275\u0275nextContext().$implicit;
    \u0275\u0275advance();
    \u0275\u0275property("src", article_r3.coverImageUrl, \u0275\u0275sanitizeUrl)("alt", article_r3.title);
  }
}
function ArticleSearchComponent_div_8_article_1_p_11_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 27);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const article_r3 = \u0275\u0275nextContext().$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(article_r3.subtitle);
  }
}
function ArticleSearchComponent_div_8_article_1_span_15_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "span", 28);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const article_r3 = \u0275\u0275nextContext().$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1("Von ", article_r3.authorName, "");
  }
}
function ArticleSearchComponent_div_8_article_1_time_16_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "time");
    \u0275\u0275text(1);
    \u0275\u0275pipe(2, "date");
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const article_r3 = \u0275\u0275nextContext().$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(\u0275\u0275pipeBind2(2, 1, article_r3.createdAt, "dd.MM.yyyy"));
  }
}
function ArticleSearchComponent_div_8_article_1_span_18_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "span", 29);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const tag_r4 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1("#", tag_r4, "");
  }
}
function ArticleSearchComponent_div_8_article_1_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "article", 11);
    \u0275\u0275template(1, ArticleSearchComponent_div_8_article_1_div_1_Template, 2, 2, "div", 12);
    \u0275\u0275elementStart(2, "div", 13)(3, "div", 14)(4, "span", 15);
    \u0275\u0275text(5);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(6, "span", 16);
    \u0275\u0275text(7);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(8, "h3")(9, "a", 17);
    \u0275\u0275text(10);
    \u0275\u0275elementEnd()();
    \u0275\u0275template(11, ArticleSearchComponent_div_8_article_1_p_11_Template, 2, 1, "p", 18);
    \u0275\u0275elementStart(12, "p", 19);
    \u0275\u0275text(13);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(14, "div", 20);
    \u0275\u0275template(15, ArticleSearchComponent_div_8_article_1_span_15_Template, 2, 1, "span", 21)(16, ArticleSearchComponent_div_8_article_1_time_16_Template, 3, 4, "time", 22);
    \u0275\u0275elementStart(17, "div", 23);
    \u0275\u0275template(18, ArticleSearchComponent_div_8_article_1_span_18_Template, 2, 1, "span", 24);
    \u0275\u0275elementEnd()()()();
  }
  if (rf & 2) {
    const article_r3 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", article_r3.coverImageUrl);
    \u0275\u0275advance(4);
    \u0275\u0275textInterpolate(article_r3.category);
    \u0275\u0275advance();
    \u0275\u0275classProp("premium", article_r3.accessType !== "PUBLIC");
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(" ", article_r3.accessType === "PUBLIC" ? "Kostenlos" : "Premium", " ");
    \u0275\u0275advance(2);
    \u0275\u0275property("routerLink", \u0275\u0275pureFunction1(12, _c0, article_r3.id));
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(article_r3.title);
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", article_r3.subtitle);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(article_r3.summary);
    \u0275\u0275advance(2);
    \u0275\u0275property("ngIf", article_r3.authorName);
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", article_r3.createdAt);
    \u0275\u0275advance(2);
    \u0275\u0275property("ngForOf", article_r3.tags);
  }
}
function ArticleSearchComponent_div_8_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div", 9);
    \u0275\u0275template(1, ArticleSearchComponent_div_8_article_1_Template, 19, 14, "article", 10);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275property("ngForOf", ctx_r0.displayedArticles);
  }
}
function ArticleSearchComponent_p_9_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 30);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1(' Keine Artikel gefunden f\xFCr "', ctx_r0.query, '". ');
  }
}
function ArticleSearchComponent_button_10_Template(rf, ctx) {
  if (rf & 1) {
    const _r5 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "button", 31);
    \u0275\u0275listener("click", function ArticleSearchComponent_button_10_Template_button_click_0_listener() {
      \u0275\u0275restoreView(_r5);
      const ctx_r0 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r0.loadMore());
    });
    \u0275\u0275text(1, "Mehr Artikel laden");
    \u0275\u0275elementEnd();
  }
}
var ArticleSearchComponent = class _ArticleSearchComponent {
  constructor(articleService) {
    this.articleService = articleService;
    this.query = "";
    this.allArticles = [];
    this.displayedArticles = [];
    this.searched = false;
    this.hasMore = true;
    this.page = 0;
  }
  ngOnInit() {
    this.loadArticles();
  }
  search() {
    if (this.query.trim()) {
      this.articleService.searchArticles(this.query).subscribe((articles) => {
        this.displayedArticles = articles;
        this.searched = true;
        this.hasMore = false;
      });
    } else {
      this.displayedArticles = this.allArticles;
      this.searched = false;
      this.hasMore = true;
    }
  }
  onInputChange() {
    if (!this.query.trim()) {
      this.displayedArticles = this.allArticles;
      this.searched = false;
      this.hasMore = true;
    }
  }
  loadMore() {
    this.page++;
    this.loadArticles();
  }
  onImageError(event) {
    event.target.src = "https://placehold.co/400x280/1a1a2e/e65100?text=Extremsport";
  }
  loadArticles() {
    this.articleService.getPublicArticles(this.page).subscribe((articles) => {
      this.allArticles = [...this.allArticles, ...articles];
      this.displayedArticles = this.allArticles;
      if (articles.length < 20) {
        this.hasMore = false;
      }
    });
  }
  static {
    this.\u0275fac = function ArticleSearchComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ArticleSearchComponent)(\u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ArticleSearchComponent, selectors: [["app-article-search"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 11, vars: 5, consts: [[1, "search-page"], [1, "search-bar"], ["type", "text", "placeholder", "Artikel durchsuchen...", 3, "ngModelChange", "keyup.enter", "input", "ngModel"], [3, "click"], ["class", "article-count", 4, "ngIf"], ["class", "results", 4, "ngIf"], ["class", "no-results", 4, "ngIf"], ["class", "load-more", 3, "click", 4, "ngIf"], [1, "article-count"], [1, "results"], ["class", "result-item", 4, "ngFor", "ngForOf"], [1, "result-item"], ["class", "result-image", 4, "ngIf"], [1, "result-content"], [1, "result-meta"], [1, "category"], [1, "access-badge"], [3, "routerLink"], ["class", "subtitle", 4, "ngIf"], [1, "summary"], [1, "result-footer"], ["class", "author", 4, "ngIf"], [4, "ngIf"], [1, "tags"], ["class", "tag", 4, "ngFor", "ngForOf"], [1, "result-image"], [3, "error", "src", "alt"], [1, "subtitle"], [1, "author"], [1, "tag"], [1, "no-results"], [1, "load-more", 3, "click"]], template: function ArticleSearchComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Alle Artikel");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "div", 1)(4, "input", 2);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleSearchComponent_Template_input_ngModelChange_4_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.query, $event) || (ctx.query = $event);
          return $event;
        });
        \u0275\u0275listener("keyup.enter", function ArticleSearchComponent_Template_input_keyup_enter_4_listener() {
          return ctx.search();
        })("input", function ArticleSearchComponent_Template_input_input_4_listener() {
          return ctx.onInputChange();
        });
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(5, "button", 3);
        \u0275\u0275listener("click", function ArticleSearchComponent_Template_button_click_5_listener() {
          return ctx.search();
        });
        \u0275\u0275text(6, "Suchen");
        \u0275\u0275elementEnd()();
        \u0275\u0275template(7, ArticleSearchComponent_div_7_Template, 3, 1, "div", 4)(8, ArticleSearchComponent_div_8_Template, 2, 1, "div", 5)(9, ArticleSearchComponent_p_9_Template, 2, 1, "p", 6)(10, ArticleSearchComponent_button_10_Template, 2, 0, "button", 7);
        \u0275\u0275elementEnd();
      }
      if (rf & 2) {
        \u0275\u0275advance(4);
        \u0275\u0275twoWayProperty("ngModel", ctx.query);
        \u0275\u0275advance(3);
        \u0275\u0275property("ngIf", ctx.displayedArticles.length > 0);
        \u0275\u0275advance();
        \u0275\u0275property("ngIf", ctx.displayedArticles.length > 0);
        \u0275\u0275advance();
        \u0275\u0275property("ngIf", ctx.searched && ctx.displayedArticles.length === 0);
        \u0275\u0275advance();
        \u0275\u0275property("ngIf", ctx.hasMore);
      }
    }, dependencies: [CommonModule, NgForOf, NgIf, DatePipe, FormsModule, DefaultValueAccessor, NgControlStatus, NgModel, RouterModule, RouterLink], styles: ["\n\n.search-page[_ngcontent-%COMP%] {\n  max-width: 900px;\n  margin: 0 auto;\n}\n.search-bar[_ngcontent-%COMP%] {\n  display: flex;\n  gap: 0.75rem;\n  margin-bottom: 1.5rem;\n}\n.search-bar[_ngcontent-%COMP%]   input[_ngcontent-%COMP%] {\n  flex: 1;\n  padding: 0.75rem 1rem;\n  border: 2px solid #ddd;\n  border-radius: 8px;\n  font-size: 1rem;\n}\n.search-bar[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]:focus {\n  border-color: #e65100;\n  outline: none;\n}\n.search-bar[_ngcontent-%COMP%]   button[_ngcontent-%COMP%] {\n  padding: 0.75rem 1.5rem;\n}\n.article-count[_ngcontent-%COMP%] {\n  color: #666;\n  margin-bottom: 1rem;\n  font-size: 0.9rem;\n}\n.results[_ngcontent-%COMP%] {\n  display: flex;\n  flex-direction: column;\n  gap: 1.5rem;\n}\n.result-item[_ngcontent-%COMP%] {\n  display: flex;\n  gap: 1.5rem;\n  padding: 1.5rem;\n  border: 1px solid #eee;\n  border-radius: 12px;\n  transition: box-shadow 0.2s;\n}\n.result-item[_ngcontent-%COMP%]:hover {\n  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);\n}\n.result-image[_ngcontent-%COMP%] {\n  flex-shrink: 0;\n  width: 200px;\n  height: 140px;\n  border-radius: 8px;\n  overflow: hidden;\n}\n.result-image[_ngcontent-%COMP%]   img[_ngcontent-%COMP%] {\n  width: 100%;\n  height: 100%;\n  object-fit: cover;\n}\n.result-content[_ngcontent-%COMP%] {\n  flex: 1;\n}\n.result-meta[_ngcontent-%COMP%] {\n  display: flex;\n  gap: 0.75rem;\n  align-items: center;\n  margin-bottom: 0.5rem;\n}\n.category[_ngcontent-%COMP%] {\n  color: #e65100;\n  font-weight: 600;\n  text-transform: uppercase;\n  font-size: 0.75rem;\n  letter-spacing: 0.5px;\n}\n.access-badge[_ngcontent-%COMP%] {\n  font-size: 0.7rem;\n  padding: 2px 8px;\n  border-radius: 4px;\n  background: #e8f5e9;\n  color: #2e7d32;\n  font-weight: 600;\n}\n.access-badge.premium[_ngcontent-%COMP%] {\n  background: #fff8e1;\n  color: #f57f17;\n}\n.result-content[_ngcontent-%COMP%]   h3[_ngcontent-%COMP%] {\n  margin: 0.25rem 0;\n}\n.result-content[_ngcontent-%COMP%]   h3[_ngcontent-%COMP%]   a[_ngcontent-%COMP%] {\n  color: #1a1a2e;\n  text-decoration: none;\n}\n.result-content[_ngcontent-%COMP%]   h3[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]:hover {\n  color: #e65100;\n}\n.subtitle[_ngcontent-%COMP%] {\n  color: #555;\n  font-style: italic;\n  margin: 0.25rem 0;\n}\n.summary[_ngcontent-%COMP%] {\n  color: #666;\n  font-size: 0.9rem;\n  line-height: 1.5;\n}\n.result-footer[_ngcontent-%COMP%] {\n  display: flex;\n  flex-wrap: wrap;\n  gap: 0.75rem;\n  align-items: center;\n  margin-top: 0.75rem;\n  color: #888;\n  font-size: 0.8rem;\n}\n.tags[_ngcontent-%COMP%] {\n  display: flex;\n  gap: 0.4rem;\n  flex-wrap: wrap;\n}\n.tag[_ngcontent-%COMP%] {\n  background: #f5f5f5;\n  padding: 2px 6px;\n  border-radius: 4px;\n  font-size: 0.75rem;\n  color: #666;\n}\n.no-results[_ngcontent-%COMP%] {\n  text-align: center;\n  color: #999;\n  padding: 2rem;\n}\n.load-more[_ngcontent-%COMP%] {\n  display: block;\n  margin: 2rem auto;\n  padding: 0.75rem 2rem;\n}\n@media (max-width: 600px) {\n  .result-item[_ngcontent-%COMP%] {\n    flex-direction: column;\n  }\n  .result-image[_ngcontent-%COMP%] {\n    width: 100%;\n    height: 180px;\n  }\n}\n/*# sourceMappingURL=article-search.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ArticleSearchComponent, { className: "ArticleSearchComponent" });
})();
export {
  ArticleSearchComponent
};
//# sourceMappingURL=chunk-PV7UW7D7.js.map
