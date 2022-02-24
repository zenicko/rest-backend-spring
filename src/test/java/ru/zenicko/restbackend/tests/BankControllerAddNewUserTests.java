package ru.zenicko.restbackend.tests;

import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zenicko.restbackend.api.BankApi;
import ru.zenicko.restbackend.data.DataProvider;
import ru.zenicko.restbackend.domain.UserInfo;

import java.util.Date;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@DisplayName("Testing API handler \"/user\" \"Add a new user\" ")
public class BankControllerAddNewUserTests {

    @Test
    @DisplayName("Check status code is 201")
    void shouldBeStatusCode201() {
        BankApi.createNewUser(
                DataProvider.generateUserName(),
                DataProvider.generatePassword()
        );
    }

    @Test
    @DisplayName("Check schema the response")
    void shouldBeCorrectSchema() {
        ValidatableResponse newUser = BankApi.createNewUser(
                DataProvider.generateUserName(),
                DataProvider.generatePassword()
        );
        newUser
                .body(matchesJsonSchemaInClasspath("schema/loginfo-schema.json"));

    }

    @Test
    @DisplayName("Check values of the field of the response")
    void shouldBeEqualValues() {
        String
                userName = DataProvider.generateUserName(),
                password = DataProvider.generatePassword();

        ValidatableResponse newUser = BankApi.createNewUser(userName, password);
        newUser
                .body("user_name", Matchers.is(userName))
                .body("login_date", Matchers.is(Matchers.greaterThan(164509812300L)));

        UserInfo UserInfo =
                newUser
                        .extract()
                        .response()
                        .as(UserInfo.class);

        Assertions
                .assertThat(UserInfo.getUserName())
                .isEqualTo(userName);
        Assertions
                .assertThat(UserInfo.getLoginDate().getTime())
                .isLessThan(UserInfo.START_DATE);
    }

}
