package com.harsh.config;

import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	 @Value("${jwt.secret}")
	    private String secret;

	    private Key key;

	    @PostConstruct
	    public void init(){
	        this.key = Keys.hmacShaKeyFor(secret.getBytes());
	    }

	    public Claims getAllClaimsFromToken(String token) {
	        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	    }

	    private boolean isTokenExpired(String token) {
	        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
	    }

	    public boolean isInvalid(String token) {
	        return this.isTokenExpired(token);
	    }


}
