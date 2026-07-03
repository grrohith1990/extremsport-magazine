import {
  RouterLink,
  RouterModule
} from "./chunk-3WQAWOKT.js";
import {
  CommonModule,
  ɵsetClassDebugInfo,
  ɵɵStandaloneFeature,
  ɵɵdefineComponent,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵtext
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/forum/pages/forum-overview/forum-overview.component.ts
var ForumOverviewComponent = class _ForumOverviewComponent {
  static {
    this.\u0275fac = function ForumOverviewComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ForumOverviewComponent)();
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ForumOverviewComponent, selectors: [["app-forum-overview"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 10, vars: 0, consts: [[1, "forum"], ["routerLink", "new-thread", 1, "btn-new"], [1, "thread-list"]], template: function ForumOverviewComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Community Forum");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "p");
        \u0275\u0275text(4, "Diskutiere mit anderen Extremsport-Enthusiasten");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(5, "a", 1);
        \u0275\u0275text(6, "Neues Thema erstellen");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(7, "div", 2)(8, "p");
        \u0275\u0275text(9, "Forum-Threads werden hier angezeigt...");
        \u0275\u0275elementEnd()()();
      }
    }, dependencies: [CommonModule, RouterModule, RouterLink], encapsulation: 2 });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ForumOverviewComponent, { className: "ForumOverviewComponent" });
})();
export {
  ForumOverviewComponent
};
//# sourceMappingURL=chunk-WZHLKYFV.js.map
