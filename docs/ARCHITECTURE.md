# Extremsport Online Magazine - Architecture Documentation

## 1. Qualitätsanforderungen (Non-Functional Requirements)

### QA-1: Modifizierbarkeit / Modifiability
**Beschreibung:** Das System muss so gestaltet sein, dass einzelne Komponenten (z.B. CMS, Auth-Server) ausgetauscht werden können, ohne das Gesamtsystem zu beeinträchtigen.  
**Stimulus:** Das CMS soll von IBM FileNet auf ein cloudbasiertes Produkt migriert werden.  
**Reaktion:** Der Austausch einer Komponente darf maximal 2 Wochen Entwicklungsarbeit erfordern, ohne andere Module zu beeinflussen.  
**Maßnahme:** Hexagonale Architektur mit Ports & Adapters Pattern; alle externen Systeme werden über Interfaces abstrahiert.

### QA-2: Skalierbarkeit / Scalability
**Beschreibung:** Das System muss bei steigender Nutzerzahl (z.B. nach viralen Extremsport-Videos) horizontal skalierbar sein.  
**Stimulus:** 10x Anstieg der gleichzeitigen Nutzer innerhalb von Minuten.  
**Reaktion:** Das System skaliert automatisch und hält Antwortzeiten unter 2 Sekunden.  
**Maßnahme:** Stateless Microservices, Container-basiertes Deployment (Docker/K8s), Event-Driven Architecture.

### QA-3: Sicherheit / Security
**Beschreibung:** Schutz der kostenpflichtigen Inhalte und Benutzerdaten. Nur berechtigte Nutzer dürfen auf Abo-Inhalte zugreifen.  
**Stimulus:** Unautorisierter Zugriff auf Premium-Inhalte.  
**Reaktion:** Zugriff wird verweigert, Vorfall wird geloggt.  
**Maßnahme:** OAuth2/OpenID Connect via Keycloak, JWT-Token basierte Autorisierung, RBAC (Role-Based Access Control).

### QA-4: Verfügbarkeit / Availability
**Beschreibung:** Das Onlinemagazin muss eine Verfügbarkeit von 99,5% erreichen (max. 44h Downtime/Jahr).  
**Stimulus:** Ausfall einer Systemkomponente.  
**Reaktion:** Das System bleibt funktionsfähig durch Redundanz und Graceful Degradation.  
**Maßnahme:** Circuit Breaker Pattern, Health Checks, Redundante Services, Caching-Strategien.

### QA-5: Performance / Responsiveness
**Beschreibung:** Artikel und Seiten müssen schnell laden, insbesondere auf mobilen Geräten (Responsive Frontend).  
**Stimulus:** Nutzer ruft einen Artikel auf einem mobilen Gerät auf.  
**Reaktion:** First Contentful Paint < 1,5s, Time to Interactive < 3s.  
**Maßnahme:** CDN für statische Assets, Lazy Loading, Server-Side Rendering für SEO, Caching auf API-Ebene.

---

## 2. High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                                    EXTERNAL SYSTEMS                                       │
│                                                                                           │
│  ┌──────────────────┐   ┌──────────────────────┐   ┌─────────────────────────────────┐  │
│  │   CMS System     │   │  Merchandise Shop    │   │  Buchhaltungs- & Rechnungs-     │  │
│  │  (Cloud-based    │   │  (Drittanbieter)     │   │  system (Windows 2000)          │  │
│  │   / IBM FileNet) │   │                      │   │                                 │  │
│  └────────┬─────────┘   └──────────┬───────────┘   └───────────────┬─────────────────┘  │
│           │                        │                                │                     │
└───────────┼────────────────────────┼────────────────────────────────┼─────────────────────┘
            │ REST/SOAP              │ REST API                       │ REST Adapter
            │                        │                                │
