package ru.zenicko.restbackend.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zenicko.restbackend.api.BankApi;

public class BankControllerTests {

    @Test
    @DisplayName("Check status code is 201 in handler \"/user\"")
    void shouldBeStatusCode200() throws InterruptedException {
        BankApi.createNewUser("nick7", "111");
    }

}
