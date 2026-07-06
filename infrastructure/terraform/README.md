# Extremsport Magazine - AWS Infrastructure (Terraform)

## Architecture

This Terraform configuration deploys the Extremsport Magazine platform to AWS using:

- **ECS Fargate** — Containerized microservices (no server management)
- **RDS PostgreSQL** — Managed database for each service
- **ALB** — Application Load Balancer for routing
- **ECR** — Container registry for Docker images
- **S3 + CloudFront** — Frontend static hosting
- **VPC** — Isolated network with public/private subnets

## Structure

```
infrastructure/terraform/
├── modules/
│   ├── vpc/          # VPC, subnets, NAT gateway
│   ├── ecr/          # Container registries
│   ├── ecs/          # ECS cluster, services, task definitions
│   ├── rds/          # PostgreSQL databases
│   └── alb/          # Application Load Balancer
├── environments/
│   ├── dev/          # Dev environment config
│   └── prod/         # Production environment config
└── README.md
```

## Prerequisites

- AWS CLI configured (`aws configure`)
- Terraform >= 1.5
- Docker (for building images)

## Usage

### Deploy Dev Environment

```bash
cd environments/dev
terraform init
terraform plan
terraform apply
```

### Deploy Production

```bash
cd environments/prod
terraform init
terraform plan
terraform apply
```

### Destroy

```bash
terraform destroy
```

## Services Deployed

| Service              | Port | Container       |
|---------------------|------|-----------------|
| API Gateway         | 8000 | api-gateway     |
| Article Service     | 8081 | article-service |
| User Service        | 8082 | user-service    |
| Forum Service       | 8083 | forum-service   |
| Subscription Service| 8084 | subscription-service |
| Frontend (S3)       | 443  | Static files    |

