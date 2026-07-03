import {
  ActivatedRoute
} from "./chunk-3WQAWOKT.js";
import {
  CommonModule,
  ɵsetClassDebugInfo,
  ɵɵStandaloneFeature,
  ɵɵadvance,
  ɵɵdefineComponent,
  ɵɵdirectiveInject,
  ɵɵelementEnd,
  ɵɵelementStart,
  ɵɵtext,
  ɵɵtextInterpolate1
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/forum/pages/thread-detail/thread-detail.component.ts
var ThreadDetailComponent = class _ThreadDetailComponent {
  constructor(route) {
    this.route = route;
    this.threadId = null;
    this.threadId = this.route.snapshot.paramMap.get("id");
  }
  static {
    this.\u0275fac = function ThreadDetailComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ThreadDetailComponent)(\u0275\u0275directiveInject(ActivatedRoute));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ThreadDetailComponent, selectors: [["app-thread-detail"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 8, vars: 1, consts: [[1, "thread-detail"], [1, "posts"]], template: function ThreadDetailComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Thread Detail");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "p");
        \u0275\u0275text(4);
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(5, "div", 1)(6, "p");
        \u0275\u0275text(7, "Posts werden hier angezeigt...");
        \u0275\u0275elementEnd()()();
      }
      if (rf & 2) {
        \u0275\u0275advance(4);
        \u0275\u0275textInterpolate1("Thread ID: ", ctx.threadId, "");
      }
    }, dependencies: [CommonModule], encapsulation: 2 });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ThreadDetailComponent, { className: "ThreadDetailComponent" });
})();
export {
  ThreadDetailComponent
};
//# sourceMappingURL=chunk-TPVUBXQH.js.map
