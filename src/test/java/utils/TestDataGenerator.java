package utils;

import java.util.UUID;

public class TestDataGenerator {
    public static String generateEmail() {
        return "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public static String generatePassword() {
        return "password_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateName() {
        return "TestUser_" + UUID.randomUUID().toString().substring(0, 8);
    }
}