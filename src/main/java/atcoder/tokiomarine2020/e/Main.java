package atcoder.tokiomarine2020.e;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    long[][] c = new long[51][51];

    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int K = sc.nextInt();
        long S = sc.nextLong();
        long T = sc.nextLong();
        long[] data = new long[N];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextLong();
        }
        for (int i = 0; i <= N; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        long[] sum = new long[(N + 1)];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= Math.min(i, K); j++) {
                sum[i] += c[i][j];
            }
        }
        long result = 0;
        List<Long> nums = new ArrayList<>();
        for (long mask = 0; mask < (1L << 18); mask++) {
            if (((T ^ S) & mask) != mask) {
                continue;
            }
            boolean isAdd = (Long.bitCount(mask) % 2) == 0;
            nums.clear();
            for (int i = 0; i < N; i++) {
                if ((data[i] & S) != S) {
                    continue;
                }
                if ((data[i] | T) != T) {
                    continue;
                }
                nums.add(data[i] & mask);
            }
            Collections.sort(nums);
            int begin = 0;
            int nextBegin;
            while (begin < nums.size()) {
                nextBegin = begin + 1;
                while (nextBegin< nums.size() && nums.get(nextBegin).equals(nums.get(nextBegin - 1))) {
                    nextBegin++;
                }
                result += (isAdd ? 1L : -1L) * (sum[nextBegin - begin]);
                begin = nextBegin;
            }
        }
        pw.println(String.format("%d", result));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}
