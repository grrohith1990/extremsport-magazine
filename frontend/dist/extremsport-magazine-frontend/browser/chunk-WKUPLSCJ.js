import {
  AuthGuard
} from "./chunk-B24JZZKN.js";
import "./chunk-3WQAWOKT.js";
import "./chunk-SXWRULYK.js";
import "./chunk-TXDUYLVM.js";

// src/app/features/forum/forum.routes.ts
var FORUM_ROUTES = [
  {
    path: "",
    loadComponent: () => import("./chunk-WZHLKYFV.js").then((m) => m.ForumOverviewComponent),
    title: "Forum"
  },
  {
    path: "thread/:id",
    loadComponent: () => import("./chunk-TPVUBXQH.js").then((m) => m.ThreadDetailComponent),
    title: "Thread"
  },
  {
    path: "new-thread",
    canActivate: [AuthGuard],
    data: { roles: ["READER", "SUBSCRIBER", "ADMIN"] },
    loadComponent: () => import("./chunk-B6T45RVO.js").then((m) => m.NewThreadComponent),
    title: "New Thread"
  }
];
export {
  FORUM_ROUTES
};
//# sourceMappingURL=chunk-WKUPLSCJ.js.map
