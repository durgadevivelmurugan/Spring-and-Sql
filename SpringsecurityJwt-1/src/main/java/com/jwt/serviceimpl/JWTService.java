package com.jwt.serviceimpl;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jwt.entity.Student;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	
	private final String SECRET_KEY="e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
	
	public String generateToken(Student student) {
		String token=Jwts.builder()
				.subject(student.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+ 24*60*60*1000))
				.signWith(getSigninKey())
				.compact();
		return token;
	}

	private SecretKey getSigninKey() {
		byte[] keybytes=Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public Claims extractAllClaims(String token) {
	 return Jwts
			 .parser()
			 .verifyWith(getSigninKey())
			 .build()
			 .parseSignedClaims(token)
			 .getPayload();
	}
	public <T>T extractClaim(String token,Function<Claims, T>resolver){
		Claims claims=extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isValid(String token ,UserDetails userDetails) {
		String Username=extractUsername(token);
		return Username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
