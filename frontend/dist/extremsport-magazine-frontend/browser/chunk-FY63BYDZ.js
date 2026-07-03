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
  ɵɵclassMap,
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

// src/app/features/author/pages/author-dashboard/author-dashboard.component.ts
var _c0 = (a0) => ["edit", a0];
function AuthorDashboardComponent_tr_21_Template(rf, ctx) {
  if (rf & 1) {
    \u0275\u0275elementStart(0, "tr")(1, "td");
    \u0275\u0275text(2);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(3, "td")(4, "span", 4);
    \u0275\u0275text(5);
    \u0275\u0275elementEnd()();
    \u0275\u0275elementStart(6, "td");
    \u0275\u0275text(7);
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(8, "td");
    \u0275\u0275text(9);
    \u0275\u0275pipe(10, "date");
    \u0275\u0275elementEnd();
    \u0275\u0275elementStart(11, "td")(12, "a", 5);
    \u0275\u0275text(13, "Bearbeiten");
    \u0275\u0275elementEnd()()();
  }
  if (rf & 2) {
    const article_r1 = ctx.$implicit;
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(article_r1.title);
    \u0275\u0275advance(2);
    \u0275\u0275classMap(article_r1.status.toLowerCase());
    \u0275\u0275advance();
    \u0275\u0275textInterpolate(article_r1.status);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(article_r1.accessType);
    \u0275\u0275advance(2);
    \u0275\u0275textInterpolate(\u0275\u0275pipeBind2(10, 7, article_r1.createdAt, "dd.MM.yyyy"));
    \u0275\u0275advance(3);
    \u0275\u0275property("routerLink", \u0275\u0275pureFunction1(10, _c0, article_r1.id));
  }
}
var AuthorDashboardComponent = class _AuthorDashboardComponent {
  constructor(articleService) {
    this.articleService = articleService;
    this.myArticles = [];
  }
  ngOnInit() {
    const authorId = "current-user-id";
    this.articleService.getArticlesByAuthor(authorId).subscribe((articles) => {
      this.myArticles = articles;
    });
  }
  static {
    this.\u0275fac = function AuthorDashboardComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _AuthorDashboardComponent)(\u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _AuthorDashboardComponent, selectors: [["app-author-dashboard"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 22, vars: 1, consts: [[1, "author-dashboard"], ["routerLink", "new-article", 1, "btn-new"], [1, "articles-table"], [4, "ngFor", "ngForOf"], [1, "status"], [3, "routerLink"]], template: function AuthorDashboardComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Autorenbereich");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "a", 1);
        \u0275\u0275text(4, "Neuen Artikel erstellen");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(5, "h2");
        \u0275\u0275text(6, "Meine Artikel");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(7, "table", 2)(8, "thead")(9, "tr")(10, "th");
        \u0275\u0275text(11, "Titel");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(12, "th");
        \u0275\u0275text(13, "Status");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(14, "th");
        \u0275\u0275text(15, "Zugang");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(16, "th");
        \u0275\u0275text(17, "Erstellt");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(18, "th");
        \u0275\u0275text(19, "Aktionen");
        \u0275\u0275elementEnd()()();
        \u0275\u0275elementStart(20, "tbody");
        \u0275\u0275template(21, AuthorDashboardComponent_tr_21_Template, 14, 12, "tr", 3);
        \u0275\u0275elementEnd()()();
      }
      if (rf & 2) {
        \u0275\u0275advance(21);
        \u0275\u0275property("ngForOf", ctx.myArticles);
      }
    }, dependencies: [CommonModule, NgForOf, DatePipe, RouterModule, RouterLink], styles: ["\n\n.articles-table[_ngcontent-%COMP%] {\n  width: 100%;\n  border-collapse: collapse;\n  margin-top: 1rem;\n}\n.articles-table[_ngcontent-%COMP%]   th[_ngcontent-%COMP%], \n.articles-table[_ngcontent-%COMP%]   td[_ngcontent-%COMP%] {\n  padding: 0.75rem;\n  border-bottom: 1px solid #eee;\n  text-align: left;\n}\n.status[_ngcontent-%COMP%] {\n  padding: 2px 8px;\n  border-radius: 4px;\n  font-size: 0.8rem;\n}\n.status.draft[_ngcontent-%COMP%] {\n  background: #fff3e0;\n}\n.status.published[_ngcontent-%COMP%] {\n  background: #e8f5e9;\n}\n.status.archived[_ngcontent-%COMP%] {\n  background: #eceff1;\n}\n.btn-new[_ngcontent-%COMP%] {\n  display: inline-block;\n  margin-bottom: 2rem;\n  padding: 0.75rem 1.5rem;\n  background: #e65100;\n  color: white;\n  border-radius: 6px;\n  text-decoration: none;\n}\n/*# sourceMappingURL=author-dashboard.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(AuthorDashboardComponent, { className: "AuthorDashboardComponent" });
})();
export {
  AuthorDashboardComponent
};
//# sourceMappingURL=chunk-FY63BYDZ.js.map
