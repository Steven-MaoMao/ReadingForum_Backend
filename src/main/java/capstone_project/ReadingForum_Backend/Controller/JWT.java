package capstone_project.ReadingForum_Backend.Controller;

import io.jsonwebtoken.*;

import java.util.Date;

public class JWT {
    private static final String signature = "ReadingForum_Backend";

    //  加密token
    public static String createToken(String username) {
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", username)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
    }

    //  解密token
    public static String parseToken(String jwtToken) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(jwtToken);
        Claims body = claimsJws.getBody();
        return body.get("username").toString();
    }
}
