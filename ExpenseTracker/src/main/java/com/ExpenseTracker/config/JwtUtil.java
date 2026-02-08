package com.ExpenseTracker.config;

import com.ExpenseTracker.ENUMs.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECERT_KEY = "my-super-secret-key-for-hs256-algorithm-123456";
    private final long exp = 1000*60*60*4;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECERT_KEY.getBytes());
    }
    public String generateToken(String username, Role role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role",role.name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+exp))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
//        return getUsername(token).equals(username) && !isTokenExpired(token);
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String getUsername(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            return null;
        }
    }
    public String getRole(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("role",String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isTokenExpired(String token){
        Date expire = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expire.before(new Date());
    }
}
