package QTree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenerateTestFiles {
    public static void main(String[] args) {
        int count = 200;

        for (int i = 1; i < 101; i++) {
            write(i, count);
            count += 200;
        }
    }

    public static double getRandomDoubleBetweenRange(double min, double max){
        return (Math.random() * ((max - min) + 1) ) + min;
    }

    public static void write(int fileName, int count) {
        File file = new File("C:/Users/Ilya/Desktop/TermProject/Tests/File" + fileName + ".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                // boundary [-499.0, 499]
                List<Double> a = new ArrayList<>();

                for (int i = 0; i < 4; i++) {
                    double n = Math.round(getRandomDoubleBetweenRange(-499, 499) * 10.0) / 10.0;
                    a.add(n);
                }

                a.sort(Comparator.naturalOrder());

                for (int i = 0; i < a.size(); i++) {
                    out.print(a.get(i) + " ");
                }

                // points [-499.0, 499.0]
                for (int i = 0; i < count; i++) {
                    out.print(Math.round(getRandomDoubleBetweenRange(-499, 499) * 10.0) / 10.0 + " ");
                }

            } finally {
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}