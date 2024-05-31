package com.jwt.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entity.Student;
import com.jwt.repository.StudentRepo;
import com.jwt.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepo repo;
	
	@Override
	public Object deleteById(int id) {
		Optional<Student>stu= repo.findById(id);
		if(stu.isPresent()) 
		{
			repo.deleteById(id);
			return "deleted";
		}
		else 
		{
			return "doesn't exist";

		}
	}

}
