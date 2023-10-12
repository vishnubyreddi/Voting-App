package com.example.todo.Repositories;

import com.example.todo.Entities.Constituency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstituencyRepository extends CrudRepository<Constituency,Long> {
}
