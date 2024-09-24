//package com.nexjot.nexjot.api.security.service;
//
////import lombok.Value;
//
//import com.nexjot.nexjot.api.model.User;
//import com.nexjot.nexjot.api.security.model.AuthUser;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
////import lombok.Value;
//
//import javax.crypto.SecretKey;
//
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.function.Function;
//
////
//@Service
//public class JwtService {
//    @Value("${security.jwt.secret-key}")
//    private String secretKey;
//
//    private SecretKey getSigningKey() {
//        byte[] bytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(bytes);
//
//    }
//    private Claims extractAllClaims(String jwtToken) {
//        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(jwtToken).getPayload();
//    }
//
//    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(jwtToken);
//        return claimsResolver.apply(claims);
//    }
//
//    public String extractUsername(String jwtToken) {
//        return extractClaim(jwtToken, Claims::getSubject);
//    }
//
//    private Date extractExpiration(String jwtToken) {
//        return extractClaim(jwtToken, Claims::getExpiration);
//    }
//
//    private boolean isTokenExpired(String jwtToken) {
//        return extractExpiration(jwtToken).before(new Date());
//    }
//
//    public boolean isTokenValid(String jwtToken, AuthUser authUser) {
//        final String username = extractUsername(jwtToken);
//        return username.equals(authUser.getUsername()) && !isTokenExpired(jwtToken);
//    }
//
//    private String createToken(String username) {
//        return Jwts.builder()
//                .subject(username)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(getSigningKey())
//                .compact();
//    }
//
//    public String generateToken(User u) {
//        return createToken(u.getUsername());
//    }
//}
