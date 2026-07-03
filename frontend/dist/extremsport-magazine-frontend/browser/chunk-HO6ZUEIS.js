import "./chunk-TXDUYLVM.js";

// src/app/features/author/author.routes.ts
var AUTHOR_ROUTES = [
  {
    path: "",
    loadComponent: () => import("./chunk-FY63BYDZ.js").then((m) => m.AuthorDashboardComponent),
    title: "Author Dashboard"
  },
  {
    path: "new-article",
    loadComponent: () => import("./chunk-IMUB3GYG.js").then((m) => m.ArticleEditorComponent),
    title: "New Article"
  },
  {
    path: "edit/:id",
    loadComponent: () => import("./chunk-IMUB3GYG.js").then((m) => m.ArticleEditorComponent),
    title: "Edit Article"
  }
];
export {
  AUTHOR_ROUTES
};
//# sourceMappingURL=chunk-HO6ZUEIS.js.map
