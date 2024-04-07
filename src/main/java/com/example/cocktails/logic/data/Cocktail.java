package com.example.cocktails.logic.data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cocktails")
public class Cocktail implements Comparable<Cocktail> {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id")
    private Long id;

    @Column(name = "name")
    private String name;

    // TODO LAZY
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_id", referencedColumnName = "cocktail_id")
    private List<Instruction> instructions;

    @Override
    public int compareTo(Cocktail other) {
        return name.compareTo(other.name);
    }

    // Konstruktoren, Getter und Setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

}
