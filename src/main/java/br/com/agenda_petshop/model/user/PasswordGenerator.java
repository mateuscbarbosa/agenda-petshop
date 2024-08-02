package br.com.agenda_petshop.model.user;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*_+-";
    private static final String ALL_CHARS = UPPER + LOWER + NUMBERS + SYMBOLS;
    private static SecureRandom random = new SecureRandom();

    public String generatePassword(int length){
        StringBuilder password = new StringBuilder(length);

        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));

        for (int i=4; i<length; i++){
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return shufflePassword(password.toString());
    }

    private String shufflePassword(String password) {
        char[] charArray = password.toCharArray();
        for (int i = 0; i < charArray.length; i++){
            int randomIndex = random.nextInt(charArray.length);
            char temp = charArray[i];
            charArray[randomIndex] = temp;
        }
        return new String(charArray);
    }
}
