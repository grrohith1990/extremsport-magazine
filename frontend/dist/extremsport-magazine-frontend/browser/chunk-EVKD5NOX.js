import {
  ArticleService
} from "./chunk-FCC2N2KL.js";
import {
  RouterLink,
  RouterModule
} from "./chunk-3WQAWOKT.js";
import {
  CommonModule,
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
  ɵɵnextContext,
  ɵɵproperty,
  ɵɵpureFunction1,
  ɵɵsanitizeUrl,
  ɵɵtemplate,
  ɵɵtext,
  ɵɵtextInterpolate
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/articles/pages/premium-articles/premium-articles.component.ts
var _c0 = (a0) => ["/article", a0];
function PremiumArticlesComponent_article_6_img_1_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275element(0, "img", 9);
  }
  if (rf & 2) {
    const article_r1 = \u0275\u0275nextContext().$implicit;
    \u0275\u0275property("src", article_r1.coverImageUrl, \u0275\u0275sanitizeUrl)("alt", article_r1.title);
  }
}
function PremiumArticlesComponent_article_6_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "article", 4);
    \u0275\u0275template(1, PremiumArticlesComponent_article_6_img_1_Template, 1, 2, "img", 5);
    \u0275\u0275elementStart(2, "div", 6)(3, "span", 7);
    \u0275\u0275text(4, "Premium");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(5, "h2")(6, "a", 8);
    \u0275\u0275text(7);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(8, "p");
    \u0275\u0275text(9);
    \u0275\u0275elementEnd()()();
  }
  if (rf & 2) {
    const article_r1 = ctx.$implicit;
    \u0275\u0275advance();
    \u0275\u0275property("ngIf", article_r1.coverImageUrl);
    \u0275\u0275advance(5);
    \u0275\u0275property("routerLink", \u0275\u0275pureFunction1(4, _c0, article_r1.id));
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(article_r1.title);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(article_r1.summary);
  }
}
var PremiumArticlesComponent = class _PremiumArticlesComponent {
  constructor(articleService) {
    this.articleService = articleService;
    this.articles = [];
  }
  ngOnInit() {
    this.articleService.getPremiumArticles().subscribe((articles) => {
      this.articles = articles;
    });
  }
  static {
    this.\u0275fac = function PremiumArticlesComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _PremiumArticlesComponent)(\u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _PremiumArticlesComponent, selectors: [["app-premium-articles"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 7, vars: 1, consts: [[1, "premium"], [1, "intro"], [1, "articles-grid"], ["class", "article-card premium-card", 4, "ngFor", "ngForOf"], [1, "article-card", "premium-card"], [3, "src", "alt", 4, "ngIf"], [1, "article-card-content"], [1, "badge"], [3, "routerLink"], [3, "src", "alt"]], template: function PremiumArticlesComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Premium Inhalte");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "p", 1);
        \u0275\u0275text(4, "Exklusive Artikel f\xFCr Abonnenten");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(5, "div", 2);
        \u0275\u0275template(6, PremiumArticlesComponent_article_6_Template, 10, 6, "article", 3);
        \u0275\u0275elementEnd()();
      }
      if (rf & 2) {
        \u0275\u0275advance(6);
        \u0275\u0275property("ngForOf", ctx.articles);
      }
    }, dependencies: [CommonModule, NgForOf, NgIf, RouterModule, RouterLink], styles: ["\n\n.articles-grid[_ngcontent-%COMP%] {\n  display: grid;\n  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));\n  gap: 1.5rem;\n}\n.premium-card[_ngcontent-%COMP%] {\n  border: 2px solid gold;\n  border-radius: 8px;\n  overflow: hidden;\n}\n.badge[_ngcontent-%COMP%] {\n  background: gold;\n  padding: 2px 8px;\n  border-radius: 4px;\n  font-weight: bold;\n}\n/*# sourceMappingURL=premium-articles.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(PremiumArticlesComponent, { className: "PremiumArticlesComponent" });
})();
export {
  PremiumArticlesComponent
};
//# sourceMappingURL=chunk-EVKD5NOX.js.map
