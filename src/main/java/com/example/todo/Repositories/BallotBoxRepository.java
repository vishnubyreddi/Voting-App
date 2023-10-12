package com.example.todo.Repositories;

import com.example.todo.Entities.BallotBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotBoxRepository extends CrudRepository<BallotBox,Long>{
}
