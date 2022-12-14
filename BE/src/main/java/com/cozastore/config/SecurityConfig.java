package com.cozastore.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cozastore.filter.AuthorizationFilter;
import com.cozastore.utils.JwtTokenUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {


	@Autowired
	JwtTokenUtil jwtUtil;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeHttpRequests()
 
				.antMatchers("/swagger-ui.html/**").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/api-docs/**").permitAll()
				.antMatchers("/v1/author/**").permitAll()
				.antMatchers("/v1/client/**").hasAuthority("ROLE_USER")
				.antMatchers("/v1/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/v1/registration**").permitAll()
				
				.and()
				.addFilterBefore(new AuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
				;
		
		return http.build();
	}
	


}
