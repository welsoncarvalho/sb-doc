package com.test.sbdocker.repository;

import com.test.sbdocker.model.Monster;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends CrudRepository<Monster, String> {

}