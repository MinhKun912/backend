package ra.jwt;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.security.UserDetails;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${ra.jwt.secret}")
    private String JWT_SECRET;
    @Value("${ra.jwt.expiration}")
    private int JWT_EXPIRATION;

    public String generateToken(UserDetails userDetails){
        Date now=new Date();
        Date dataExpired= new Date(now.getTime()+JWT_EXPIRATION);
         return Jwts.builder().setSubject(userDetails.getUsername())
                 .setIssuedAt(now)
                 .setExpiration(dataExpired)
                 .signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
    }
    public String userNameFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException exception){

        }catch (ExpiredJwtException exception){

        }catch (UnsupportedJwtException e){

        }catch (IllegalArgumentException e){

        }
        return false;
    }

}
