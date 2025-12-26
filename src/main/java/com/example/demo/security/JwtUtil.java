package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private final String secretKey;
    private final long expirationMillis;

    public JwtUtil(String secretKey, long expirationMillis) {
        this.secretKey = secretKey;
        this.expirationMillis = expirationMillis;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long userId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.get("role", String.class) : null;
    }

    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.get("userId", Long.class) : null;
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            if (claims == null) {
                return false;
            }
            Date expiration = claims.getExpiration();
            return expiration != null && !expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
