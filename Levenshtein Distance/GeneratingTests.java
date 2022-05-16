import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GeneratingTests {
    public static void main(String[] args) {
        int s1Length = 10;
        int s2Length = s1Length / 2;

        for (int i = 1; i < 101; i++) {
            write(i, s1Length, s2Length);
            s1Length += 10;
            s2Length = s1Length / 2;
        }
    }

    public static String[] generateStrings(int s1Length, int s2Length) {
        int leftLimit = 97;
        int rightLimit = 122;

        Random random = new Random();

        String[] strings = new String[2];

        for (int i = 0; i < strings.length; i++) {
            strings[0] = random.ints(leftLimit, rightLimit + 1)
                    .limit(s1Length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            strings[1] = random.ints(leftLimit, rightLimit + 1)
                    .limit(s2Length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        return strings;
    }

    public static void write(int fileName, int s1Length, int s2Length) {
        File file = new File("C:/Users/Ilya/Desktop/LevDist/Tests1/File" + fileName + ".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                String[] strings = generateStrings(s1Length, s2Length);

                for (int i = 0; i < strings.length; i++) {
                    out.println(strings[i]);
                }

            } finally {
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}