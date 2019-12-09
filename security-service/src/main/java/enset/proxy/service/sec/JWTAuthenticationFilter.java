package enset.proxy.service.sec;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import enset.proxy.service.entities.AppUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
		}catch (Exception e) {			
			throw new RuntimeException(e);
		}
		}
		
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		List<String> roles = new ArrayList<String>();
		authResult.getAuthorities().forEach(r->{
			roles.add(r.getAuthority());
		});
		String jwt = JWT.create()
								.withIssuer(request.getRequestURI())
								.withSubject(user.getUsername())
								.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
								.withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.JWT_EXPIRATION))
								.sign(Algorithm.HMAC256(SecurityParams.JWT_SECRECT));
		response.addHeader(SecurityParams.JWT_HEADER_NAME,jwt);
		System.out.println("done");
	}

}
