package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.service.StudentService;
@RestController
public class StudentController {

	@Autowired
	StudentService service;
	
	@GetMapping("/teach")
	public String teacher() {
		return "hi teacher";
		
	}
	@GetMapping("/student")
	public ResponseEntity<String> student(){
		return ResponseEntity.ok("Student");
		
	}
	@DeleteMapping("/delete/{id}")
	public Object deleteById(@PathVariable("id") int id) {
		return service.deleteById(id);
	}
}
