package com.example.cocktails.logic.repository;

import com.example.cocktails.logic.data.*;

import javax.ejb.*;
import javax.transaction.*;

import java.util.*;

import static org.junit.Assert.*;

@Stateless
@Transactional
public class CocktailRepositoryDAO {

    @EJB
    private CocktailRepository repository;

    public void newCocktailRepository() {
        assertNotNull(repository);
    }

    public void findAll() {
        List<Cocktail> cocktails = repository.findAll();
        assertEquals(69, cocktails.size());
    }

    public void findByID() {
        Cocktail cocktail = repository.findByID(24L);
        assertEquals("Milkshake", cocktail.getName());
        assertEquals(4, cocktail.getInstructions().size());
    }

    public void findByNameContains() {
        List<Cocktail> cocktails = repository.findByNameContains("Milk");
        assertEquals(3, cocktails.size());
    }

}
