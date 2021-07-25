package codeforces.round633div1;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskC {
    long[] data = {1, 2, 3};

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            long N = in.nextLong();
            out.println(Long.valueOf(f(N), 4));
        }
    }

    private String f(long n) {
        if (n <= 3) {
            return String.valueOf(n);
        }
        int currentBit = 2;
        int nextBit = currentBit + 2;
        while ((1L << nextBit) <= n) {
            nextBit += 2;
        }

        long left = n - (1L << (nextBit - 2)) + 1;
        long firstPart = data[(int) (left + 2) % 3];
        if (left <= 3) {
            return firstPart + "0";
        }
        long latter = left - 4;
        long singlePasses = (1L << (nextBit - 2)) - 1;
        long passes = latter / singlePasses;
        long rotate = latter % singlePasses;
        return firstPart + f(((rotate + passes) % singlePasses) + 1);
    }
}
