# RDS Module - PostgreSQL databases for microservices

variable "project_name" {
  type = string
}

variable "environment" {
  type = string
}

variable "vpc_id" {
  type = string
}

variable "private_subnet_ids" {
  type = list(string)
}

variable "ecs_security_group_id" {
  type = string
}

variable "instance_class" {
  type    = string
  default = "db.t3.micro"
}

variable "databases" {
  type = map(object({
    name     = string
    username = string
  }))
  default = {
    articles = {
      name     = "articles_db"
      username = "articles_user"
    }
    users = {
      name     = "users_db"
      username = "users_user"
    }
    forum = {
      name     = "forum_db"
      username = "forum_user"
    }
    subscriptions = {
      name     = "subscriptions_db"
      username = "subscriptions_user"
    }
  }
}

# DB Subnet Group
resource "aws_db_subnet_group" "main" {
  name       = "${var.project_name}-${var.environment}-db-subnet"
  subnet_ids = var.private_subnet_ids

  tags = {
    Name        = "${var.project_name}-${var.environment}-db-subnet"
    Environment = var.environment
  }
}

# Security Group for RDS
resource "aws_security_group" "rds" {
  name        = "${var.project_name}-${var.environment}-rds-sg"
  description = "Security group for RDS"
  vpc_id      = var.vpc_id

  ingress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [var.ecs_security_group_id]
  }

  tags = {
    Name        = "${var.project_name}-${var.environment}-rds-sg"
    Environment = var.environment
  }
}

# Generate random passwords
resource "random_password" "db_passwords" {
  for_each = var.databases
  length   = 24
  special  = false
}

# RDS Instances
resource "aws_db_instance" "services" {
  for_each = var.databases

  identifier     = "${var.project_name}-${var.environment}-${each.key}"
  engine         = "postgres"
  engine_version = "16.3"
  instance_class = var.instance_class

  allocated_storage     = 20
  max_allocated_storage = 50
  storage_encrypted     = true

  db_name  = each.value.name
  username = each.value.username
  password = random_password.db_passwords[each.key].result

  db_subnet_group_name   = aws_db_subnet_group.main.name
  vpc_security_group_ids = [aws_security_group.rds.id]

  multi_az            = var.environment == "prod" ? true : false
  skip_final_snapshot = var.environment == "dev" ? true : false

  backup_retention_period = var.environment == "prod" ? 7 : 1

  tags = {
    Name        = "${var.project_name}-${var.environment}-${each.key}"
    Environment = var.environment
  }
}

# Store credentials in AWS Secrets Manager
resource "aws_secretsmanager_secret" "db_credentials" {
  for_each = var.databases
  name     = "${var.project_name}/${var.environment}/${each.key}/db-credentials"

  tags = {
    Environment = var.environment
  }
}

resource "aws_secretsmanager_secret_version" "db_credentials" {
  for_each  = var.databases
  secret_id = aws_secretsmanager_secret.db_credentials[each.key].id

  secret_string = jsonencode({
    host     = aws_db_instance.services[each.key].endpoint
    port     = 5432
    dbname   = each.value.name
    username = each.value.username
    password = random_password.db_passwords[each.key].result
  })
}

output "db_endpoints" {
  value = { for k, v in aws_db_instance.services : k => v.endpoint }
}

output "db_secret_arns" {
  value = { for k, v in aws_secretsmanager_secret.db_credentials : k => v.arn }
}

