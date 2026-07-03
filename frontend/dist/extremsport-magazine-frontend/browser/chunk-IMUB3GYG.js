import {
  DefaultValueAccessor,
  FormsModule,
  NgControlStatus,
  NgControlStatusGroup,
  NgForm,
  NgModel,
  NgSelectOption,
  RequiredValidator,
  SelectControlValueAccessor,
  ɵNgNoValidate,
  ɵNgSelectMultipleOption
} from "./chunk-YNG4O4AC.js";
import {
  ArticleService
} from "./chunk-FCC2N2KL.js";
import {
  ActivatedRoute,
  Router
} from "./chunk-3WQAWOKT.js";
import {
  CommonModule,
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
  ɵɵproperty,
  ɵɵresetView,
  ɵɵrestoreView,
  ɵɵtemplate,
  ɵɵtext,
  ɵɵtextInterpolate,
  ɵɵtwoWayBindingSet,
  ɵɵtwoWayListener,
  ɵɵtwoWayProperty
} from "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/core/models/models.ts
var ArticleStatus;
(function(ArticleStatus2) {
  ArticleStatus2["DRAFT"] = "DRAFT";
  ArticleStatus2["IN_REVIEW"] = "IN_REVIEW";
  ArticleStatus2["PUBLISHED"] = "PUBLISHED";
  ArticleStatus2["ARCHIVED"] = "ARCHIVED";
})(ArticleStatus || (ArticleStatus = {}));
var AccessType;
(function(AccessType2) {
  AccessType2["PUBLIC"] = "PUBLIC";
  AccessType2["PREMIUM"] = "PREMIUM";
  AccessType2["EXCLUSIVE"] = "EXCLUSIVE";
})(AccessType || (AccessType = {}));
var UserRole;
(function(UserRole2) {
  UserRole2["READER"] = "READER";
  UserRole2["SUBSCRIBER"] = "SUBSCRIBER";
  UserRole2["AUTHOR"] = "AUTHOR";
  UserRole2["EDITOR"] = "EDITOR";
  UserRole2["MODERATOR"] = "MODERATOR";
  UserRole2["ADMIN"] = "ADMIN";
})(UserRole || (UserRole = {}));
var SubscriptionPlan;
(function(SubscriptionPlan2) {
  SubscriptionPlan2["MONTHLY"] = "MONTHLY";
  SubscriptionPlan2["YEARLY"] = "YEARLY";
  SubscriptionPlan2["PREMIUM"] = "PREMIUM";
})(SubscriptionPlan || (SubscriptionPlan = {}));
var SubscriptionStatus;
(function(SubscriptionStatus2) {
  SubscriptionStatus2["ACTIVE"] = "ACTIVE";
  SubscriptionStatus2["CANCELLED"] = "CANCELLED";
  SubscriptionStatus2["EXPIRED"] = "EXPIRED";
  SubscriptionStatus2["TRIAL"] = "TRIAL";
})(SubscriptionStatus || (SubscriptionStatus = {}));

