package enset.proxy.service.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Expose-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods ", "*");
        response.addHeader("Access-Control-Expose-Headers", "*");

        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else if(request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        else {


            String jwt = request.getHeader(SecurityParams.JWT_HEADER_NAME);
            if(jwt==null || !jwt.startsWith(SecurityParams.JWT_HEADER_PREFIX)){
                filterChain.doFilter(request, response);
                return;
            }
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SecurityParams.JWT_SECRECT)).build();
            String jwtString = jwt.substring(SecurityParams.JWT_HEADER_PREFIX.length());
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtString);

            String username = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            roles.forEach(role->{
                authorities.add(new SimpleGrantedAuthority(role));
            });
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username,null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            filterChain.doFilter(request, response);
        }

    }

}
