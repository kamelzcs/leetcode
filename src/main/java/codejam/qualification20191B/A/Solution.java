package codejam.qualification20191B.A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {
    final static String PROBLEM_NAME = "alien";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2019/q1a/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input.txt";
    final static String OUTPUT_FILE_NAME = "output.txt";

    int P, Q;
    // 0 -> x, 1 -> y
    List<List<Predicate<Integer>>> predicts;
    List<TreeSet<Integer>> candidiates;
    void solve(Scanner sc, PrintWriter pw) {
        P = sc.nextInt();
        Q = sc.nextInt();
        predicts = new ArrayList<>();
        predicts.add(new ArrayList<>());
        predicts.add(new ArrayList<>());
        candidiates = new ArrayList<>();
        candidiates.add(new TreeSet<>());
        candidiates.add(new TreeSet<>());

        for (int i = 0; i < P; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            String direction = sc.next();
            if ("N".equals(direction) || "S".equals(direction)) {
                if ("N".equals(direction)) {
                    predicts.get(1).add(v -> v > y);
                    candidiates.get(1).add(Math.min(y + 1, Q));
                } else {
                    predicts.get(1).add(v -> v < y);
                    candidiates.get(1).add(Math.max(0, y - 1));
                }
            } else {
                if ("E".equals(direction)) {
                    predicts.get(0).add(v -> v > x);
                    candidiates.get(0).add(Math.min(x + 1, Q));
                } else {
                    predicts.get(0).add(v -> v < x);
                    candidiates.get(0).add(Math.max(0, x - 1));
                }
            }
        }
        candidiates.get(0).add(0);
        candidiates.get(1).add(0);

        pw.println(String.format("%d %d", func(0), func(1)));
    }

    private int func(int index) {
        long max = -1;
        int result = -1;
        for (int candidate : candidiates.get(index)) {
            long part = predicts.get(index).stream().filter(p -> p.test(candidate)).count();
            if (part > max) {
                max = part;
                result = candidate;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
//        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
//                + OUTPUT_FILE_NAME));
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
