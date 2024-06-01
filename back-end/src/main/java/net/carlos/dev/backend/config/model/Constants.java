package net.carlos.dev.backend.config.model;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
	
	// Spring Security
	public static final String LOGIN_URL = "/authenticate";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	// JWT
	public static final String SUPER_SECRET_KEY = "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZX";
//	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
	public static final long TOKEN_EXPIRATION_TIME = 3_600_000;
	public static Key getSigningKey864(String secret) {
		byte[] keyBytes = Decoders.BASE64.decode(secret); 
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public static Key getSigningKey(String secret) { 
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8); 
		return Keys.hmacShaKeyFor(keyBytes);
	}
}