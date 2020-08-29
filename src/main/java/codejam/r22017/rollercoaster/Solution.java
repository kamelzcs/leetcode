package codejam.r22017.rollercoaster;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Solution {
    void solve(Scanner sc, PrintWriter pw) {
        int N, M, C;
        Map<Integer, List<Integer>> personTickers = new HashMap<>();
        List<Integer> positions = new ArrayList<>();
        Map<Integer, Integer> posCount = new HashMap<>();
        N = sc.nextInt();
        M = sc.nextInt();
        C = sc.nextInt();
        for (int i = 0; i < C; i++) {
            int pos = sc.nextInt();
            int person = sc.nextInt();
            personTickers.computeIfAbsent(person, x -> new ArrayList<>()).add(pos);
            positions.add(pos);
            posCount.put(pos, posCount.getOrDefault(pos, 0) + 1);
        }
        Collections.sort(positions);
        int rides = personTickers.values().stream()
                .map(List::size)
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        int sum = 0;
        for (int pos : positions) {
            sum += 1;
            rides = Math.max(rides, (sum + pos - 1) / pos);
        }
        final int ridesFinal = rides;
        int moving = posCount.values().stream()
                .mapToInt(x -> Math.max(0, x - ridesFinal))
                .sum();
        pw.println(String.format("%d %d", rides, moving));
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
