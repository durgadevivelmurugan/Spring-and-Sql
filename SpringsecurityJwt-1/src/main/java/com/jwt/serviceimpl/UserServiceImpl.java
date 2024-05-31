package com.jwt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.repository.StudentRepo;
@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	StudentRepo studentRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return studentRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("not found"));
	}

}
