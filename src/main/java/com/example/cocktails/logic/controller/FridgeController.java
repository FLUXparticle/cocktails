package com.example.cocktails.logic.controller;

import com.example.cocktails.logic.data.*;
import com.example.cocktails.logic.service.*;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Stateless
@Path("/user/fridge")
public class FridgeController {

    @EJB
    private FridgeService fridgeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFridgeList() {
        Collection<FridgeIngredient> fridgeIngredients = fridgeService.getFridgeIngredients();
        return Response.ok(fridgeIngredients).build();
    }

    @PATCH
    @Path("/{ingredientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFridgeIngredient(@PathParam("ingredientId") Long ingredientId, FridgeIngredient fridgeIngredient) {
        fridgeService.updateFridgeIngredient(ingredientId, fridgeIngredient.isInFridge());
        return Response.ok().build();
    }

    @GET
    @Path("/possible")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPossibleRecipes() {
        Collection<Cocktail> possibleCocktails = fridgeService.getPossibleCocktails();
        return Response.ok(possibleCocktails).build();
    }

    @GET
    @Path("/shopping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingList() {
        Collection<Ingredient> shoppingList = fridgeService.getShoppingList();
        return Response.ok(shoppingList).build();
    }

}
