package com.jwt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.serviceimpl.JWTService;
import com.jwt.serviceimpl.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter{

	//responsible for handling JWT related operations like token validation, extraction of user details from tokens, etc.
	@Autowired
	JWTService jwtService;
	
	// deals with user-related operations.
	@Autowired
	UserServiceImpl impl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader=request.getHeader("Authorization");
		
		//
		if(authHeader==null||authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token=authHeader.substring(7);
		String username=jwtService.extractUsername(token);
		
		//username is not null and there is no existing authentication in the SecurityContextHolder, it loads the UserDetails from the database using UserServiceImpl.
		if(username !=null &&SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails=impl.loadUserByUsername(username);		
		if(jwtService.isValid(token, userDetails)) {
			UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(token, null,userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
		}
	}
	filterChain.doFilter(request, response);
		}
		
}
