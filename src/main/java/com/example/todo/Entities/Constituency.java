package com.example.todo.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "constituency")
public class Constituency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "constituency_name")
    private String constituencyName;

    @OneToMany(mappedBy = "constituency", cascade = CascadeType.ALL)
    private List<Voter> voters;

    // Constructors, getters, and setters (omitted for brevity)
}
