package com.example.todo.Repositories;

import com.example.todo.Entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    // You can add custom query methods here if needed
}