#!/bin/bash
# Build only the backend services
set -e

BASE_DIR="$(cd "$(dirname "$0")" && pwd)/backend"
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

echo "🔨 Building Backend Services..."
echo "   Using Java: $JAVA_HOME"
echo ""

cd "$BASE_DIR"
mvn clean package -DskipTests

echo ""
echo "✅ Backend build complete!"
echo "   api-gateway:          backend/api-gateway/target/*.jar"
echo "   article-service:      backend/article-service/target/*.jar"
echo "   user-service:         backend/user-service/target/*.jar"
echo "   forum-service:        backend/forum-service/target/*.jar"
echo "   subscription-service: backend/subscription-service/target/*.jar"

