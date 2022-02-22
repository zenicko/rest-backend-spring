package ru.zenicko.restbackend.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class BankApi {

    public static ValidatableResponse createNewUser(String userName, String password) {

        String data1 = "{" +
                "\"user_name\":\"" + userName + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        return
                //@formatter:off
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                            .body(data1)
                        .when()
                            .post("http://localhost:8080/user")
                        .then()
                            .statusCode(201);
    }
}
