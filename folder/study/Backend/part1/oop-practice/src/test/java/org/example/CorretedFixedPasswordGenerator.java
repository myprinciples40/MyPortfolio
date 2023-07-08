package org.example;

public class CorretedFixedPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "abcdefgh"; // 8 characters
    }
}
