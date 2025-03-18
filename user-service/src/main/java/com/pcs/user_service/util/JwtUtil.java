package com.pcs.user_service.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "23ec164c7317fa7717689207daa003c6c32c0b23b47759e17c9ca4a6eb8e7dbdaff30cb52bee114f7c7bd1ac5ab9bc26166b93093e198f54ccbfb282562b50e9ac042c4755b68623edf1c3f01ab1dad4064c10fab5f8a9d0b2740b4b672096eb1c4069ba51527d1d6bc785f5dc8c009b1b8f907af9edfc3b8a1b25f06823fe2bc984fbb89c1eb02e067485c77c38ed904ba98993547b18d49c7c974be3019ad05b539d34d66fcf2e71f87eaeb04901c9791fd39fb1f5a9a0f221b1eae43be0f20ba275ae1c443d3f22f9a55d57124c4c1dff48628a38752177e673adc4dd2459215392f1c1d8619069b77db2f91c17c8c29479530e4564040778c258612644c7"; // Must be 256-bit for HS256
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Set the payload with username
                .setIssuedAt(new Date()) // Token creation date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiration time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with secret key
                .compact(); // Generate JWT
    }

    // Extract Username from Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract Expiration Date from Token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim using function
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract All Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate Token
    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}

