package com.example.cocktails.logic.data;

import javax.persistence.*;
import java.sql.*;
import java.util.*;

public class CocktailData {

    private static final EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("cocktails");

    private static final EntityManager EM = EM_FACTORY.createEntityManager();

    public static List<Cocktail> getCocktails() throws SQLException {
        TypedQuery<Cocktail> query = EM.createQuery("SELECT c FROM Cocktail c", Cocktail.class);
        return query.getResultList();
    }

    public static void main(String[] args) throws SQLException {
        List<Cocktail> cocktails = getCocktails();

        for (Cocktail cocktail : cocktails) {
            System.out.println("Cocktail ID: " + cocktail.getId() + ", Name: " + cocktail.getName());
        }
    }

}
