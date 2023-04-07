import java.security.SecureRandom;

public class PasswordGenerator {
    private static SecureRandom random = new SecureRandom();

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);

    public static void main(String[] args) {
        System.out.println("Password: " + generatePassword(12));
    }

    public static String generatePassword(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Invalid password length");
        }

        StringBuilder sb = new StringBuilder(length);

        sb.append(randomChar(CHAR_LOWER));
        sb.append(randomChar(CHAR_UPPER));
        sb.append(randomChar(NUMBER));
        sb.append(randomChar(OTHER_CHAR));

        for (int i = 4; i < length; ++i) {
            sb.append(randomChar(PASSWORD_ALLOW_BASE_SHUFFLE));
        }

        return sb.toString();
    }

    private static char randomChar(String charPool) {
        int randomIdx = random.nextInt(charPool.length());
        return charPool.charAt(randomIdx);
    }

    private static String shuffleString(String str) {
        char[] charArray = str.toCharArray();
        for (int i = charArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }
        return new String(charArray);
    }
}
