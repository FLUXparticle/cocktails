package com.example.cocktails.logic.controller;

import com.example.cocktails.logic.data.*;
import com.example.cocktails.logic.service.*;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Stateless
@Path("/cocktails")
public class CocktailController {

    @EJB
    private CocktailService cocktailService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCocktailList() {
        Collection<Cocktail> cocktails = cocktailService.getAllCocktails();
        return Response.ok(cocktails).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCocktailDetails(@PathParam("id") Long id) {
        Cocktail cocktail = cocktailService.getCocktailWithID(id);
        if (cocktail != null) {
            return Response.ok(cocktail).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/ingredients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientList() {
        Collection<Ingredient> ingredients = cocktailService.getAllIngredients();
        return Response.ok(ingredients).build();
    }

    @GET
    @Path("/ingredients/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientDetails(@PathParam("id") Long id) {
        Ingredient ingredient = cocktailService.getIngredientWithID(id);
        if (ingredient != null) {
            Collection<Cocktail> cocktails = cocktailService.getAllCocktailsWithIngredient(id);
            return Response.ok(Map.of("name", ingredient.getName(), "cocktails", cocktails)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCocktails(@QueryParam("query") String query) {
        Collection<Cocktail> cocktails = cocktailService.search(query);
        return Response.ok(cocktails).build();
    }

}
