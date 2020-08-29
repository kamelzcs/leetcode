package codejam.qualification20181B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    final static String PROBLEM_NAME = "overrandomize";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2020/q1c/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input";
    final static String OUTPUT_FILE_NAME = "output";

    int N, L;
    List<Set<Character>> digits;
    Set<String> strs;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        L = sc.nextInt();
        digits = new ArrayList<>();
        strs = new HashSet<>();
        for (int i = 0; i < L; i++) {
            digits.add(new HashSet<>());
        }
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            strs.add(str);
            for (int j = 0; j < L; j++) {
                char c = str.charAt(j);
                digits.get(j).add(c);
            }
        }
        int total = digits.stream().map(Set::size).reduce(1, (x, y) -> x * y);
        if (total == N) {
            pw.println("-");
        } else {
            pw.println(dfs(0, ""));
        }
    }

    String dfs(int index, String str) {
        if (index >= L) {
            if (strs.contains(str)) {
                return "";
            } else {
                return str;
            }
        }

        for (char c : digits.get(index)) {
            String candidate = dfs(index + 1, str + c);
            if (!candidate.isEmpty()) {
                return candidate;
            }
        }
        return "";
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
