package com.jwt.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.filter.AuthenticationFilter;
import com.jwt.serviceimpl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class Securityconfig {

	@Autowired
	AuthenticationFilter authenticationFilter;
	
	@Autowired
	UserServiceImpl impl;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req-> req.requestMatchers("/register/**","/login","/delete/**").permitAll()
						.requestMatchers("/student/**").hasAnyAuthority("USER")
						.requestMatchers("/teach").hasAnyAuthority("ADMIN")
					.anyRequest().authenticated())
				
				.userDetailsService(impl)
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager(); 
	}
}
//	@Bean 
//	public UserDetailsService userDetailsService() {
//		
//		List<UserDetails> userone=new ArrayList<UserDetails>();
//		 userone.add(User.withDefaultPasswordEncoder().username("durga").password("1234").roles("USER").build());
//		return new InMemoryUserDetailsManager(userone);
//		
//	}

