package org.example;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CheckStatus {

    @BeforeClass
    public static  void setup(){
        RestAssured.baseURI ="https://randomuser.me/";
        RestAssured.basePath = "/api";
    }


    @Test
    public void checkStatusGetUsers(){

        given()
                .param("results",20)
                .when()
                .get("")
                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void checkStatusGetUser(){

        given()
                .param("nat","BR")
                .when()
                .get("")
                .then()
                .statusCode(200)
        ;

    }

    @Test
    public void checkStatusGetUserPage(){

        given()
                .param("page",3)
                .param("results",1)
                .when()
                .get("")
                .then()
                .statusCode(200)
        ;

    }

    @Test
    public void checkStatusGetNameEmail(){
        given()
                .param("inc","name,email")
                .when()
                .get("")
                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void checkStatusGetUsersNationality(){

        List<String> nascionalidades = Arrays.asList("BR","US","ES","CA");

                given()
                        .param("nat","BR,US,ES,CA")
                        .when()
                        .get("")
                        .then()
                        .statusCode(200)
                ;
    }

}
