<?php
// Central configuration for frontend
// Reads BACKEND_URL from environment (set this in Render). Defaults to http://localhost:9000 for local dev.
$backend = getenv('BACKEND_URL') ?: 'http://localhost:9000';
$backend = rtrim($backend, '/');
define('BACKEND_URL', $backend);

function api_url($path) {
    // Ensure path begins with '/'
    if (substr($path,0,1) !== '/') $path = '/' . $path;
    return BACKEND_URL . $path;
}

?>
