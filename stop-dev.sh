#!/bin/bash
# Stop all running services
set -e

echo "🛑 Stopping Extremsport Magazine Services..."

# Stop Spring Boot services
echo "▶ Stopping backend services..."
pkill -f "spring-boot:run" 2>/dev/null && echo "   Backend stopped" || echo "   No backend processes found"

# Stop Angular dev server
echo "▶ Stopping frontend dev server..."
pkill -f "ng serve" 2>/dev/null && echo "   Frontend stopped" || echo "   No frontend process found"

# Stop Docker (optional)
if [ "$1" = "--all" ]; then
  echo "▶ Stopping Docker containers..."
  docker compose down 2>/dev/null && echo "   Docker stopped" || echo "   No Docker containers found"
fi

echo ""
echo "✅ All services stopped."
echo "   Use '--all' flag to also stop Docker containers."

