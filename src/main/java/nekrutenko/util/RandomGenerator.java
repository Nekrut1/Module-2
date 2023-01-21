package nekrutenko.util;

import java.util.Random;

public class RandomGenerator {
    private final Random random = new Random();

    public int randomNumber() {
        return random.nextInt(15, 90);
    }
    public String getRandomString() {
        int lenght = 10;
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < lenght; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            builder.append(randomChar);
        }
        return builder.toString();
    }
}
