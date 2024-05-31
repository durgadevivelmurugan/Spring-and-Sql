package com.jwt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.entity.AuthenticationResponse;
import com.jwt.entity.Student;
import com.jwt.repository.StudentRepo;

@Service
public class AuthenticationService {

	
	@Autowired
	StudentRepo repo;
	@Autowired
	JWTService jwtService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(Student stu) {
		Student student=new Student();
		student.setId(stu.getId());
		student.setUsername(stu.getUsername());
		student.setDepartment(stu.getDepartment());
		student.setPassword(encoder.encode(stu.getPassword()));
		student.setRole(stu.getRole());
		repo.save(student);
		
		String token=jwtService.generateToken(student);
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse login(Student student) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword()));
	repo.findByUsername(student.getUsername()).orElseThrow();
	String token=jwtService.generateToken(student);
	return new AuthenticationResponse(token);
	
	
	}
}
