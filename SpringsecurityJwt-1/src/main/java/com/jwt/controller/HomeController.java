package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.AuthenticationResponse;
import com.jwt.entity.Student;
import com.jwt.service.StudentService;
import com.jwt.serviceimpl.AuthenticationService;

@RestController
//don't give RestController(o/p->home.jsp)
public class HomeController {

	@Autowired
	AuthenticationService authenticationService;
	
	
//	@GetMapping("/")
//	public String home() {
//		return "home.jsp";
//	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody Student student){
		return ResponseEntity.ok(authenticationService.register(student));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse>login(@RequestBody Student student){
		return ResponseEntity.ok(authenticationService.login(student));
	}
	
	
}
