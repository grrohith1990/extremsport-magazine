#!/bin/bash
# Start the full development environment
set -e

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "🚀 Starting Extremsport Magazine Development Environment..."
echo ""

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
  echo "⚠️  Docker is not running. Please start Docker first."
  echo "   Run: docker compose up -d"
  exit 1
fi

# Start infrastructure (Docker)
echo "▶ Starting infrastructure (PostgreSQL, Keycloak)..."
cd "$BASE_DIR"
docker compose up -d
echo "✅ Infrastructure started"
echo ""

# Start Backend
echo "▶ Starting Backend Services..."
cd "$BASE_DIR/backend"
bash start-dev.sh &
BACKEND_PID=$!

# Wait for backend to be ready
echo "   Waiting for backend to start..."
sleep 15

# Start Frontend
echo ""
echo "▶ Starting Frontend (Angular Dev Server)..."
cd "$BASE_DIR/frontend"
npx ng serve --open &
FRONTEND_PID=$!

echo ""
echo "✅ Development environment ready!"
echo ""
echo "   🌐 Frontend:           http://localhost:4200"
echo "   🔌 API Gateway:        http://localhost:8000"
echo "   📝 Article Service:    http://localhost:8081"
echo "   👤 User Service:       http://localhost:8082"
echo "   💬 Forum Service:      http://localhost:8083"
echo "   💳 Subscription Svc:   http://localhost:8084"
echo ""
echo "Press Ctrl+C to stop all services."

# Wait and cleanup
trap "kill $BACKEND_PID $FRONTEND_PID 2>/dev/null; docker compose down; exit" SIGINT SIGTERM
wait

