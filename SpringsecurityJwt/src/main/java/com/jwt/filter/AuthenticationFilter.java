package com.jwt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.serviceImpl.JwtService;
import com.jwt.serviceImpl.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserServiceImpl serviceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader=request.getHeader("Authorization");
		
		if(authHeader==null|| !authHeader.startsWith(" Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token=authHeader.substring(7);
		String username =jwtService.extractUsername(token);
		
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails= serviceImpl.loadUserByUsername(username);
		
		if(jwtService.isValid(token, userDetails)) {
			UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
		}
	}
	filterChain.doFilter(request, response);
	
	

		
	}

}
