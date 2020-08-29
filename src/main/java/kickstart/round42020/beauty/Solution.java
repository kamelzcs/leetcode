package kickstart.round42020.beauty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    int[] modA;
    int[] modB;
    List<Integer>[] neighbors;
    int N, A, B;
    Map<Long, SimpleEntry<Double, Integer>> dp;
    double result;

    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();
        modA = new int[A];
        modB = new int[B];
        dp = new HashMap<>();

        neighbors = new List[N + 1];
        for (int i = 0; i < neighbors.length; i++) {
            neighbors[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            int parent = sc.nextInt();
            neighbors[parent].add(i);
        }

        result = 0;
        dfs(1, 0);
        pw.println(result);
    }

    private void dfs(int index, int shift) {
        int modeAIndex = shift % A;
        int modeBIndex = shift % B;
        int preModeA = modA[modeAIndex];
        int preModeB = modB[modeBIndex];

        for (int neighbor : neighbors[index]) {
            dfs(neighbor, shift + 1);
        }
        modA[modeAIndex]++;
        modB[modeBIndex]++;

        int totalModA = modA[modeAIndex] - preModeA;
        int totalModB = modB[modeBIndex] - preModeB;
        double probA = 1.0 / N * totalModA;
        double probB = 1.0 / N * totalModB;
        result += (1.0 - (1.0 - probA) * (1.0 - probB));
    }

    int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
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
