package QTree;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class RemoveTest {
    public static void main(String[] args) throws IOException {
        Test(60);
    }
    public static void Test(int testsNum) throws IOException {
        Scanner sc;
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");

        for (int i = 1; i < testsNum + 1; i++) {
            sheet = workbook.getSheet("Results");
            int n = 0;
            double sum = 0;

            for (int j = 1; j < 1000001; j++) {
                n = 0;

                File file = new File("C:/Users/Ilya/Desktop/TermProject/Tests/File" + i + ".txt");
                sc = new Scanner(file);

                AABB<String> boundary = new AABB<>(-500, -500, 500, 500);
                QuadTree<String> quadTree = new QuadTree<>(boundary, 8, null);

                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();

                AABB<String> searchArea = new AABB<>(x1, y1, x2, y2);

                double x = sc.nextDouble();
                double y = sc.nextDouble();

                XY<String> point = new XY<>(x, y);

                while (sc.hasNext()) {
                    XY<String> p = new XY<>(sc.nextDouble(), sc.nextDouble());
                    quadTree.insert(p);
                    n++;
                }

                double start = System.nanoTime();
                quadTree.delete(point);
                double finish = System.nanoTime();

                double e = finish - start;
                sum += (e / 1000000.0);
            }

            Row row1 = sheet.createRow(i);
            Row row2 = sheet.createRow(i);
            Cell c = row1.createCell(0);
            c.setCellValue((float) n);

            Cell name = row2.createCell(3);
            name.setCellValue((float) sum / 1000000.0);
        }

        FileOutputStream fileExcel = new FileOutputStream("C:/Users/Ilya/Desktop/TermProject/Graphs/Remove8.xls");
        workbook.write(fileExcel);
        fileExcel.close();
    }
}