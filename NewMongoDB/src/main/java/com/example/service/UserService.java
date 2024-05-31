package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {

	public List<User> getStudents();
	public User saveStudent(User user);
	public User deleteStudent(int id);
	public User updateStudent(int id,User user);
//	List<User> findByText(String text);
}
