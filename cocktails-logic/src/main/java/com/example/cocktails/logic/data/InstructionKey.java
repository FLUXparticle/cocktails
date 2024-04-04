package com.example.cocktails.logic.data;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Embeddable
public class InstructionKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cocktail_id", referencedColumnName = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionKey that = (InstructionKey) o;
        return Objects.equals(cocktail.getId(), that.cocktail.getId()) && Objects.equals(ingredient.getId(), that.ingredient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktail.getId(), ingredient.getId());
    }

    // Getter und Setter

    public Cocktail getCocktail() {
        return cocktail;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

}
