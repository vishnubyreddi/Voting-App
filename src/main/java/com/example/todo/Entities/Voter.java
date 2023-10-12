package com.example.todo.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "voter")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_id")
    private Long voterId;

    @Column(name = "voter_name")
    private String voterName;

    @Column(name = "voter_age")
    private int voterAge;

    @ManyToOne
    @JoinColumn(name = "constituency_id")
    private Constituency constituency;

    private String voted;

}
