package com.example.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;

@Component
public class JwtService {

    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> Т extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey()
    }



    private Key getSigningKey(){

    }

}
