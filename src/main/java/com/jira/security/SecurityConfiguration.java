package com.jira.security;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jira.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	UserDetailServiceImpl detailServiceImpl;
	
	@Bean
	public SecurityFilterChain  chain(HttpSecurity httpSecurity) {
		
		httpSecurity.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
				.requestMatchers("/api/users/**","/api/department/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				).httpBasic(Customizer.withDefaults())
		;
		return httpSecurity.build();
		
	}
	
	@Bean
	public AuthenticationProvider authenticationManager() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(detailServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return daoAuthenticationProvider;
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
