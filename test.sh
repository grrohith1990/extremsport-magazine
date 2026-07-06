#!/bin/bash
# Run all tests for Extremsport Magazine
set -e

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "🧪 Running Extremsport Magazine Tests..."
echo ""

# Backend Tests
echo "▶ Running Backend Tests (Maven)..."
cd "$BASE_DIR/backend"
mvn test -q
echo "✅ Backend tests passed"
echo ""

# Frontend Tests
echo "▶ Running Frontend Tests (Karma)..."
cd "$BASE_DIR/frontend"
npx ng test --watch=false --browsers=ChromeHeadless
echo "✅ Frontend tests passed"
echo ""

echo "🎉 All tests passed!"

