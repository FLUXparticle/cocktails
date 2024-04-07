package com.example.cocktails.logic.repository;

import com.example.cocktails.logic.data.*;

import javax.ejb.*;
import javax.persistence.*;
import java.util.List;

@Stateless
public class IngredientRepository {

    @PersistenceContext(unitName = "cocktails")
    private EntityManager em;

    public List<Ingredient> findAll() {
        return em.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
    }

    public List<Ingredient> findByIDs(List<Long> ingredientIds) {
        return em.createQuery("SELECT i FROM Ingredient i WHERE i.id IN :ids", Ingredient.class)
                .setParameter("ids", ingredientIds)
                .getResultList();
    }

    public List<Ingredient> findByNameContains(String query) {
        return em.createQuery("SELECT i FROM Ingredient i WHERE i.name LIKE :query", Ingredient.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

}
