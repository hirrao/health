package com.hirrao.health.component;

import com.hirrao.health.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Configuration
public class JWTUtil {

    private final SecretKey secretKey;
    private final long expiration;
    private final String issuer;

    public JWTUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expiration,
                   @Value("${jwt.issuer}") String issuer) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
        this.issuer = issuer;
    }

    public String createToken(User user) {
        var claims = new HashMap<String, String>();
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("permission", user.getPermission()
                                     .name());
        return Jwts.builder()
                   .claims(claims)
                   .subject(String.valueOf(user.getUid()))
                   .issuer(issuer)
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(
                           new Date(System.currentTimeMillis() + expiration))
                   .signWith(secretKey)
                   .compact();
    }

    public int decodeToken(String token) {
        return Integer.parseInt(Jwts.parser()
                                    .decryptWith(secretKey)
                                    .build()
                                    .parseEncryptedClaims(token)
                                    .getPayload()
                                    .getSubject());
    }
}
