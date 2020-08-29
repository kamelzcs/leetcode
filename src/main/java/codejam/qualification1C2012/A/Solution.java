package codejam.qualification1C2012.A;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    final static String PROBLEM_NAME = "diamond";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2012/q1c/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "A-large-practice.in";
    final static String OUTPUT_FILE_NAME = "A-large-practice.out";

    int N;
    List<List<Integer>> adj;
    Set<Integer> visited;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        adj =  new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int neighbors = sc.nextInt();
            adj.add(new ArrayList<>());
            for (int j = 0; j < neighbors; j++) {
                int neighbor = sc.nextInt();
                if (neighbor > 0) {
                    adj.get(i).add(neighbor - 1);
                }
            }
        }
        pw.println(dfs());
    }

    String dfs() {
        for (int i = 0; i < N; i++) {
            visited = new HashSet<>();
            if (dfs(i)) {
                return "YES";
            }
        }
        return "NO";
    }

    private boolean dfs(int index) {
        visited.add(index);
        for (int neighbor : adj.get(index)) {
            if (visited.contains(neighbor)) {
                return true;
            }
            if (dfs(neighbor)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
                + OUTPUT_FILE_NAME));
//        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        PrintWriter pw = new PrintWriter(System.out);
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
