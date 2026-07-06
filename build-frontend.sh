#!/bin/bash
# Build only the frontend
set -e

BASE_DIR="$(cd "$(dirname "$0")" && pwd)/frontend"

echo "🔨 Building Frontend..."
echo ""

cd "$BASE_DIR"

# Install dependencies
echo "▶ Installing dependencies..."
npm ci --silent

# Build for production
echo "▶ Building for production..."
npx ng build --configuration=production

echo ""
echo "✅ Frontend build complete!"
echo "   Output: frontend/dist/extremsport-magazine-frontend/"

