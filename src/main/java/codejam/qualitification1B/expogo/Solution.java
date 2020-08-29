package codejam.qualitification1B.expogo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Pair<K, V> {
    K first;
    V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}

public class Solution {
    final static String PROBLEM_NAME = "test";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2020/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input.txt";
    final static String OUTPUT_FILE_NAME = "output.txt";

    void solve(Scanner sc, PrintWriter pw) {
        StringBuilder result = new StringBuilder();
        long x = sc.nextLong();
        long y = sc.nextLong();
        if (((x + y) & 1) == 0) {
            pw.println("IMPOSSIBLE");
        } else {
            long absX = Math.abs(x);
            long absY = Math.abs(y);
            List<Pair<Long, Integer>> represents = represent(absX + absY);
            Pair<List<Pair<Long, Integer>>, List<Pair<Long, Integer>>> positions = represent(absX, absY, represents);
            Set<Integer> firstSet = positions.first.stream().map(v -> v.second).collect(Collectors.toSet());
            for (int i = 0; i < represents.size(); i++) {
                if (firstSet.contains(i)) {
                    result.append(getX(x * (represents.get(i).first) > 0));
                } else {
                    result.append(getY(y * (represents.get(i).first) > 0));
                }
            }
            pw.println(result.toString());
        }
    }

    private String getX(boolean isEast) {
        return isEast ? "E" : "W";
    }

    private String getY(boolean isNorth) {
        return isNorth ? "N" : "S";
    }

    private Pair<List<Pair<Long, Integer>>, List<Pair<Long, Integer>>> represent(long l, long r, List<Pair<Long, Integer>> represents) {
        if (l == 0 || r == 0) {
            if (l == 0) {
                List<Pair<Long, Integer>> first = new ArrayList<>();
                List<Pair<Long, Integer>> second = new ArrayList<>(represents);
                return new Pair<>(first, second);
            } else {
                List<Pair<Long, Integer>> first = new ArrayList<>(represents);
                List<Pair<Long, Integer>> second = new ArrayList<>();
                return new Pair<>(first, second);
            }
        }

        Pair<Long, Integer> tail = represents.get(represents.size() - 1);
        List<Pair<Long, Integer>> parts = represents.subList(0, represents.size() - 1);
        if (tail.first < 0) {
            if (l < r) {
                Pair<List<Pair<Long, Integer>>, List<Pair<Long, Integer>>> partial = represent(l - tail.first, r, parts);
                partial.first.add(tail);
                return partial;
            } else {
                Pair<List<Pair<Long, Integer>>, List<Pair<Long, Integer>>> partial = represent(l, r - tail.first, parts);
                partial.second.add(tail);
                return partial;
            }
        } else {
            if (l > r) {
                Pair<List<Pair<Long, Integer>>, List<Pair<Long, Integer>>> partial = represent(l - tail.first, r, parts);
                partial.first.add(tail);
                return partial;
            } else {
                Pair<List<Pair<Long, Integer>>, List<Pair<Long, Integer>>> partial = represent(l, r - tail.first, parts);
                partial.second.add(tail);
                return partial;
            }
        }
    }

    private List<Pair<Long, Integer>> represent(long value) {
        List<Pair<Long, Integer>> result = new ArrayList<>();
        int bit = 0;
        while ((1L << bit) <= value) {
            if ((1L << (bit + 1) < value) && ((value & (1L << (bit + 1))) == 0)) {
                result.add(new Pair<>(-(1L << bit), bit));
            } else {
                result.add(new Pair<>((1L << bit), bit));
            }
            bit++;
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
//            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
