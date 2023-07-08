package org.example;

public class User {
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator) {
        // as-is : At high combinations
        // It's hard to write test code because you can't control the randomPasswordGenerator!
//        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        // To solve this, we use the parent PasswordGenerator interface

        // to-be : Write better code with lower combinations (since both Wrong and Correct can come in, not just Random)
        String password = passwordGenerator.generatePassword();

        /**
         * Your password must be at least 8 characters and no more than 12 characters long.
         * */
        if (password.length() >= 8 && password.length() <= 12) {
            this.password = password;
        }
    }

    public String getPassword() {
        return password;
    }
}
