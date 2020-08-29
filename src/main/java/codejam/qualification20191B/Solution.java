package codejam.qualification20191B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution {
    final static String PROBLEM_NAME = "alien";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2019/q1a/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input.txt";
    final static String OUTPUT_FILE_NAME = "output.txt";

    int N;
    String[] strings;
    Set<Integer> usedIndex;
    TreeMap<String, Set<Integer>> suffixMap;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        strings = new String[N];
        usedIndex = new HashSet<>();
        suffixMap = new TreeMap<>(Comparator.comparingInt(String::length).reversed().thenComparing(String::compareTo));
        for (int i = 0; i < N; i++) {
            strings[i] = new StringBuilder(sc.next()).reverse().toString();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= strings[i].length(); j++) {
                String part = strings[i].substring(0, j);
                suffixMap.computeIfAbsent(part, x -> new HashSet<>()).add(i);
            }
        }
        pw.println(largest());
    }

    private int largest() {
        int result = 0;
        for (String candidate : suffixMap.navigableKeySet()) {
            List<Integer> containsIndex = suffixMap.get(candidate)
                    .stream().filter(x -> !usedIndex.contains(x)).collect(Collectors.toList());
            if (containsIndex.size() < 2) {
                continue;
            }
            result += 2;
            usedIndex.addAll(containsIndex.subList(0, 2));
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
