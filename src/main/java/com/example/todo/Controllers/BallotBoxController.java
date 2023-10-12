package com.example.todo.Controllers;

import com.example.todo.Entities.BallotBox;
import com.example.todo.Entities.Party;
import com.example.todo.Repositories.BallotBoxRepository;
import com.example.todo.Repositories.ConstituencyRepository;
import com.example.todo.Repositories.PartyRepository;
import com.example.todo.Repositories.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import com.example.todo.Entities.Voter;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BallotBoxController {
    private final BallotBoxRepository ballotBoxRepository;

    private final VoterRepository voterRepository;

    private final PartyRepository partyRepository;

    private final ConstituencyRepository constituencyRepository;

    @Autowired
    public BallotBoxController(BallotBoxRepository ballotBoxRepository, VoterRepository voterRepository, PartyRepository partyRepository, ConstituencyRepository constituencyRepository) {
        this.ballotBoxRepository = ballotBoxRepository;
        this.voterRepository = voterRepository;
        this.partyRepository = partyRepository;
        this.constituencyRepository = constituencyRepository;
    }

    @PostMapping("/vote")
    public String vote(@RequestBody String vote){
        List<String> voteData = List.of(vote.split(","));
        Optional<Voter> voterOptional = voterRepository.findById(Long.valueOf(voteData.get(0)));

        if (voterOptional.isPresent()) {
            if(!voterOptional.get().getVoted().equalsIgnoreCase("Y")) {
                Voter voter = voterOptional.get();
                voter.setVoted("Y");
                voterRepository.save(voter);
                BallotBox ballotBox = new BallotBox();
                ballotBox.setParty(partyRepository.findById(Long.valueOf(voteData.get(1))).get());
                ballotBox.setConstituency(voter.getConstituency());
                ballotBoxRepository.save(ballotBox);
            }else {
                return "Voted Already!";
            }
        }else {
            return "No Voter present";
        }
        return "Vote Accepted";
    }

    @GetMapping("/voteList")
    public HashMap voteList(){
        List<BallotBox> ballotBoxList = (List<BallotBox>) ballotBoxRepository.findAll();
        HashMap<String, HashMap<String,Integer>> voteMap = new HashMap<>();
        for (BallotBox ballotBox : ballotBoxList){
            if(!voteMap.containsKey(ballotBox.getConstituency().getConstituencyName())){
                HashMap<String,Integer> hashMap = new HashMap<>();
                hashMap.put(ballotBox.getParty().getPartyName(), 1);
                voteMap.put(ballotBox.getConstituency().getConstituencyName(),hashMap);
            }else{
                HashMap<String,Integer> hashMap = voteMap.get(ballotBox.getConstituency().getConstituencyName());
                if(hashMap.containsKey(ballotBox.getParty().getPartyName())){
                    Integer i = hashMap.get(ballotBox.getParty().getPartyName())+1;
                    hashMap.put(ballotBox.getParty().getPartyName(),i);
                    voteMap.put(ballotBox.getConstituency().getConstituencyName(),hashMap);
                }else{
                    hashMap.put(ballotBox.getParty().getPartyName(),1);
                    voteMap.put(ballotBox.getConstituency().getConstituencyName(),hashMap);
                }
            }
        }
        return voteMap;
    }

    @PostMapping("/getVotesByConstituency")
    public String getVotesByConstituency(@RequestBody String constituency){
        List<BallotBox> ballotBoxList = (List<BallotBox>) ballotBoxRepository.findAll();
        List<BallotBox> ballotBoxList1 = ballotBoxList.stream().filter(ballotBox -> ballotBox.getConstituency().getConstituencyName().equalsIgnoreCase(constituency)).collect(Collectors.toList());
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(BallotBox ballotBox : ballotBoxList1){
            if(hashMap.containsKey(ballotBox.getParty().getPartyName())){
                Integer i = hashMap.get(ballotBox.getParty().getPartyName())+1;
                hashMap.put(ballotBox.getParty().getPartyName(),i);
            }else{
                hashMap.put(ballotBox.getParty().getPartyName(),1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for(String key : hashMap.keySet()){
            stringBuilder.append(key+":"+hashMap.get(key)+",");
        }
        return String.valueOf(stringBuilder);
    }

    @GetMapping("/getParties")
    public String getParties(){
        List<Party> partyList = (List<Party>) partyRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder("");
        for(Party party : partyList){
            stringBuilder.append(party.getPartyName()+":"+party.getId()+",");
        }
        return String.valueOf(stringBuilder);
    }
}
