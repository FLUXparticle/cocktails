package com.example.cocktails.logic.repository;

import com.example.cocktails.logic.data.*;
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
@Classes({IngredientRepository.class})
public class IngredientRepositoryTest {

    @EJB
    private IngredientRepository repository;

    @Test
    public void newIngredientRepository() {
        assertNotNull(repository);
    }

    @Test
    public void findAll() {
        List<Ingredient> ingredients = repository.findAll();
        assertEquals(61, ingredients.size());
    }

    @Test
    public void findByNameContains() {
        List<Ingredient> ingredients = repository.findByNameContains("sirup");
        assertEquals(24, ingredients.size());
    }

}