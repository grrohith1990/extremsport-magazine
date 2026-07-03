import "./chunk-TXDUYLVM.js";

// src/app/features/articles/articles.routes.ts
var ARTICLE_ROUTES = [
  {
    path: "",
    loadComponent: () => import("./chunk-EOQ6RE5P.js").then((m) => m.ArticleListComponent),
    title: "Extremsport Magazine - Home"
  },
  {
    path: "article/:id",
    loadComponent: () => import("./chunk-5JCJNNQ7.js").then((m) => m.ArticleDetailComponent),
    title: "Article"
  },
  {
    path: "search",
    loadComponent: () => import("./chunk-PV7UW7D7.js").then((m) => m.ArticleSearchComponent),
    title: "Search"
  },
  {
    path: "archive",
    loadComponent: () => import("./chunk-ERYENNQR.js").then((m) => m.ArticleArchiveComponent),
    title: "Archive"
  },
  {
    path: "premium",
    loadComponent: () => import("./chunk-EVKD5NOX.js").then((m) => m.PremiumArticlesComponent),
    title: "Premium Content"
  }
];
export {
  ARTICLE_ROUTES
};
//# sourceMappingURL=chunk-JSPC7IRS.js.map
