package com.example.todo.Controllers;

import com.example.todo.Entities.Candidate;
import com.example.todo.Repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CandidateController {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/registerCandidate")
    public String registerCandidate(@RequestBody Candidate candidate){
        try {
            candidateRepository.save(candidate);
        }catch (Exception e){
            return " Already a candidate registered from the party in the same constituency";
        }
        return "Successful";
    }

    @GetMapping("/")
    public List<Candidate> getAllCandidates(){
        return (List<Candidate>) candidateRepository.findAll();
    }

}
