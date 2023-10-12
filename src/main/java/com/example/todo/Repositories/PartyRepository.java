package com.example.todo.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.todo.Entities.Party;
public interface PartyRepository extends CrudRepository<Party, Long> {
}
