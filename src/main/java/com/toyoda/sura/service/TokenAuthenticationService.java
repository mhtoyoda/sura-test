package com.toyoda.sura.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    static final String SECRET = "um-segredo-token";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) {
        String JWT = generateToken(username);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    private static String generateToken(String username) {
        LocalDateTime localDateTime = LocalDateTime.now().plusYears(2);
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return JWT;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null && !token.equals("null")) {
            // faz parse do token
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }
}
