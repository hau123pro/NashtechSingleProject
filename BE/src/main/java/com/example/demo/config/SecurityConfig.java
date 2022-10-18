package com.example.demo.config;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.impl.CustomerDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	CustomerDetailService customer;
	
	@Autowired
	JwtTokenUtil jwtUtil;
	
	 @Bean
	    public ModelMapper modelMapper() {
	        ModelMapper mapper = new ModelMapper();
	        mapper.getConfiguration()
	                .setMatchingStrategy(MatchingStrategies.STRICT);
	        return mapper;
	    }

	// cấu hình bean cách thức mã hóa ở đây là Bcrypt
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeHttpRequests()
			//cho phép các link trong ".." được xác thực 
			.antMatchers("/api/v1/author/**").permitAll()
			.antMatchers("/api/v1/registration**").permitAll()
			.antMatchers("/api/v1/**").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
	        .antMatchers("/api-docs/**").permitAll()
			// còn lại phải xác thực bằng cách login
			.anyRequest().authenticated();
			
			http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(),jwtUtil));
			http.addFilterBefore(new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
			;
	}
	// Xác thực tài khoản đăng nhập bằng dùng userdetail từ customerdetailservice
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	    .userDetailsService(customer).passwordEncoder(passwordEncoder());
//	      .inMemoryAuthentication()
//	        .withUser("user").password(passwordEncoder().encode("123")).roles("USER");
	  }
	
}
	