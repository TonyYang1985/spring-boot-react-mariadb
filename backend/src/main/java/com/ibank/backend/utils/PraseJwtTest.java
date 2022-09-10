package com.ibank.backend.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class PraseJwtTest {
    public static void tokenToOut(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("my-123")
                .parseClaimsJws(token)
                .getBody();
    }
}