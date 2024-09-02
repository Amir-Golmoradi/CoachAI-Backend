package dev.amir.golmoradi.coachbackend.Infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {
    @Value("${spring.security.jwt.secret-key}")
    private static String secretKey;
    @Value("${spring.security.jwt.expiration-time}")
    private static long expirationTime;

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(subject, claims);

    }


    private String createToken(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractSubject(String jwt) {
        return extractClaims(jwt).getSubject();
    }


    private Claims extractClaims(String jwt) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt, String subject) {
        final String extractedSubject = extractSubject(jwt);
        return (extractedSubject.equals(subject) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        Date today = Date.from(Instant.now());
        return extractClaims(jwt).getExpiration().before(today);
    }


    private SecretKey getSignInKey() {
        byte[] byteKey = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(byteKey);
    }
}
