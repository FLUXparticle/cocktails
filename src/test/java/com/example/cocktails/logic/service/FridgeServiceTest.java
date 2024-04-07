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
@Classes({FridgeService.class, CocktailService.class, CocktailRepository.class, IngredientRepository.class})
public class FridgeServiceTest {

    @EJB
    private FridgeService service;

    @Test
    public void newFridgeService() {
        assertNotNull(service);
    }

    @Test
    public void CRUD() {
        Collection<FridgeIngredient> fridgeIngredients0 = service.getFridgeIngredients();
        assertEquals(0, countInFridge(fridgeIngredients0));

        service.updateFridgeIngredient(29, true);

        Collection<FridgeIngredient> fridgeIngredients1 = service.getFridgeIngredients();
        assertEquals(1, countInFridge(fridgeIngredients1));

        service.updateFridgeIngredient(29, false);

        Collection<FridgeIngredient> fridgeIngredients2 = service.getFridgeIngredients();
        assertEquals(0, countInFridge(fridgeIngredients2));
    }

    @Test
    public void getPossibleCocktails() {
        Collection<Cocktail> possibleCocktailsBefore = service.getPossibleCocktails();
        assertEquals(0, possibleCocktailsBefore.size());

        service.updateFridgeIngredient(8, true);
        service.updateFridgeIngredient(31, true);

        Collection<Cocktail> possibleCocktailsAfter = service.getPossibleCocktails();
        assertEquals(1, possibleCocktailsAfter.size());
    }

    @Test
    public void getShoppingList() {
        Collection<Ingredient> ingredientsBefore = service.getShoppingList();
        assertEquals(61, ingredientsBefore.size());

        service.updateFridgeIngredient(29, true);

        Collection<Ingredient> ingredientsAfter = service.getShoppingList();
        assertEquals(60, ingredientsAfter.size());
    }

    private int countInFridge(Collection<FridgeIngredient> fridgeIngredients) {
        int count = 0;
        for (FridgeIngredient ingredient : fridgeIngredients) {
            if (ingredient.isInFridge()) {
                count++;
            }
        }
        return count;
    }

}
