#!/bin/bash
# Start all Extremsport Magazine backend services in dev mode

export JAVA_HOME=$(/usr/libexec/java_home -v 21)
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "🚀 Starting Extremsport Magazine Backend Services..."
echo "   Using Java: $JAVA_HOME"
echo ""

# Start Article Service
echo "▶ Starting Article Service (port 8081)..."
cd "$BASE_DIR/article-service" && mvn spring-boot:run -Dspring-boot.run.profiles=dev -q &

# Start User Service
echo "▶ Starting User Service (port 8082)..."
cd "$BASE_DIR/user-service" && mvn spring-boot:run -Dspring-boot.run.profiles=dev -q &

# Start Forum Service
echo "▶ Starting Forum Service (port 8083)..."
cd "$BASE_DIR/forum-service" && mvn spring-boot:run -Dspring-boot.run.profiles=dev -q &

# Start Subscription Service
echo "▶ Starting Subscription Service (port 8084)..."
cd "$BASE_DIR/subscription-service" && mvn spring-boot:run -Dspring-boot.run.profiles=dev -q &

# Start API Gateway
echo "▶ Starting API Gateway (port 8000)..."
cd "$BASE_DIR/api-gateway" && mvn spring-boot:run -Dspring-boot.run.profiles=dev -q &

echo ""
echo "✅ All services starting. Wait ~15 seconds for startup."
echo "   Article Service:      http://localhost:8081"
echo "   User Service:         http://localhost:8082"
echo "   Forum Service:        http://localhost:8083"
echo "   Subscription Service: http://localhost:8084"
echo "   API Gateway:          http://localhost:8000"
echo ""
echo "Press Ctrl+C to stop all services."
wait

