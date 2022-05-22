package com.example.spring.repository;

import com.example.spring.entity.Horses;
import org.springframework.data.repository.CrudRepository;

public interface HorseRepo extends CrudRepository<Horses, Integer> {
}
