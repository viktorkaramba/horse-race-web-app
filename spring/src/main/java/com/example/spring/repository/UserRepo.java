package com.example.spring.repository;

import com.example.spring.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
