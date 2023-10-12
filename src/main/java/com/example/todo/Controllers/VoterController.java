package com.example.todo.Controllers;

import com.example.todo.Entities.BallotBox;
import com.example.todo.Repositories.VoterRepository;
import com.example.todo.Entities.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VoterController {

    private final VoterRepository voterRepository; // Declare the repository as a field

    @Autowired // Inject the VoterRepository using constructor injection
    public VoterController(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @GetMapping("/getVoters")
    public List<Voter> voters(){
        List<Voter> voterList= new ArrayList<>();
        try {
            voterList = voterRepository.findAll();
        }catch (Exception E){
            E.getCause();
        }
        return voterList;
    }

    @PostMapping("/addVoter")
    public String addVoter(@RequestBody Voter voter){
        Voter voter1 = voterRepository.save(voter);
        return "Voter created with Id :"+voter1.getVoterId();
    }


 }
