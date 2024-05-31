package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.AuthenticationResponse;
import com.jwt.entity.User;
import com.jwt.serviceImpl.AuthenticationService;

@RestController
public class UserController {

	@Autowired
	AuthenticationService authenticationService;
	
	@GetMapping("/getuser")
	public String getuser() {
		return "Hello world";
	}
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
		return ResponseEntity.ok(authenticationService.register(user));
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse>login(@RequestBody User user){
		return ResponseEntity.ok(authenticationService.authenticate(user));
	}
	@GetMapping("/admin")
	public ResponseEntity<String> getadmin(){
		return ResponseEntity.ok("Hello,Admin");
	}
	@GetMapping("/user")
	public ResponseEntity<String> getUser(){
		return ResponseEntity.ok("Hello,User");
	}
}
