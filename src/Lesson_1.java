import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class Lesson_1 {
    private static final Random random = new Random();
    private static final int byteLength = 3;
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = generateRandomString();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedBytes = digest.digest(
                str.getBytes(StandardCharsets.UTF_8));

        byte[] sourceBytes = Arrays.copyOf(encodedBytes, byteLength);
        byte[] compBytes = {};
        String colStr = generateRandomString();
        while (!Arrays.equals(compBytes, sourceBytes)) {
            colStr = generateRandomString();
            while (colStr.equals(str)) {
                colStr = generateRandomString();
            }
            encodedBytes = digest.digest(
                    colStr.getBytes(StandardCharsets.UTF_8));
            compBytes = Arrays.copyOf(encodedBytes, byteLength);
        }

        System.out.println(str + " " + colStr);
    }

    public static String generateRandomString () {
        int length = random.nextInt(5) + 5;
        int aCode = 'a';
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int d = random.nextInt(26);
            builder.append((char) (aCode + d));
        }

        return builder.toString();
    }
}
