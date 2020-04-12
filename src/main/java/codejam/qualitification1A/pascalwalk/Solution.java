package codejam.qualitification1A.pascalwalk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
    final static String PROBLEM_NAME = "test";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2020/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input.txt";
    final static String OUTPUT_FILE_NAME = "output.txt";
    String[] data;
    String[][] parts;
    long N;

    long cnr(long n, long k) {
        long result = 1;
        if (k == 0) {
            return 1;
        }
        for (long d = 1; d <= k; d++) {
            result *= n--;
            result /= d;
        }
        return result;
    }

    long sumCnr(long n, long k) {
        long sum = 0;
        for (long i = 0; i <= k; i++) {
            sum += cnr(n, i);
        }
        return sum;
    }

    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextLong();
        StringBuilder result = new StringBuilder();
        long total = 1;
        long row = 1;
        long column = 1;
        result.append("1 1\n");
        while (true) {
            long nextRow = row + 1;
            long nextCol = Math.min((nextRow + 1) / 2, column + 1);
            long partial = sumCnr(nextRow - 1, nextCol - 1);
            if (partial + total <= N) {
                result.append(String.format("%d %d\n", nextRow, nextCol));
                total += cnr(nextRow - 1, nextCol - 1);
                row = nextRow;
                column = nextCol;
            } else {
                if (column == 1) {
                    for (long i = 1; i + total <= N; i++) {
                        result.append(String.format("%d 1\n", row + i));
                    }
                    break;
                }
                column -= 1;
                result.append(String.format("%d %d\n", row, column));
                total += cnr(row - 1, column - 1);
            }
//            System.out.println(String.format("%d %d %d", total, row, column));
        }
        pw.println(String.format("%s", result.toString()));
    }

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
//        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
//                + OUTPUT_FILE_NAME));
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.println("Case #" + (caseNum + 1) + ":");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
