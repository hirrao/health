package com.hirrao.health.security;

import com.hirrao.health.common.enums.RoleEnum;
import com.hirrao.health.common.model.AuthUser;
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
        claims.put("role", user.getRole()
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

    public AuthUser decodeTokenUser(String token) {
        var claims = Jwts.parser()
                         .verifyWith(secretKey)
                         .build()
                         .parseSignedClaims(token)
                         .getPayload();
        return new AuthUser(Long.parseLong(claims.getSubject()),
                            (String) claims.get("username"),
                            (String) claims.get("email"),
                            (RoleEnum) claims.get("role"));
    }

    public int decodeToken(String token) {
        return Integer.parseInt(Jwts.parser()
                                    .verifyWith(secretKey)
                                    .build()
                                    .parseSignedClaims(token)
                                    .getPayload()
                                    .getSubject());
    }

    public boolean isTokenExpired(String token) {
        var expirationDate = Jwts.parser()
                                 .verifyWith(secretKey)
                                 .build()
                                 .parseSignedClaims(token)
                                 .getPayload()
                                 .getExpiration();
        return expirationDate.before(new Date());
    }
}
