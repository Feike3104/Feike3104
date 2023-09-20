package fileUtils;

import java.io.*;

public class fileutile {
    public static String readTextFile(String filePath) {

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeOutputFile(String filePath, double similarity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("相似度: " + String.format("%.2f",similarity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

