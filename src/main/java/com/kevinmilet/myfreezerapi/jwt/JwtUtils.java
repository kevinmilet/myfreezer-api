package com.kevinmilet.myfreezerapi.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * @author kevin
 *
 */
@Component
public class JwtUtils {

    private static final long JWT_VALIDITY = 5 * 60 * 60;
    private static final String AUTHORITIES_KEY = "sub";
    private final Key key;
    private final JwtParser jwtParser;

    /**
     * Constructor
     * 
     * @param secret
     */
    public JwtUtils(@Value("${jwt.secret}") String secret) {
	this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String generateToken(Authentication authentication) {

	Map<String, Object> claims = new HashMap<>();

	return Jwts.builder().setClaims(claims).setSubject(authentication.getName())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000)).signWith(key).compact();
    }

    public Authentication getAuthentication(String token) {
	Claims claims = jwtParser.parseClaimsJws(token).getBody();

	Collection<? extends GrantedAuthority> authorities = Arrays
		.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).filter(auth -> !auth.trim().isEmpty())
		.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

	org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(
		claims.getSubject(), "", authorities);

	return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
