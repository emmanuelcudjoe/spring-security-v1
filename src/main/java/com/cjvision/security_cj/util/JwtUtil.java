package com.cjvision.security_cj.util;

import com.cjvision.security_cj.Entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final long TOKEN_DURATION = 24 * 60 * 60 * 1000;

    @Value("${app.jwt.secret_key}")
    private String secret_key;

    public boolean validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
                return true;
        } catch (ExpiredJwtException ex) {
                System.out.println("JWT expired" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
                System.out.println("Token is null, empty or only whitespace" + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.out.println("JWT is invalid" + ex);
        } catch (UnsupportedJwtException ex) {
                System.out.println("JWT is not supported" + ex);
        } catch (SignatureException ex) {
                System.out.println("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret_key)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getId() + ", " + user.getEmail())
                .setIssuer("cj-visions")
                .setIssuedAt(new Date())
                .setExpiration(new Date(TOKEN_DURATION + System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }
}
