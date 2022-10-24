package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JWindow;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import utils.JwtTokenUtil;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter{
	
	private static final String APPLICATION_JSON_VALUE = "application/json";
	private final JwtTokenUtil jwtTokenUtil;
	public AuthorizationFilter(JwtTokenUtil jwtTokenUtil) {
		// TODO Auto-generated constructor stub
		this.jwtTokenUtil=jwtTokenUtil;
	}
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getServletPath().equals("/login")) {
			filterChain.doFilter(request, response);
		}
		else {
			String authorizationHeader=request.getHeader("Authorization");
			if(authorizationHeader!=null&& authorizationHeader.startsWith("Bearer ")) {
				try {
				String token=authorizationHeader.substring("Bearer ".length());
				System.out.println(token);
//				SignatureAlgorithm.HS512
//				Algorithm algorithm=Algorithm.HMAC256(jwtTokenUtil.getSecret());
//				JWTVerifier verifier=JWT.require(algorithm).build();
//				DecodedJWT decodedJWT=verifier.verify(token);
				String email=jwtTokenUtil.getEmailFromToken(token);
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_USER");
				authorities.add(authority);
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(email, null,authorities);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				filterChain.doFilter(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					response.setHeader("error", e.getMessage());
					response.setStatus(403);
					Map<String,String> errors=new HashMap<>();
					errors.put("error-msg", e.getMessage());
					response.setContentType(APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), errors);
				}
			}
			else filterChain.doFilter(request, response);
		}
	}

}
