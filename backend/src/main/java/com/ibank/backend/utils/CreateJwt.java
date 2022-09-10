package com.ibank.backend.utils;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import com.ibank.backend.entity.User;

public class CreateJwt {
    public static String  getoken(User user) {
        JwtBuilder jwtBuilder =  Jwts.builder()
                .setId(user.getId()+"")
                .setSubject(user.getUsername()) 
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "my-123").setExpiration(new Date(new 
         Date().getTime()+86400000));
        return  jwtBuilder.compact();
    }
}
