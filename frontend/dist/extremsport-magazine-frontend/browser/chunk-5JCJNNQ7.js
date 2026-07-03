import {
  ArticleService
} from "./chunk-FCC2N2KL.js";
import {
  ActivatedRoute,
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
  ɵɵelement,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵgetCurrentView,
  ɵɵlistener,
  ɵɵnextContext,
  ɵɵpipe,
  ɵɵpipeBind2,
  ɵɵproperty,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵsanitizeHtml,
  ɵɵsanitizeUrl,
  ɵɵtemplate,
  ɵɵtext,
  ɵɵtextInterpolate,
  ɵɵtextInterpolate1
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/articles/pages/article-detail/article-detail.component.ts
function ArticleDetailComponent_article_0_span_4_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "span", 11);
    \u0275\u0275text(1, "Premium");
    \u0275\u0275elementEnd();
  }
}
function ArticleDetailComponent_article_0_p_7_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "p", 12);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext(2);
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(ctx_r0.article.subtitle);
  }
}
function ArticleDetailComponent_article_0_img_14_Template(rf, ctx) {
  if (rf & 1) {
    const _r2 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "img", 13);
    \u0275\u0275listener("error", function ArticleDetailComponent_article_0_img_14_Template_img_error_0_listener($event) {
      \u0275\u0275restoreView(_r2);
      const ctx_r0 = \u0275\u0275nextContext(2);
      return \u0275\u0275resetView(ctx_r0.onImageError($event));
    });
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext(2);
    \u0275\u0275property("src", ctx_r0.article.coverImageUrl, \u0275\u0275sanitizeUrl)("alt", ctx_r0.article.title);
  }
}
function ArticleDetailComponent_article_0_span_18_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "span", 14);
    \u0275\u0275text(1);
    \u0275\u0275elementEnd();
  }
  if (rf & 2) {
    const tag_r3 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275textInterpolate1("#", tag_r3, "");
  }
}
function ArticleDetailComponent_article_0_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "article", 2)(1, "header")(2, "span", 3);
    \u0275\u0275text(3);
    \u0275\u0275elementEnd();
    \u0275\u0275template(4, ArticleDetailComponent_article_0_span_4_Template, 2, 0, "span", 4);
    \u0275\u0275elementStart(5, "h1");
    \u0275\u0275text(6);
    \u0275\u0275elementEnd();
    \u0275\u0275template(7, ArticleDetailComponent_article_0_p_7_Template, 2, 1, "p", 5);
    \u0275\u0275elementStart(8, "div", 6)(9, "span");
    \u0275\u0275text(10);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(11, "time");
    \u0275\u0275text(12);
    \u0275\u0275pipe(13, "date");
    \u0275\u0275elementEnd()()();
    \u0275\u0275template(14, ArticleDetailComponent_article_0_img_14_Template, 1, 2, "img", 7);
    \u0275\u0275element(15, "div", 8);
    \u0275\u0275elementStart(16, "footer")(17, "div", 9);
    \u0275\u0275template(18, ArticleDetailComponent_article_0_span_18_Template, 2, 1, "span", 10);
    \u0275\u0275elementEnd()()();
  }
  if (rf & 2) {
    const ctx_r0 = \u0275\u0275nextContext();
    \u0275\u0275advance(3);
    \u0275\u0275textInterpolate(ctx_r0.article.category);
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", ctx_r0.article.accessType !== "PUBLIC");
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(ctx_r0.article.title);
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", ctx_r0.article.subtitle);
    \u0275\u0275advance(3);
    \u0275\u0275textInterpolate1("Von ", ctx_r0.article.authorName, "");
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(\u0275\u0275pipeBind2(13, 9, ctx_r0.article.publishedAt, "dd. MMMM yyyy"));
    \u0275\u0275advance(2);
    \u0275\u0275property("ngIf", ctx_r0.article.coverImageUrl);
    \u0275\u0275advance();
    \u0275\u0275property("innerHTML", ctx_r0.article.content, \u0275\u0275sanitizeHtml);
    \u0275\u0275advance(3);
    \u0275\u0275property("ngForOf", ctx_r0.article.tags);
  }
}
function ArticleDetailComponent_div_1_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "div", 15);
    \u0275\u0275text(1, "Artikel wird geladen...");
    \u0275\u0275elementEnd();
  }
}
var ArticleDetailComponent = class _ArticleDetailComponent {
  constructor(route, articleService) {
    this.route = route;
    this.articleService = articleService;
    this.article = null;
  }
  ngOnInit() {
    const id = this.route.snapshot.paramMap.get("id");
    if (id) {
      this.articleService.getArticleById(id).subscribe((article) => {
        this.article = article;
      });
    }
  }
  onImageError(event) {
    event.target.src = "https://placehold.co/800x400/1a1a2e/e65100?text=Extremsport";
  }
  static {
    this.\u0275fac = function ArticleDetailComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ArticleDetailComponent)(\u0275\u0275directiveInject(ActivatedRoute), \u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ArticleDetailComponent, selectors: [["app-article-detail"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 2, vars: 2, consts: [["class", "article-detail", 4, "ngIf"], ["class", "loading", 4, "ngIf"], [1, "article-detail"], [1, "category"], ["class", "badge premium", 4, "ngIf"], ["class", "subtitle", 4, "ngIf"], [1, "meta"], ["class", "cover", 3, "src", "alt", "error", 4, "ngIf"], [1, "content", 3, "innerHTML"], [1, "tags"], ["class", "tag", 4, "ngFor", "ngForOf"], [1, "badge", "premium"], [1, "subtitle"], [1, "cover", 3, "error", "src", "alt"], [1, "tag"], [1, "loading"]], template: function ArticleDetailComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275template(0, ArticleDetailComponent_article_0_Template, 19, 12, "article", 0)(1, ArticleDetailComponent_div_1_Template, 2, 0, "div", 1);
      }
      if (rf & 2) {
        \u0275\u0275property("ngIf", ctx.article);
        \u0275\u0275advance();
        \u0275\u0275property("ngIf", !ctx.article);
      }
    }, dependencies: [CommonModule, NgForOf, NgIf, DatePipe, RouterModule], styles: ["\n\n.article-detail[_ngcontent-%COMP%] {\n  max-width: 800px;\n  margin: 0 auto;\n  padding: 2rem;\n}\n.cover[_ngcontent-%COMP%] {\n  width: 100%;\n  border-radius: 8px;\n  margin: 1.5rem 0;\n}\n.category[_ngcontent-%COMP%] {\n  color: #e65100;\n  font-weight: bold;\n  text-transform: uppercase;\n}\n.badge.premium[_ngcontent-%COMP%] {\n  background: gold;\n  padding: 2px 8px;\n  border-radius: 4px;\n  margin-left: 1rem;\n}\n.meta[_ngcontent-%COMP%] {\n  color: #666;\n  margin: 1rem 0;\n}\n.tag[_ngcontent-%COMP%] {\n  background: #f0f0f0;\n  padding: 4px 8px;\n  border-radius: 4px;\n  margin-right: 0.5rem;\n}\n/*# sourceMappingURL=article-detail.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ArticleDetailComponent, { className: "ArticleDetailComponent" });
})();
export {
  ArticleDetailComponent
};
//# sourceMappingURL=chunk-5JCJNNQ7.js.map
