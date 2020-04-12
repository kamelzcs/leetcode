package codejam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {
    final static String PROBLEM_NAME = "test";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2020/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input.txt";
    final static String OUTPUT_FILE_NAME = "output.txt";

    void solve(Scanner sc, PrintWriter pw) {
        String result = sc.next();
        pw.println(String.format("%s %s", adjust1(result), adjust2(result)));
    }

    private String adjust1(String result) {
        char[] parts = result.toCharArray();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] == '4') {
                parts[i] -= 1;
            }
        }
        return new String(parts);
    }

    private String adjust2(String result) {
        char[] parts = result.toCharArray();
        int firstNonZero = -1;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] == '4') {
                parts[i] = '1';
                if (firstNonZero < 0) {
                    firstNonZero = i;
                }
            } else {
                parts[i] = '0';
            }
        }
        return new String(parts).substring(firstNonZero);
    }

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
//        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
//                + OUTPUT_FILE_NAME));
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
