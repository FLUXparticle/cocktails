package com.example.cocktails.logic.repository;

import org.apache.openejb.junit.*;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Descriptor;
import org.apache.openejb.testing.Descriptors;
import org.junit.*;
import org.junit.runner.*;

import javax.ejb.*;

@RunWith(ApplicationComposer.class)
@Descriptors({
        @Descriptor(name = "persistence.xml", path = "META-INF/persistence.xml"),
        @Descriptor(name = "resources.xml", path = "src/main/webapp/WEB-INF/resources.xml"),
})
@Classes({CocktailRepositoryDAO.class, CocktailRepository.class})
public class CocktailRepositoryTest {

    @EJB
    private CocktailRepositoryDAO dao;

    @Test
    public void newCocktailRepository() {
        dao.newCocktailRepository();
    }

    @Test
    public void findAll() {
        dao.findAll();
    }

    @Test
    public void findByID() {
        dao.findByID();
    }

    @Test
    public void findByNameContains() {
        dao.findByNameContains();
    }

}
