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

// src/app/shared/components/unauthorized.component.ts
var UnauthorizedComponent = class _UnauthorizedComponent {
  static {
    this.\u0275fac = function UnauthorizedComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _UnauthorizedComponent)();
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _UnauthorizedComponent, selectors: [["app-unauthorized"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 7, vars: 0, consts: [[1, "unauthorized-container"], ["routerLink", "/"]], template: function UnauthorizedComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "div", 0)(1, "h1");
        \u0275\u0275text(2, "403 - Zugriff verweigert");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "p");
        \u0275\u0275text(4, "Sie haben keine Berechtigung, auf diese Seite zuzugreifen.");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(5, "a", 1);
        \u0275\u0275text(6, "Zur\xFCck zur Startseite");
        \u0275\u0275elementEnd()();
      }
    }, dependencies: [CommonModule], encapsulation: 2 });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(UnauthorizedComponent, { className: "UnauthorizedComponent" });
})();
export {
  UnauthorizedComponent
};
//# sourceMappingURL=chunk-U6FD7WFT.js.map
