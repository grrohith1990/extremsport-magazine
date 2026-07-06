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
│   │   │   ├── auth/             # Authentifizierung
│   │   │   ├── forum/            # Forum
│   │   │   ├── subscription/     # Kundenportal & Abo-Verwaltung
│   │   │   └── author/           # Autorenbereich
│   │   └── shared/               # Shared Components
│   └── src/environments/         # Environment Configs
├── infrastructure/                # IaC & Deployment
│   ├── deploy.sh                  # Deployment Script
│   └── terraform/                 # Terraform Modules (VPC, ECS, ALB, RDS, ECR)
└── docker-compose.yml             # Lokale Entwicklungsumgebung
```

## Features

### Öffentlicher Bereich
- Artikel lesen (Public & Premium-Vorschau)
- Volltextsuche über alle Artikel
- Kategorien: Ski & Snowboard, Base Jumping, Mountainbike, Kajak, Klettern, Surfen, Paragliding u.v.m.

### Premium-Bereich (Abo erforderlich)
- Zugang zu allen Premium-Artikeln
- Archiv-Zugang
- Werbefreies Lesen
- Frühzeitiger Zugang zu neuen Artikeln
- Premium-Forum & Community
- Monatliche Gewinnspiele
- Exklusive Video-Dokumentationen

### Abo-Modelle
| Plan | Preis | Highlights |
|------|-------|------------|
| Monatlich | 9,99 € / Monat | Alle Premium-Artikel, Archiv, werbefrei, jederzeit kündbar |
| Jährlich | 89,99 € / Jahr | Alles aus Monatlich + 25% Ersparnis, exklusive Inhalte, Early Access, Community |

### Kundenportal
- Abo-Verwaltung
- Einzelartikel-Käufe
- Premium-Inhalte-Übersicht

### Autorenbereich
- Artikel erstellen & bearbeiten
- Entwürfe verwalten
- Veröffentlichungs-Workflow

### Forum
- Moderiertes Community-Forum
- Threads & Posts
- Moderations-Tools

## Technologie-Stack

| Layer | Technologie |
|-------|-------------|
| Frontend | Angular 18, Angular Material, NgRx, Keycloak-Angular |
| API Gateway | Spring Cloud Gateway, Resilience4j |
| Backend | Java 21, Spring Boot 3.3, Spring Security, Spring Data JPA |
| Auth | Keycloak (OAuth2/OIDC) |
| Messaging | RabbitMQ |
| Datenbank | PostgreSQL 16, Elasticsearch 8 |
| Infrastructure | Terraform (AWS: VPC, ECS, ALB, RDS, ECR) |
| DevOps | Docker, Docker Compose, GitHub Actions |
| Monitoring | Spring Boot Actuator, Prometheus, Grafana |

## Quick Start

### Voraussetzungen
- Java 21+
- Node.js 20+
- Docker & Docker Compose
- Maven 3.9+

### Entwicklungsumgebung starten

```bash
# Alles starten (Infrastructure + Services)
./start-dev.sh

# Oder manuell:

# 1. Infrastructure starten (PostgreSQL, Keycloak, RabbitMQ, Elasticsearch)
docker-compose up -d

# 2. Backend bauen
./build-backend.sh
# oder: cd backend && mvn clean install

# 3. Services starten (jeweils in separatem Terminal)
cd backend/api-gateway && mvn spring-boot:run -Dspring-boot.run.profiles=dev
cd backend/article-service && mvn spring-boot:run -Dspring-boot.run.profiles=dev
cd backend/user-service && mvn spring-boot:run -Dspring-boot.run.profiles=dev
cd backend/forum-service && mvn spring-boot:run -Dspring-boot.run.profiles=dev
cd backend/subscription-service && mvn spring-boot:run -Dspring-boot.run.profiles=dev

# 4. Frontend starten
./build-frontend.sh
# oder: cd frontend && npm install && ng serve
```

### Stoppen

```bash
./stop-dev.sh
```

### Tests ausführen

```bash
./test.sh
# oder: cd backend && mvn test
```

## Scripts

| Script | Beschreibung |
|--------|-------------|
| `start-dev.sh` | Startet die gesamte Entwicklungsumgebung |
| `stop-dev.sh` | Stoppt alle Services und Container |
| `build.sh` | Baut Backend und Frontend |
| `build-backend.sh` | Baut nur das Backend (Maven) |
| `build-frontend.sh` | Baut nur das Frontend (Angular) |
| `test.sh` | Führt alle Tests aus |
| `infrastructure/deploy.sh` | Deployment auf AWS |

## Architektur-Highlights

1. **Hexagonale Architektur** – Externe Systeme (CMS, Auth) austauschbar ohne Kernlogik-Änderung
2. **Ports & Adapters** – CMS-Wechsel (FileNet → Cloud) nur Adapter-Austausch
3. **Microservices** – Unabhängiges Deployment und Skalierung
4. **Event-Driven** – Lose Kopplung via RabbitMQ
5. **Circuit Breaker** – Graceful Degradation bei Ausfall externer Systeme
6. **Feature Toggles ready** – Schrittweise Migration (z.B. Auth: AD → Keycloak)
7. **Database per Service** – Keine geteilte Datenbank zwischen Microservices

## Qualitätsanforderungen (NFRs)

| # | Anforderung | Ziel |
|---|-------------|------|
| QA-1 | **Modifizierbarkeit** | Komponenten austauschbar (CMS, Auth-Server) in max. 2 Wochen |
| QA-2 | **Skalierbarkeit** | Horizontal skalierbar, 10x Last-Anstieg verkraftbar |
| QA-3 | **Sicherheit** | OAuth2/OIDC, JWT, RBAC – Schutz der Premium-Inhalte |
| QA-4 | **Verfügbarkeit** | 99,5% SLA, Circuit Breaker, Redundanz |
| QA-5 | **Performance** | FCP < 1,5s, TTI < 3s, Caching, CDN, Lazy Loading |

## Infrastructure (AWS)

Das Projekt nutzt Terraform für Infrastructure-as-Code mit folgenden Modulen:

- **VPC** – Netzwerk-Isolation
- **ECS** – Container Orchestration (Fargate)
- **ALB** – Application Load Balancer
- **RDS** – Managed PostgreSQL
- **ECR** – Container Registry

Umgebungen: `dev` und `prod` (siehe `infrastructure/terraform/environments/`)

## Dokumentation

- [Architektur-Dokumentation](docs/ARCHITECTURE.md)
- [Infrastructure README](infrastructure/terraform/README.md)
