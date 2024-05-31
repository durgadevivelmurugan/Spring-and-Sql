package com.example.repository;

import java.util.List;

import com.example.entity.User;

public interface SearchRepo {
	List<User> findByText(String text);
}
