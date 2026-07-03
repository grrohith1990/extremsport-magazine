import {
  HttpClient,
  HttpParams
} from "./chunk-3WQAWOKT.js";
import {
  ɵɵdefineInjectable,
  ɵɵinject
} from "./chunk-SXWRULYK.js";

// src/environments/environment.ts
var environment = {
  production: false,
  apiGatewayUrl: "http://localhost:8000",
  keycloak: {
    url: "http://localhost:8080",
    realm: "extremsport",
    clientId: "extremsport-frontend"
  }
};

// src/app/core/services/article.service.ts
var ArticleService = class _ArticleService {
  constructor(http) {
    this.http = http;
    this.baseUrl = `${environment.apiGatewayUrl}/api/v1/articles`;
  }
  // === Public Access ===
  getPublicArticles(page = 0, size = 20) {
    const params = new HttpParams().set("page", page).set("size", size);
    return this.http.get(`${this.baseUrl}/public`, { params });
  }
  getArticleById(id) {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
  searchArticles(query, page = 0, size = 20) {
    const params = new HttpParams().set("q", query).set("page", page).set("size", size);
    return this.http.get(`${this.baseUrl}/search`, { params });
  }
  // === Premium Access ===
  getPremiumArticles(page = 0, size = 20) {
    const params = new HttpParams().set("page", page).set("size", size);
    return this.http.get(`${this.baseUrl}/premium`, { params });
  }
  // === Archive ===
  getArchivedArticles(page = 0, size = 20) {
    const params = new HttpParams().set("page", page).set("size", size);
    return this.http.get(`${this.baseUrl}/archive`, { params });
  }
  // === Author Operations ===
  createArticle(article) {
    return this.http.post(this.baseUrl, article);
  }
  updateArticle(id, article) {
    return this.http.put(`${this.baseUrl}/${id}`, article);
  }
  publishArticle(id) {
    return this.http.post(`${this.baseUrl}/${id}/publish`, {});
  }
  archiveArticle(id) {
    return this.http.post(`${this.baseUrl}/${id}/archive`, {});
  }
  getArticlesByAuthor(authorId) {
    return this.http.get(`${this.baseUrl}/author/${authorId}`);
  }
  static {
    this.\u0275fac = function ArticleService_Factory(__ngFactoryType__) {
      return new (__ngFactoryType__ || _ArticleService)(\u0275\u0275inject(HttpClient));
    };
  }
  static {
    this.\u0275prov = /* @__PURE__ */ \u0275\u0275defineInjectable({ token: _ArticleService, factory: _ArticleService.\u0275fac, providedIn: "root" });
  }
};

export {
  ArticleService
};
//# sourceMappingURL=chunk-FCC2N2KL.js.map