// src/app/features/author/pages/article-editor/article-editor.component.ts
function ArticleEditorComponent_button_53_Template(rf, ctx) {
  if (rf & 1) {
    const _r1 = \u0275\u0275getCurrentView();
    \u0275\u0275elementStart(0, "button", 27);
    \u0275\u0275listener("click", function ArticleEditorComponent_button_53_Template_button_click_0_listener() {
      \u0275\u0275restoreView(_r1);
      const ctx_r1 = \u0275\u0275nextContext();
      return \u0275\u0275resetView(ctx_r1.publish());
    });
    \u0275\u0275text(1, "Ver\xF6ffentlichen");
    \u0275\u0275elementEnd();
  }
}
var ArticleEditorComponent = class _ArticleEditorComponent {
  constructor(route, router, articleService) {
    this.route = route;
    this.router = router;
    this.articleService = articleService;
    this.article = { accessType: AccessType.PUBLIC };
    this.tagsInput = "";
    this.isEdit = false;
    this.AccessType = AccessType;
  }
  ngOnInit() {
    const id = this.route.snapshot.paramMap.get("id");
    if (id) {
      this.isEdit = true;
      this.articleService.getArticleById(id).subscribe((article) => {
        this.article = article;
        this.tagsInput = article.tags?.join(", ") || "";
      });
    }
  }
  save() {
    this.article.tags = this.tagsInput.split(",").map((t) => t.trim()).filter((t) => t);
    if (this.isEdit && this.article.id) {
      this.articleService.updateArticle(this.article.id, this.article).subscribe(() => {
        this.router.navigate(["/author"]);
      });
    } else {
      this.articleService.createArticle(this.article).subscribe(() => {
        this.router.navigate(["/author"]);
      });
    }
  }
  publish() {
    if (this.article.id) {
      this.articleService.publishArticle(this.article.id).subscribe(() => {
        this.router.navigate(["/author"]);
      });
    }
  }
  static {
    this.\u0275fac = function ArticleEditorComponent_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ArticleEditorComponent)(\u0275\u0275directiveInject(ActivatedRoute), \u0275\u0275directiveInject(Router), \u0275\u0275directiveInject(ArticleService));
    };
  }
  static {
    this.\u0275cmp = /* @__PURE__ */ \u0275\u0275defineComponent({ type: _ArticleEditorComponent, selectors: [["app-article-editor"]], standalone: true, features: [\u0275\u0275StandaloneFeature], decls: 54, vars: 13, consts: [[1, "article-editor"], [3, "ngSubmit"], [1, "form-group"], ["for", "title"], ["id", "title", "type", "text", "name", "title", "required", "", 3, "ngModelChange", "ngModel"], ["for", "subtitle"], ["id", "subtitle", "type", "text", "name", "subtitle", 3, "ngModelChange", "ngModel"], ["for", "category"], ["id", "category", "name", "category", 3, "ngModelChange", "ngModel"], ["value", "climbing"], ["value", "surfing"], ["value", "skydiving"], ["value", "mountainbiking"], ["value", "snowboarding"], ["value", "other"], ["for", "accessType"], ["id", "accessType", "name", "accessType", 3, "ngModelChange", "ngModel"], [3, "value"], ["for", "summary"], ["id", "summary", "name", "summary", "rows", "3", 3, "ngModelChange", "ngModel"], ["for", "content"], ["id", "content", "name", "content", "rows", "20", "required", "", 3, "ngModelChange", "ngModel"], ["for", "tags"], ["id", "tags", "type", "text", "name", "tags", 3, "ngModelChange", "ngModel"], [1, "actions"], ["type", "submit"], ["type", "button", 3, "click", 4, "ngIf"], ["type", "button", 3, "click"]], template: function ArticleEditorComponent_Template(rf, ctx) {
      if (rf & 1) {
        \u0275\u0275elementStart(0, "section", 0)(1, "h1");
        \u0275\u0275text(2);
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(3, "form", 1);
        \u0275\u0275listener("ngSubmit", function ArticleEditorComponent_Template_form_ngSubmit_3_listener() {
          return ctx.save();
        });
        \u0275\u0275elementStart(4, "div", 2)(5, "label", 3);
        \u0275\u0275text(6, "Titel *");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(7, "input", 4);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_input_ngModelChange_7_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.article.title, $event) || (ctx.article.title = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(8, "div", 2)(9, "label", 5);
        \u0275\u0275text(10, "Untertitel");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(11, "input", 6);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_input_ngModelChange_11_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.article.subtitle, $event) || (ctx.article.subtitle = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(12, "div", 2)(13, "label", 7);
        \u0275\u0275text(14, "Kategorie");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(15, "select", 8);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_select_ngModelChange_15_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.article.category, $event) || (ctx.article.category = $event);
          return $event;
        });
        \u0275\u0275elementStart(16, "option", 9);
        \u0275\u0275text(17, "Klettern");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(18, "option", 10);
        \u0275\u0275text(19, "Surfen");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(20, "option", 11);
        \u0275\u0275text(21, "Fallschirmspringen");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(22, "option", 12);
        \u0275\u0275text(23, "Mountainbiking");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(24, "option", 13);
        \u0275\u0275text(25, "Snowboarding");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(26, "option", 14);
        \u0275\u0275text(27, "Sonstiges");
        \u0275\u0275elementEnd()()();
        \u0275\u0275elementStart(28, "div", 2)(29, "label", 15);
        \u0275\u0275text(30, "Zugangsart");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(31, "select", 16);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_select_ngModelChange_31_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.article.accessType, $event) || (ctx.article.accessType = $event);
          return $event;
        });
        \u0275\u0275elementStart(32, "option", 17);
        \u0275\u0275text(33, "\xD6ffentlich (kostenlos)");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(34, "option", 17);
        \u0275\u0275text(35, "Premium (Einzelkauf oder Abo)");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(36, "option", 17);
        \u0275\u0275text(37, "Exklusiv (nur Abo)");
        \u0275\u0275elementEnd()()();
        \u0275\u0275elementStart(38, "div", 2)(39, "label", 18);
        \u0275\u0275text(40, "Zusammenfassung");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(41, "textarea", 19);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_textarea_ngModelChange_41_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.article.summary, $event) || (ctx.article.summary = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(42, "div", 2)(43, "label", 20);
        \u0275\u0275text(44, "Inhalt *");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(45, "textarea", 21);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_textarea_ngModelChange_45_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.article.content, $event) || (ctx.article.content = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(46, "div", 2)(47, "label", 22);
        \u0275\u0275text(48, "Tags (kommagetrennt)");
        \u0275\u0275elementEnd();
        \u0275\u0275elementStart(49, "input", 23);
        \u0275\u0275twoWayListener("ngModelChange", function ArticleEditorComponent_Template_input_ngModelChange_49_listener($event) {
          \u0275\u0275twoWayBindingSet(ctx.tagsInput, $event) || (ctx.tagsInput = $event);
          return $event;
        });
        \u0275\u0275elementEnd()();
        \u0275\u0275elementStart(50, "div", 24)(51, "button", 25);
        \u0275\u0275text(52);
        \u0275\u0275elementEnd();
        \u0275\u0275template(53, ArticleEditorComponent_button_53_Template, 2, 0, "button", 26);
        \u0275\u0275elementEnd()()();
      }
      if (rf & 2) {
        \u0275\u0275advance(2);
        \u0275\u0275textInterpolate(ctx.isEdit ? "Artikel bearbeiten" : "Neuer Artikel");
        \u0275\u0275advance(5);
        \u0275\u0275twoWayProperty("ngModel", ctx.article.title);
        \u0275\u0275advance(4);
        \u0275\u0275twoWayProperty("ngModel", ctx.article.subtitle);
        \u0275\u0275advance(4);
        \u0275\u0275twoWayProperty("ngModel", ctx.article.category);
        \u0275\u0275advance(16);
        \u0275\u0275twoWayProperty("ngModel", ctx.article.accessType);
        \u0275\u0275advance();
        \u0275\u0275property("value", ctx.AccessType.PUBLIC);
        \u0275\u0275advance(2);
        \u0275\u0275property("value", ctx.AccessType.PREMIUM);
        \u0275\u0275advance(2);
        \u0275\u0275property("value", ctx.AccessType.EXCLUSIVE);
        \u0275\u0275advance(5);
        \u0275\u0275twoWayProperty("ngModel", ctx.article.summary);
        \u0275\u0275advance(4);
        \u0275\u0275twoWayProperty("ngModel", ctx.article.content);
        \u0275\u0275advance(4);
        \u0275\u0275twoWayProperty("ngModel", ctx.tagsInput);
        \u0275\u0275advance(3);
        \u0275\u0275textInterpolate(ctx.isEdit ? "Speichern" : "Erstellen");
        \u0275\u0275advance();
        \u0275\u0275property("ngIf", ctx.isEdit);
      }
    }, dependencies: [CommonModule, NgIf, FormsModule, \u0275NgNoValidate, NgSelectOption, \u0275NgSelectMultipleOption, DefaultValueAccessor, SelectControlValueAccessor, NgControlStatus, NgControlStatusGroup, RequiredValidator, NgModel, NgForm], styles: ["\n\n.article-editor[_ngcontent-%COMP%] {\n  max-width: 900px;\n  margin: 0 auto;\n  padding: 2rem;\n}\n.form-group[_ngcontent-%COMP%] {\n  margin-bottom: 1.5rem;\n}\n.form-group[_ngcontent-%COMP%]   label[_ngcontent-%COMP%] {\n  display: block;\n  font-weight: bold;\n  margin-bottom: 0.5rem;\n}\n.form-group[_ngcontent-%COMP%]   input[_ngcontent-%COMP%], \n.form-group[_ngcontent-%COMP%]   textarea[_ngcontent-%COMP%], \n.form-group[_ngcontent-%COMP%]   select[_ngcontent-%COMP%] {\n  width: 100%;\n  padding: 0.75rem;\n  border: 1px solid #ddd;\n  border-radius: 6px;\n  font-size: 1rem;\n}\n.actions[_ngcontent-%COMP%] {\n  display: flex;\n  gap: 1rem;\n}\n.actions[_ngcontent-%COMP%]   button[_ngcontent-%COMP%] {\n  padding: 0.75rem 1.5rem;\n  border-radius: 6px;\n  cursor: pointer;\n}\n/*# sourceMappingURL=article-editor.component.css.map */"] });
  }
};
(() => {
  (typeof ngDevMode === "undefined" || ngDevMode) && \u0275setClassDebugInfo(ArticleEditorComponent, { className: "ArticleEditorComponent" });
})();
export {
  ArticleEditorComponent
};
//# sourceMappingURL=chunk-IMUB3GYG.js.map
