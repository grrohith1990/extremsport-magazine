import {
  DefaultValueAccessor,
  FormsModule,
  NgControlStatus,
  NgControlStatusGroup,
  NgForm,
  NgModel,
  RequiredValidator,
  ɵNgNoValidate
} from "./chunk-YNG4O4AC.js";
import {
  Router
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
  ɵɵlistener,
  ɵɵtext,
  ɵɵtwoWayBindingSet,
  ɵɵtwoWayListener,
  ɵɵtwoWayProperty
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/forum/pages/new-thread/new-thread.component.ts
var NewThreadComponent = class _NewThreadComponent {
  constructor(router) {
    this.router = router;
    this.title = "";
    this.content = "";
  }
  createThread() {
    console.log("Creating thread:", this.title);
    this.router.navigate(["/forum"]);
  }
  static {
    this.\u0275fac = function NewThreadComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _NewThreadComponent)(\u0275\u0275directiveInject(Router));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _NewThreadComponent, selectors: [["app-new-thread"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 14, vars: 2, consts: [[1, "new-thread"], [3, "ngSubmit"], [1, "form-group"], ["for", "title"], ["id", "title", "type", "text", "name", "title", "required", "", 3, "ngModelChange", "ngModel"], ["for", "content"], ["id", "content", "name", "content", "rows", "10", "required", "", 3, "ngModelChange", "ngModel"], ["type", "submit"]], template: function NewThreadComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2, "Neues Thema erstellen");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "form", 1);
        \u0275\u0275listener("ngSubmit", function NewThreadComponent_Template_form_ngSubmit_3_listener() {
          return ctx.createThread();
        });
        \u0275\u0275elementStart(4, "div", 2)(5, "label", 3);
        \u0275\u0275text(6, "Titel");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(7, "input", 4);
        \u0275\u0275twoWayListener("ngModelChange", function NewThreadComponent_Template_input_ngModelChange_7_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.title, $event) || (ctx.title = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(8, "div", 2)(9, "label", 5);
        \u0275\u0275text(10, "Inhalt");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(11, "textarea", 6);
        \u0275\u0275twoWayListener("ngModelChange", function NewThreadComponent_Template_textarea_ngModelChange_11_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.content, $event) || (ctx.content = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(12, "button", 7);
        \u0275\u0275text(13, "Thema erstellen");
        \u0275\u0275elementEnd()()();
      }
      if (rf & 2) {
        \u0275\u0275advance(7);
        \u0275\u0275twoWayProperty("ngModel", ctx.title);
        \u0275\u0275advance(4);
        \u0275\u0275twoWayProperty("ngModel", ctx.content);
      }
    }, dependencies: [CommonModule, FormsModule, \u0275NgNoValidate, DefaultValueAccessor, NgControlStatus, NgControlStatusGroup, RequiredValidator, NgModel, NgForm], encapsulation: 2 });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(NewThreadComponent, { className: "NewThreadComponent" });
})();
export {
  NewThreadComponent
};
//# sourceMappingURL=chunk-B6T45RVO.js.map
