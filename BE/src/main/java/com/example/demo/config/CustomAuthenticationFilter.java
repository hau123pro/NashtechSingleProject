package com.example.demo.config;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private static final String APPLICATION_JSON_VALUE = "application/json";
	private final AuthenticationManager authenticationManager;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
		// TODO Auto-generated constructor stub
		this.authenticationManager=authenticationManager;
		this.jwtTokenUtil=jwtTokenUtil;
	}
	
	
	private final JwtTokenUtil jwtTokenUtil;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String pass=request.getParameter("password");
		log.info("Username is ",username);
		log.info("Password is ",pass);
		 System.out.print(username);
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, pass);
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		User user=(User)authResult.getPrincipal();
		System.out.println(user.getUsername());
		Algorithm algorithm=Algorithm.HMAC256(jwtTokenUtil.getSecret());
		String token=JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
						.withIssuer(request.getRequestURL().toString())
						.sign(algorithm);
		System.out.println(this.jwtTokenUtil.generateToken(user));
		response.setHeader("token",  token);
		response.addHeader("Access-Control-Expose-Headers", "token");
		Map<String,String> tokens=new HashMap<>();
		tokens.put("token", token);
		response.setContentType(APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
	
}
