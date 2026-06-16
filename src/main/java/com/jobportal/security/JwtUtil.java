package com.jobportal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // secret key for strong password...
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "myveryveryverysecuresecretkey1234567890".getBytes()
    );

    //  token validity is 1 hr
    private final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7 ;

    // 🔹 1. Generate Token
    // now in order to generate token we have to make sure that parameters would be
    // email as well as the role
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)   // claim is used to add the extra data inside token
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // 🔹 2. Extract Email
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract role
    public String extractRole(String token)
    {
         return extractAllClaims(token).get("role",String.class);
    }

    // 🔹 3. Extract Expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 🔹 4. Generic Claim Extractor
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // 🔹 5. Validate Token
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    // 🔹 6. Check Expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 🔹 7. Extract All Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder() //
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}