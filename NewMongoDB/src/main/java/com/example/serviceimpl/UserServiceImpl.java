package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.User;
import com.example.repository.UserRepo;
import com.example.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo repo;
	

	@Override
	@Transactional
	public List<User> getStudents() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public User saveStudent(User user)  {
    return repo.save(user);
	}

	@Override
	@Transactional
	public User deleteStudent(int id) {
		User user=repo.findById(id).get();
		repo.delete(user);
		return user;
	}

	@Override
	@Transactional
	public User updateStudent(int id, User user) {
		User updateuser=repo.findById(id).get();
		updateuser.setName(user.getName());
		updateuser.setAge(user.getAge());
		updateuser.setPlace(user.getPlace());
		 repo.save(updateuser);
		 return updateuser;
	}



}
