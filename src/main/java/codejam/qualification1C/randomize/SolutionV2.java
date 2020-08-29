package codejam.qualification1C.randomize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class SolutionV2 {
    final static String PROBLEM_NAME = "overrandomize";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2020/q1c/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input";
    final static String OUTPUT_FILE_NAME = "output";

    int U;
    Map<Integer, Map<Character, Integer>> count;
    Set<Character> totalChars;
    void solve(Scanner sc, PrintWriter pw) {
        U = sc.nextInt();
        count = new HashMap<>();
        totalChars = new HashSet<>();
        for (int i = 0; i < 10_000; i++) {
            String number = sc.next();
            String mapped = sc.next();
            char topMapped = mapped.charAt(0);
            int lenNumber = number.length();
            totalChars.addAll(mapped.chars().mapToObj(x -> (char)x).collect(Collectors.toSet()));
            Map<Character, Integer> lenCount = count.computeIfAbsent(lenNumber, x -> new HashMap<>());
            lenCount.put(topMapped, lenCount.getOrDefault(topMapped, 0) + 1);
        }

//        for (Map<Character, Integer> value : count.values()) {
//            value.remove(zero);
//        }

        List<Character> sortedList = count.get(U).entrySet().stream().sorted((x, y) -> Integer.compare(y.getValue(), x.getValue())).map(Map.Entry::getKey).collect(Collectors.toList());
        char zero = 'x';
        for (char c : totalChars) {
            if (!sortedList.contains(c)) {
                zero = c;
                break;
            }
        }



//        for (Map.Entry<List<Character>, Integer> entry : mappedCount.entrySet()) {
//            System.out.println(String.format("%s %d", entry.getKey(), entry.getValue()));
//        }
        String result = "";
        result += zero;
        for (char c : sortedList) {
            result += c;
        }
        pw.println(result);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
                + OUTPUT_FILE_NAME));
//        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            new SolutionV2().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
