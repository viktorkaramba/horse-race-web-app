package com.example.spring.repository;

import com.example.spring.entity.Races;
import org.springframework.data.repository.CrudRepository;

public interface RaceRepo extends CrudRepository<Races, Integer> {

}
