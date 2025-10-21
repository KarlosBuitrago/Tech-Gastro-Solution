#!/usr/bin/env bash
set -e

# Default backend URL (can be overridden by BACKEND_URL env var set in Render)
: ${BACKEND_URL:=http://localhost:9000}

echo "Starting entrypoint. Using BACKEND_URL=${BACKEND_URL}"

# Replace occurrences of hardcoded localhost backend URLs in .php/.js files inside /var/www/html
# This is simple and safe for most templates. If you have a central config file, prefer using that.
find /var/www/html -type f \( -name "*.php" -o -name "*.js" -o -name "*.html" \) -print0 \
  | xargs -0 sed -i "s#http://localhost:9000#${BACKEND_URL}#g"

exec "$@"
