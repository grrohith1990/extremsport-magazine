# Extremsport Online Magazine

Agiles Softwareprojekt für ein Onlinemagazin im Bereich Extremsport.

## Projektstruktur

```
extremsport-magazine/
├── docs/                          # Architektur-Dokumentation
│   ├── ARCHITECTURE.md            # Qualitätsanforderungen & Architektur
│   └── architecture-diagram.puml  # PlantUML Diagramm
├── backend/                       # Java Spring Boot Microservices
│   ├── pom.xml                    # Parent POM
│   ├── api-gateway/               # Spring Cloud Gateway
│   ├── article-service/           # Artikel (Public, Premium, Archiv)
│   ├── user-service/              # User & Autoren
│   ├── forum-service/             # Moderiertes Forum
│   └── subscription-service/      # Abo & Payment
├── frontend/                      # Angular 18 SPA
│   ├── src/app/
│   │   ├── core/                  # Services, Guards, Models
│   │   ├── features/             # Feature Modules (Lazy Loaded)
│   │   │   ├── articles/         # Public & Premium Artikel
│   │   │   ├── forum/            # Forum
│   │   │   ├── subscription/     # Kundenportal
│   │   │   └── author/           # Autorenbereich
│   │   └── shared/               # Shared Components
│   └── src/environments/         # Environment Configs
└── docker-compose.yml             # Lokale Entwicklungsumgebung
```

## Technologie-Stack

| Layer | Technologie |
|-------|-------------|
| Frontend | Angular 18, Angular Material, NgRx, Keycloak-Angular |
| API Gateway | Spring Cloud Gateway, Resilience4j |
| Backend | Java 21, Spring Boot 3.3, Spring Security, Spring Data JPA |
| Auth | Keycloak (OAuth2/OIDC) |
| Messaging | RabbitMQ |
| Datenbank | PostgreSQL 16, Elasticsearch 8 |
| DevOps | Docker, Docker Compose |

## Quick Start

```bash
# 1. Infrastructure starten
docker-compose up -d

# 2. Backend bauen
cd backend && mvn clean install

# 3. Services starten (jeweils in separatem Terminal)
cd backend/api-gateway && mvn spring-boot:run
cd backend/article-service && mvn spring-boot:run

# 4. Frontend starten
cd frontend && npm install && ng serve
```

## Architektur-Highlights (Agilität)

1. **Hexagonale Architektur** - Externe Systeme (CMS, Auth) austauschbar ohne Kernlogik-Änderung
2. **Ports & Adapters** - CMS-Wechsel (FileNet → Cloud) nur Adapter-Austausch
3. **Microservices** - Unabhängiges Deployment und Skalierung
4. **Event-Driven** - Lose Kopplung via RabbitMQ
5. **Circuit Breaker** - Graceful Degradation bei Ausfall externer Systeme
6. **Feature Toggles ready** - Schrittweise Migration (z.B. Auth: AD → Keycloak)

## Qualitätsanforderungen (NFRs)

1. **Modifizierbarkeit** - Komponenten austauschbar (CMS, Auth-Server)
2. **Skalierbarkeit** - Horizontal skalierbar, stateless Services
3. **Sicherheit** - OAuth2/OIDC, JWT, RBAC
4. **Verfügbarkeit** - 99,5% SLA, Circuit Breaker, Redundanz
5. **Performance** - FCP < 1,5s, Caching, CDN, Lazy Loading

