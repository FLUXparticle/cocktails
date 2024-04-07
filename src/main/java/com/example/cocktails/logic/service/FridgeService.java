package com.example.cocktails.logic.service;

import com.example.cocktails.logic.data.*;

import javax.ejb.*;
import java.util.*;

import static java.util.stream.Collectors.*;

@Stateful
public class FridgeService {

    @EJB
    private CocktailService cocktailService;

    private final Set<Long> fridge = new HashSet<>();

    public void updateFridgeIngredient(long ingredientID, boolean inFridge) {
        if (inFridge) {
            fridge.add(ingredientID);
        } else {
            fridge.remove(ingredientID);
        }
    }

    public Collection<FridgeIngredient> getFridgeIngredients() {
        return cocktailService.getAllIngredients().stream()
                .map(ingredient -> {
                    boolean inFridge = fridge.contains(ingredient.getId());
                    return new FridgeIngredient(ingredient, inFridge);
                })
                .collect(toList());
    }

    public Collection<Cocktail> getPossibleCocktails() {
        Collection<Cocktail> allCocktailsWithIngredients = cocktailService.getAllCocktailsWithIngredients(fridge, true);

        return allCocktailsWithIngredients.stream()
                .filter(cocktail -> cocktail.getInstructions().stream()
                        .map(Instruction::getIngredient)
                        .map(Ingredient::getId)
                        .allMatch(fridge::contains)
                )
                .collect(toList());
    }

    public Set<Ingredient> getShoppingList() {
        Collection<Ingredient> allIngredients = cocktailService.getAllIngredients();

        Set<Ingredient> missing = new TreeSet<>(allIngredients);
        missing.removeIf(ingredient -> fridge.contains(ingredient.getId()));

        return missing;
    }

}
