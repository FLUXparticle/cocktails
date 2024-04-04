package com.example.cocktails.logic.data;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Comparable<Ingredient> {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(Ingredient other) {
        return name.compareTo(other.name);
    }

    // Konstruktoren, Getter und Setter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
