package com.example.cocktails.logic.service;

import com.example.cocktails.logic.data.*;
import com.example.cocktails.logic.repository.*;

import javax.ejb.*;
import java.util.*;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;

@Stateless
public class CocktailService {

    @EJB
    private CocktailRepository cocktailRepository;

    @EJB
    private IngredientRepository ingredientRepository;

    public Collection<Cocktail> getAllCocktails() {
        return cocktailRepository.findAll();
    }

    public Collection<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Collection<Cocktail> getAllCocktailsWithIngredient(Long ingredientsId) {
        return getAllCocktailsWithIngredients(Collections.singleton(ingredientsId), false);
    }

    public Cocktail getCocktailWithID(Long id) {
        return cocktailRepository.findByID(id);
    }

    public Ingredient getIngredientWithID(Long id) {
        return ingredientRepository.findByIDs(List.of(id)).get(0);
    }

    public Collection<Cocktail> search(String query) {
        Collection<Cocktail> cocktailsWithName = cocktailRepository.findByNameContains(query);

        Collection<Ingredient> ingredientsWithName = ingredientRepository.findByNameContains(query);

        Set<Long> ingredientIDs = ingredientsWithName.stream()
                .map(Ingredient::getId)
                .collect(toSet());

        Collection<Cocktail> cocktailsWithIngredients = getAllCocktailsWithIngredients(ingredientIDs, false);

        Set<Cocktail> result = new TreeSet<>(cocktailsWithIngredients);
        result.addAll(cocktailsWithName);

        return result;
    }

    public Collection<Cocktail> getAllCocktailsWithIngredients(Collection<Long> ingredientIDs, boolean withInstructions) {
        if (ingredientIDs.isEmpty()) {
            return emptyList();
        } else {
            return cocktailRepository.findByIngredientIds(ingredientIDs);
        }
    }

}
