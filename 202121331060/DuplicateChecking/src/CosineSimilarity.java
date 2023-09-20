import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static fileUtils.fileutile.readTextFile;
import static fileUtils.fileutile.writeOutputFile;

public class CosineSimilarity {
    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
    int[] tempArray = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入第一个文件的路径：");
        String filePath1 = scanner.nextLine();

        System.out.println("请输入第二个文件的路径：");
        String filePath2 = scanner.nextLine();

        System.out.println("请输入输出文件的路径：");
        String outputPath = scanner.nextLine();

        String str1 = readTextFile(filePath1);
        String str2 = readTextFile(filePath2);

        CosineSimilarity similarity = new CosineSimilarity(str1, str2);
        System.out.printf("%.2f", similarity.sim());

        writeOutputFile(outputPath, similarity.sim());

        scanner.close();
    }

    public CosineSimilarity(String string1, String string2) {
        for (Character character1 : string1.toCharArray()) {
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }

    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }


}