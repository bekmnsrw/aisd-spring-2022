import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class LevenshteinDistanceTest {
    public static void main(String[] args) throws IOException {
        test(100);
    }

    public static void test(int testsNum) throws IOException {
        Scanner sc;
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");

        for (int i = 1; i < testsNum + 1; i++) {
            sheet = workbook.getSheet("Results");
            double sum = 0;

            for (int j = 1; j < 1000001; j++) {

                File file = new File("C:/Users/Ilya/Desktop/LevDist/Tests1/File" + i + ".txt");
                sc = new Scanner(file);

                String s1 = sc.nextLine();
                String s2 = sc.nextLine();

                double start = System.nanoTime();
                LevenshteinDistance.levDist(s1, s2);
                double finish = System.nanoTime();

                double e = finish - start;
                sum += (e / 1000000.0);
            }

            Row row1 = sheet.createRow(i);
            Row row2 = sheet.createRow(i);
            Cell c = row1.createCell(0);
            c.setCellValue(i * 10);

            Cell name = row2.createCell(3);
            name.setCellValue(sum / 1000000.0);
        }

        FileOutputStream fileExcel = new FileOutputStream("C:/Users/Ilya/Desktop/LevDist/Test.xls");
        workbook.write(fileExcel);
        fileExcel.close();
    }
}