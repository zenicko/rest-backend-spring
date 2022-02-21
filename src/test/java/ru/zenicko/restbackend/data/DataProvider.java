package ru.zenicko.restbackend.data;

import com.github.javafaker.Faker;

public class DataProvider {

    public static String generateUserName() {
        return new Faker().name().username();
    }

    public static String generatePassword() {
        return new Faker().internet().password(2, 5);
    }
}
