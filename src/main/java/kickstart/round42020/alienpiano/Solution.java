package kickstart.round42020.alienpiano;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    void solve(Scanner sc, PrintWriter pw) {
        int K;
        K = sc.nextInt();
        int[] data = new int[K];
        for (int i = 0; i < K; i++) {
            data[i] = sc.nextInt();
        }
        int[][] dp = new int[K + 1][4];
        int INF = Integer.MAX_VALUE / 4;
        for (int[] v : dp) {
            Arrays.fill(v, INF);
        }
        Arrays.fill(dp[0], 0);
        for (int i = 1; i < K; i++) {
            for (int curPitch = 0; curPitch < 4; curPitch++) {
                for (int prePitch = 0; prePitch < 4; prePitch++) {
                    int originCompare = Integer.compare(data[i], data[i - 1]);
                    int translatedCompare = Integer.compare(curPitch, prePitch);

                    int delta = 0;
                    if (originCompare != translatedCompare) {
                        delta = 1;
                    }

                    dp[i][curPitch] = Math.min(dp[i][curPitch], dp[i - 1][prePitch] + delta);
//                    System.out.println(String.format("%d %d %d", i, curPitch, dp[i][curPitch]));
                }
            }
        }

        int result = Arrays.stream(dp[K - 1]).min().getAsInt();
        pw.println(String.format("%d", result));
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
