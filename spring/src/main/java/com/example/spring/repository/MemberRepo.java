package com.example.spring.repository;

import com.example.spring.entity.Members;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Members, Integer> {
    Iterable<Members> findByIDRA(int id);
}
