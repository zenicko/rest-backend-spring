package ru.zenicko.restbackend.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zenicko.restbackend.api.BankApi;
import ru.zenicko.restbackend.domain.UserInfo;

@DisplayName("Test the handler: Get all users")
public class BankControllerGetAllUsersTests {

    @Test
    @DisplayName("Check a status code. It will be 200")
    void statusCodeShouldBe200() {
        BankApi
                .getAllUsers()
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Check a list of users: size is not 0")
    void sizeListOfUsersGreaterThanZero() {
        //@formatter:off
        UserInfo[] userInfos =
                BankApi
                            .getAllUsers()
                        .then()
                            .extract()
                            .response()
                            .as(UserInfo[].class);
        //@formatter:on

        Assertions.assertThat(userInfos.length).isGreaterThan(0);

    }

    @Test
    @DisplayName("Check schemas of all elements which to equal the base schema")
    void checkSchemaResponse() throws JsonProcessingException {
        UserInfo[] userInfos =
                BankApi
                        .getAllUsers()
                        .body()
                        .as(UserInfo[].class);

        ObjectMapper mapper = new ObjectMapper();
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo.toString());
            System.out.println(mapper.writeValueAsString(userInfo));
            MatcherAssert.assertThat(
                    mapper.writeValueAsString(userInfo),
                    JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/loginfo-schema.json")
            );
        }
    }
}
