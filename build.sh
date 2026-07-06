#!/bin/bash
# Build all Extremsport Magazine services
set -e

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "🔨 Building Extremsport Magazine..."
echo ""

# Build Backend
echo "▶ Building Backend (Maven)..."
cd "$BASE_DIR/backend"
mvn clean package -DskipTests -q
echo "✅ Backend build complete"
echo ""

# Build Frontend
echo "▶ Building Frontend (Angular)..."
cd "$BASE_DIR/frontend"
npm ci --silent
npx ng build --configuration=production
echo "✅ Frontend build complete"
echo ""

echo "🎉 All builds successful!"
echo "   Backend JARs:  backend/*/target/*.jar"
echo "   Frontend dist:  frontend/dist/"

