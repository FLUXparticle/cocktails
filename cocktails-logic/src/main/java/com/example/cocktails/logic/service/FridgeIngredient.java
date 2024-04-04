package com.example.cocktails.logic.service;

import com.example.cocktails.logic.data.*;

public class FridgeIngredient {

    private Long id;

    private String name;

    private boolean inFridge;

    // Konstruktoren, Getter und Setter
    public FridgeIngredient() {
        // empty
    }

    public FridgeIngredient(Ingredient ingredient, boolean inFridge) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.inFridge = inFridge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInFridge() {
        return inFridge;
    }

    public void setInFridge(boolean inFridge) {
        this.inFridge = inFridge;
    }

}
