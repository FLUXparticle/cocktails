package com.example.cocktails.logic.repository;

import com.example.cocktails.logic.data.*;

import javax.ejb.*;
import javax.enterprise.inject.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.Transactional.*;
import java.util.*;

@Stateless
public class CocktailRepository {

    @PersistenceContext(unitName = "cocktails")
    private EntityManager em;

    public List<Cocktail> findAll() {
        return em.createQuery("SELECT c FROM Cocktail c", Cocktail.class).getResultList();
    }

    public Cocktail findByID(Long cocktailId) {
        return em.find(Cocktail.class, cocktailId);
    }

    public Collection<Cocktail> findByIngredientIds(Collection<Long> ingredientIds) {
        return em.createQuery("SELECT DISTINCT c FROM Cocktail c JOIN c.instructions i WHERE i.key.ingredient.id IN :ingredientIds", Cocktail.class)
                .setParameter("ingredientIds", ingredientIds)
                .getResultList();
    }

    public List<Cocktail> findByNameContains(String query) {
        return em.createQuery("SELECT c FROM Cocktail c WHERE c.name LIKE :query", Cocktail.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

}
