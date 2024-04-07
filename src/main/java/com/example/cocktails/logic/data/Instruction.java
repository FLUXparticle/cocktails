package com.example.cocktails.logic.data;

import javax.persistence.*;

@Entity
@Table(name = "instructions")
public class Instruction {

    @EmbeddedId
    private InstructionKey key;

    @Column(name = "amount_cl")
    private Integer amountCl;

    // Konstruktoren, Getter und Setter

    public Ingredient getIngredient() {
        return key.getIngredient();
    }

    public Integer getAmountCl() {
        return amountCl;
    }

}
