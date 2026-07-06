#!/bin/bash
# Deploy to AWS - Build, push images, and deploy
set -e

ENVIRONMENT=${1:-dev}
AWS_REGION="eu-central-1"
PROJECT_NAME="extremsport-magazine"
IMAGE_TAG=${2:-latest}

echo "🚀 Deploying Extremsport Magazine to AWS ($ENVIRONMENT)"
echo ""

# Get AWS Account ID
AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
ECR_BASE="$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com"

# Login to ECR
echo "▶ Logging into ECR..."
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_BASE

# Build and push backend services
SERVICES=("api-gateway" "article-service" "user-service" "forum-service" "subscription-service")

for SERVICE in "${SERVICES[@]}"; do
  echo ""
  echo "▶ Building $SERVICE..."
  cd "$(dirname "$0")/../backend/$SERVICE"

  # Build JAR
  mvn clean package -DskipTests -q

  # Build Docker image
  docker build -t "$PROJECT_NAME/$SERVICE:$IMAGE_TAG" .

  # Tag and push
  docker tag "$PROJECT_NAME/$SERVICE:$IMAGE_TAG" "$ECR_BASE/$PROJECT_NAME/$SERVICE:$IMAGE_TAG"
  docker push "$ECR_BASE/$PROJECT_NAME/$SERVICE:$IMAGE_TAG"

  echo "✅ $SERVICE pushed to ECR"
done

# Build and deploy frontend
echo ""
echo "▶ Building Frontend..."
cd "$(dirname "$0")/../frontend"
npm ci --silent
npx ng build --configuration=production

# Sync to S3
BUCKET_NAME="$PROJECT_NAME-$ENVIRONMENT-frontend"
echo "▶ Deploying Frontend to S3..."
aws s3 sync dist/extremsport-magazine-frontend/browser/ "s3://$BUCKET_NAME" --delete

echo ""
echo "▶ Updating ECS services..."
cd "$(dirname "$0")/terraform/environments/$ENVIRONMENT"
terraform apply -var="image_tag=$IMAGE_TAG" -auto-approve

echo ""
echo "🎉 Deployment complete!"
echo "   Environment: $ENVIRONMENT"
echo "   Image Tag:   $IMAGE_TAG"

