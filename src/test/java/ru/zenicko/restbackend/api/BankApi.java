package ru.zenicko.restbackend.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BankApi {

    public static Response createNewUser(String userName, String password) {

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
                            .statusCode(201)
                            .extract().response();
    }
}
