package com.example.OceanNews.Procedure;

import com.example.OceanNews.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
public class JwtTokenUtil {
    public static String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Constants.Token_Validity);
        return Jwts.builder()
                .setSubject(username) // You can set the subject to the user's username or ID
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,Constants.API_SECRET_KEY)
                .compact();
    }
}
