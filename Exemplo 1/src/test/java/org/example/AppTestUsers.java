package org.example;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTestUsers
{
    @BeforeClass
    public static  void setup(){
        RestAssured.baseURI ="https://randomuser.me/";
        RestAssured.basePath = "/api";
    }

    @Test
    public void retornarVinteUsuario(){

             given()
                .param("results",20)
                .when()
                .get("")
                .then()
                .body("info.results", Matchers.is(20))
             ;

    }

    @Test
    public void retornarUsuarioBrasileiro(){

        given()
                .param("nat","BR")
                .when()
                .get("")
                .then()
                .statusCode(200)
                .body("results[0].nat", Matchers.is("BR"))
        ;

    }

    @Test
    public void retornarUsuarioPaginado(){

        given()
                .param("page",3)
                .param("results",1)
                .when()
                .get("")
                .then()
                .body("info.results", Matchers.is(1))
                .body("info.page",Matchers.is(3))
        ;

    }

    @Test
    public void retornarNomeEmail(){

        given()
                .param("inc","name,email")
                .when()
                .get("")
                .then()
                .statusCode(200)
                .body("results[0]", Matchers.hasKey("name"))
                .body("results[0]", Matchers.hasKey("email"))
        ;
    }

    @Test
    public void retornarUsuarioBrUsEsCa(){

        List<String> nascionalidades = Arrays.asList("BR","US","ES","CA");

        String nat =
                given()
                .param("nat","BR,US,ES,CA")
                .when()
                .get("")
                .then()
                .statusCode(200)
                .extract().path("results[0].nat")
        ;
        Assert.assertThat(nascionalidades, Matchers.hasItem(nat));

    }


}
