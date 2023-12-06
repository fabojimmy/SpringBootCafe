package com.inn.cafe.cafe.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private String secret="fabodaysh1445sqkjhsqhsgyuqguza213598qsqkjbhsijnklhkqsjgjqkjshdjgjqhsvbkj89489qsjhbqshjqjsjhqbqvsbhskjxb";


    public String extractUsername(String token)
    {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){

        return extractClaims(token,Claims::getExpiration);
    }

    public <T> T extractClaims(String token ,Function<Claims,T> claimResolver)
    {
        final Claims claims =extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
    }

     public String generateToken(String username,String role) {
            Map<String, Object>claims=new HashMap<>();

            claims.put("role", role);
            return this.createToken(claims, username);
    }

    private String createToken(Map<String,Object>claim,String username) {

        return Jwts.builder()
                  .setClaims(claim)
                  .setSubject(username)
                  .setIssuedAt(new Date(System.currentTimeMillis()))
                  .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                  .signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token,UserDetails userDetails){

        final String username=extractUsername(token);
        return(username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private Key getSignKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
            return Keys.hmacShaKeyFor(keyBytes);
    }   
}
