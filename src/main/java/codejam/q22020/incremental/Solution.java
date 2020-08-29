package codejam.q22020.incremental;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    long upperBound(long v) {
        long l = 0; long r = 2_000_000_000;
        while (l < r) {
            long mid = (l + r + 1) / 2;
            if (mid * (mid + 1) > 2 * v) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return r;
    }
    void solve(Scanner sc, PrintWriter pw) {
        long round = 0;
        long L = sc.nextLong();
        long R = sc.nextLong();

        long delta = Math.abs(L - R);
        long x = upperBound(delta);
        boolean lLarger = true;
        if (R > L) {
            lLarger = false;
            long tmp = L;
            L = R;
            R= tmp;
        }

        round += x;
        L -= (x * (x + 1)) / 2;

        if (L == R) {
            lLarger = true;
        }

        // L >= R
        long W = search(L, R, x);
        L -= W * W + W * x;
        R -= W * W + W + W * x;

        round += 2 * W;

        if (L >= x + 2 * W + 1) {
            L -= x + 2 * W + 1;
            round++;
        }

        if (!lLarger) {
            pw.println(String.format("%d %d %d", round, R, L));
        } else {
            pw.println(String.format("%d %d %d", round, L, R));
        }
    }

    //s0 >= s1
    private long search(long s0, long s1, long x) {
        long l = 0;
        long r = 2_000_000_000;
        while (l < r) {
            long mid = (l + r + 1) / 2;
            if (s0 < mid * mid + mid * x || s1 < mid * mid + mid + mid * x) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return r;
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
