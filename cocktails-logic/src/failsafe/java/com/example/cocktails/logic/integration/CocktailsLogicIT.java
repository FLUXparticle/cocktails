package com.example.cocktails.logic.integration;

import org.junit.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CocktailsLogicIT {

    @Test
    public void cocktails() {
        when().get("/api/cocktails").then().
                statusCode(200).
                body("", iterableWithSize(69));
    }

}
