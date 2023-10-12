package com.example.todo.Controllers;


import com.example.todo.Entities.Constituency;
import com.example.todo.Repositories.ConstituencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ConstituencyController {

    private final ConstituencyRepository constituencyRepository;

    @Autowired
    public ConstituencyController(ConstituencyRepository constituencyRepository) {
        this.constituencyRepository = constituencyRepository;
    }



    @GetMapping("/constituencies")
    public List<Constituency> constituencies(){
        return (List<Constituency>) constituencyRepository.findAll();
    }

    @PostMapping("/addConstituency")
    public String addConstituency(@RequestBody Constituency constituency){
        constituencyRepository.save(constituency);
        return "constituency is successfully added";
    }

    @GetMapping("/constituenciesString")
    public String constituenciesString(){

        List<Constituency> constituencies = (List<Constituency>) constituencyRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder("");
        for(Constituency constituency : constituencies){
            stringBuilder.append(constituency.getConstituencyName()+",");
        }
        return String.valueOf(stringBuilder);
    }

}
