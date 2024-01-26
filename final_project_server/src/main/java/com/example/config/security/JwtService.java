package com.example.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long expiredTime;


    //ищем юзера по токену
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isNotExpiredToken(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.after(new Date());
    }


    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .setIssuedAt(new Date())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .setSubject(userDetails.getUsername())
                .compact();
    }

    //извлечение токена из крокозябры
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //возврат всех клемсов
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // key -обьект из java security
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
