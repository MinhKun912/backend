package ra.jwt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ra.security.UserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthencation extends OncePerRequestFilter {
    private JwtTokenProvider provider;
    private UserDetailsService service;

    private String getJwtFromRequest(HttpServletRequest request){
        String barberToken=request.getHeader("Authorization");
        if (StringUtils.hasText(barberToken)&&barberToken.startsWith("bearer")){
            return barberToken.substring(7);
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt)&&provider.validate(jwt)){
                String userName = provider.userNameFromJwt(jwt);
                UserDetails userDetails = service.loadUserByUsername(userName);
                if (userDetails!=null){
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }catch (Exception ex){
            log.error("fail on set user authentication",ex);
        }
        filterChain.doFilter(request,response);
    }
    }

