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

// src/app/features/subscription/pages/portal-dashboard/portal-dashboard.component.ts
var PortalDashboardComponent = class _PortalDashboardComponent {
  static {
    this.\u0275fac = function PortalDashboardComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _PortalDashboardComponent)();
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _PortalDashboardComponent, selectors: [["app-portal-dashboard"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 23, vars: 0, consts: [[1, "portal-dashboard"], [1, "dashboard-grid"], [1, "card"], ["routerLink", "manage"], ["routerLink", "/premium"]], template: function PortalDashboardComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Kundenportal");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "div", 1)(4, "div", 2)(5, "h3");
        \u0275\u0275text(6, "Mein Abo");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(7, "p");
        \u0275\u0275text(8, "Status: ");
        \u0275\u0275elementStart(9, "strong");
        \u0275\u0275text(10, "Aktiv");
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(11, "a", 3);
        \u0275\u0275text(12, "Abo verwalten");
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(13, "div", 2)(14, "h3");
        \u0275\u0275text(15, "Meine K\xE4ufe");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(16, "p");
        \u0275\u0275text(17, "Einzelartikel und Bestellungen");
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(18, "div", 2)(19, "h3");
        \u0275\u0275text(20, "Premium Inhalte");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(21, "a", 4);
        \u0275\u0275text(22, "Alle Premium Artikel ansehen");
        \u0275\u0275elementEnd()()()();
      }
    }, dependencies: [CommonModule, RouterModule, RouterLink], styles: ["\n\n.dashboard-grid[_ngcontent-%COMP%] {\n  display: grid;\n  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));\n  gap: 1.5rem;\n  margin-top: 1.5rem;\n}\n.card[_ngcontent-%COMP%] {\n  padding: 1.5rem;\n  border: 1px solid #ddd;\n  border-radius: 8px;\n}\n/*# sourceMappingURL=portal-dashboard.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(PortalDashboardComponent, { className: "PortalDashboardComponent" });
})();
export {
  PortalDashboardComponent
};
//# sourceMappingURL=chunk-QCOP44EZ.js.map
