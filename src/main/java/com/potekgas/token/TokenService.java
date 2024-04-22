package com.potekgas.token;

import com.potekgas.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TokenService {
    private static final String SECRET_KEY = "ykt5IDEFHjn4Qwxp3MUmxIjpwGlFbi9f8zOoyIp75+XaKY5dTs5wXH6y3imxePS7tOrvh4GKW5pwqHTxy/aGWw==";

//    public String generateToken(User user) {
//        Date now = new Date();
//        // Menghitung waktu kedaluwarsa menjadi 90 menit dari waktu sekarang
//        long expiryMilliseconds = now.getTime() + 60 * 60 * 1000;
//        Date expiryDate = new Date(expiryMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(Long.toString(user.getId_user()))
//                .claim("id", user.getId_user())
//                .claim("name", user.getNama_user())
//                .claim("role", user.getRole())
//                // tambahkan claim untuk expired date
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//
//    public ArrayList getTokenInfo(String token) {
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        Long userId = claims.get("id", Long.class); // Mengambil ID pengguna dalam format Long
//        String name = claims.get("name", String.class); // Mengambil nama pengguna dalam format String
//        Long role = claims.get("role", Long.class); // Mengambil peran pengguna dalam format String
//
//        ArrayList<Object> tokenInfo = new ArrayList<>();
//        tokenInfo.add(userId);
//        tokenInfo.add(name);
//        tokenInfo.add(role);
//
//        return tokenInfo;
//    }

    public String generateToken(User user) {
        Date now = new Date();
        // Menghitung waktu kedaluwarsa menjadi 90 menit dari waktu sekarang
        long expiryMilliseconds = now.getTime() + 45 * 60 * 1000;
        Date expiryDate = new Date(expiryMilliseconds);

        return Jwts.builder()
                .setSubject(Long.toString(user.getId_user()))
                .claim("id", user.getId_user())
                .claim("name", user.getNama_user())
                .claim("role", user.getRole())
                // tambahkan claim untuk expired date
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public ArrayList getTokenInfo(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            Long userId = claims.get("id", Long.class); // Mengambil ID pengguna dalam format Long
            String name = claims.get("name", String.class); // Mengambil nama pengguna dalam format String
            Long role = claims.get("role", Long.class); // Mengambil peran pengguna dalam format Long

            // Memeriksa apakah signature valid
            boolean signatureValid = true;
            // Memeriksa apakah token expired
            boolean tokenExpired = claims.getExpiration().before(new Date());

            ArrayList<Object> tokenInfo = new ArrayList<>();
            tokenInfo.add(userId);
            tokenInfo.add(name);
            tokenInfo.add(role);
            tokenInfo.add(signatureValid);
            tokenInfo.add(tokenExpired);

            return tokenInfo;
        } catch (Exception e) {
            // Jika terjadi kesalahan dalam verifikasi token
            ArrayList<Object> errorInfo = new ArrayList<>();
            errorInfo.add("error");
            return errorInfo;
        }
    }

}