┌───────────┼────────────────────────┼────────────────────────────────┼─────────────────────┐
│           ▼                        ▼                                ▼                     │
│  ┌─────────────────────────────────────────────────────────────────────────────────────┐ │
│  │                          API GATEWAY (Spring Cloud Gateway)                          │ │
│  └───────────┬──────────────────┬──────────────────┬──────────────────┬────────────────┘ │
│              │                  │                  │                  │                    │
│              ▼                  ▼                  ▼                  ▼                    │
│  ┌───────────────┐  ┌───────────────┐  ┌───────────────┐  ┌────────────────────┐        │
│  │  Article      │  │  User &       │  │  Forum        │  │  Subscription &    │        │
│  │  Service      │  │  Author       │  │  Service      │  │  Payment Service   │        │
│  │  (Spring Boot)│  │  Service      │  │  (Spring Boot)│  │  (Spring Boot)     │        │
│  │               │  │  (Spring Boot)│  │               │  │                    │        │
│  │  - Public     │  │               │  │  - Threads    │  │  - Abo Management  │        │
│  │  - Premium    │  │  - Profile    │  │  - Posts      │  │  - Single Purchase │        │
│  │  - Archive    │  │  - Authoring  │  │  - Moderation │  │  - Billing         │        │
│  └───────┬───────┘  └───────┬───────┘  └───────┬───────┘  └─────────┬──────────┘        │
│          │                  │                  │                     │                    │
│          ▼                  ▼                  ▼                     ▼                    │
│  ┌─────────────────────────────────────────────────────────────────────────────────────┐ │
│  │                         MESSAGE BROKER (RabbitMQ / Kafka)                            │ │
│  └─────────────────────────────────────────────────────────────────────────────────────┘ │
│                                                                                           │
│  ┌─────────────────────────────────────────────────────────────────────────────────────┐ │
│  │                         PERSISTENCE LAYER                                            │ │
│  │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌─────────────┐  │ │
│  │   │PostgreSQL│    │PostgreSQL│    │PostgreSQL│    │PostgreSQL│    │Elasticsearch│  │ │
│  │   │(Articles)│    │(Users)   │    │(Forum)   │    │(Payments)│    │(Search/     │  │ │
│  │   │          │    │          │    │          │    │          │    │ Archive)    │  │ │
│  │   └──────────┘    └──────────┘    └──────────┘    └──────────┘    └─────────────┘  │ │
│  └─────────────────────────────────────────────────────────────────────────────────────┘ │
│                                                                                           │
│                              INTERNAL SYSTEM                                              │
└───────────────────────────────────────────────────────────────────────────────────────────┘
            ▲                                                          ▲
            │                                                          │
            │  OAuth2 / OIDC                                           │
            │                                                          │
┌───────────┴──────────────────┐                          ┌────────────┴───────────────────┐
│  Authentication & Auth       │                          │      Angular Frontend          │
│  Server                      │                          │                                │
│  (Keycloak / AD-basiert)     │◄─────────────────────────│  - Public Area (Articles)      │
│                              │      Token Validation     │  - Customer Portal (Abo)       │
│  - User Management           │                          │  - Author Area (CMS)           │
│  - Role Management           │                          │  - Forum                        │
│  - OAuth2 Provider           │                          │  - Responsive (Mobile-First)   │
└──────────────────────────────┘                          └────────────────────────────────┘
```

### Architektur-Entscheidungen (ADRs)

| Entscheidung | Begründung |
|---|---|
| **Microservices** | Ermöglicht unabhängige Skalierung und Deployment einzelner Fachbereiche |
| **API Gateway** | Zentraler Einstiegspunkt, Rate Limiting, Routing, SSL Termination |
| **Hexagonale Architektur pro Service** | Austauschbarkeit externer Systeme (CMS, Auth) ohne Kernlogik-Änderungen |
| **Event-Driven (Message Broker)** | Lose Kopplung zwischen Services, asynchrone Verarbeitung |
| **Keycloak (vorbereitet)** | Adapter-basiert: Wechsel von AD zu Keycloak über Konfiguration möglich |
| **Angular SPA** | Responsive, komponentenbasiert, gut für komplexe UIs mit State Management |
| **PostgreSQL pro Service** | Database per Service Pattern, keine geteilte Datenbank |
| **Elasticsearch** | Volltextsuche für Artikel und Archiv |

---

## 3. Technologie-Stack

| Schicht | Technologie |
|---|---|
| Frontend | Angular 18+, Angular Material, NgRx (State), SSR via Angular Universal |
| API Gateway | Spring Cloud Gateway |
| Backend Services | Java 21, Spring Boot 3.x, Spring Security, Spring Data JPA |
| Authentication | Keycloak (OAuth2/OIDC), Spring Security Resource Server |
| Messaging | RabbitMQ (oder Kafka für höhere Last) |
| Datenbank | PostgreSQL 16, Elasticsearch 8.x |
| Containerisierung | Docker, Kubernetes |
| CI/CD | GitHub Actions / GitLab CI |
| Monitoring | Spring Boot Actuator, Prometheus, Grafana |


