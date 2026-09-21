package com.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.model.Userlogin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtil {

	@Value("${jwt.secreteKey}")
	private String jwtSecreteKey ;//key 
	
	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));//algorithm
	}
	
	public String generateAccessToken(Userlogin user) {
		
		System.out.println("Token Gen:::::");
		return Jwts.builder()
				   .subject(user.getUsername())
				   .claim("userid", user.getUserid())
				   .issuedAt(new Date())
				   .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
				   .signWith(getSecretKey())
				   .compact();
	}

	public String getUsernameFromToken(String token) {
		System.out.println("Token Verification :::::");
		Claims claims= Jwts.parser()
		.verifyWith(getSecretKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
		
		return claims.getSubject();//username
	}
	
	
}
