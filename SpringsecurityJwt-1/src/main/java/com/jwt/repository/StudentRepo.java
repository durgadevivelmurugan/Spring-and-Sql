package com.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entity.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
Optional<Student> findByUsername(String username);
}
