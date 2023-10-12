package com.example.todo.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.todo.Entities.Candidate;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
}
