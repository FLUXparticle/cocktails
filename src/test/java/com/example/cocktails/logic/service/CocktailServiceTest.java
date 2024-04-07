package com.example.cocktails.logic.service;

import com.example.cocktails.logic.data.*;
import com.example.cocktails.logic.repository.*;
import org.apache.openejb.junit.*;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Descriptor;
import org.apache.openejb.testing.Descriptors;
import org.junit.*;
import org.junit.runner.*;

import javax.ejb.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(ApplicationComposer.class)
@Descriptors({
        @Descriptor(name = "persistence.xml", path = "META-INF/persistence.xml"),
        @Descriptor(name = "resources.xml", path = "src/main/webapp/WEB-INF/resources.xml"),
})
@Classes({CocktailService.class, CocktailRepository.class, IngredientRepository.class})
public class CocktailServiceTest {

    @EJB
    private CocktailService service;

    @Test
    public void newCocktailService() {
        assertNotNull(service);
    }

    @Test
    public void getAllCocktails() {
        Collection<Cocktail> cocktails = service.getAllCocktails();
        assertEquals(69, cocktails.size());
        for (Cocktail cocktail : cocktails) {
            // TODO assertTrue(cocktail.getInstructions().isEmpty());
        }
    }

    @Test
    public void getAllIngredients() {
        Collection<Ingredient> ingredients = service.getAllIngredients();
        assertEquals(61, ingredients.size());
    }

    @Test
    public void getCocktailInstructions() {
        Cocktail cocktail = service.getCocktailWithID(24L);
        assertNotNull(cocktail);
        assertEquals("Milkshake", cocktail.getName());
        // TODO assertEquals(4, cocktail.getInstructions().size());
    }

    @Test
    public void getCocktailsWithIngredients() {
        Collection<Cocktail> cocktails = service.getAllCocktailsWithIngredient(29L);
        assertEquals(6, cocktails.size());
        for (Cocktail cocktail : cocktails) {
            // TODO assertTrue(cocktail.getInstructions().isEmpty());
        }
    }

    @Test
    public void search() {
        Collection<Cocktail> cocktails = service.search("Milch");
        assertEquals(7, cocktails.size());
        for (Cocktail cocktail : cocktails) {
            // TODO assertTrue(cocktail.getInstructions().isEmpty());
        }
    }

}
