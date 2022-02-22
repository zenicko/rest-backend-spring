package ru.zenicko.restbackend.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zenicko.restbackend.api.BankApi;
import ru.zenicko.restbackend.data.DataProvider;

@Disabled("BankControllerTests is replaced by class BankControllerAddNewUserTests")
public class BankControllerTests {

    @Test
    @DisplayName("Check status code is 201 in handler \"/user\"")
    void shouldBeStatusCode200() throws InterruptedException {
        BankApi.createNewUser(
                DataProvider.generateUserName(),
                DataProvider.generatePassword()
        );
    }

}
