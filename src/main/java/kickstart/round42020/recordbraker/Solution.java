package kickstart.round42020.recordbraker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {
    void solve(Scanner sc, PrintWriter pw) {
        int N;
        N = sc.nextInt();
        int[] data = new int[N + 1];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }
        data[N] = -1;
        int result = 0;
        int maxValue = -1;
        for (int i = 0; i < N; i++) {
            if (data[i] > maxValue && data[i] > data[i + 1]) {
                result++;
            }
            maxValue = Math.max(maxValue, data[i]);
        }
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
