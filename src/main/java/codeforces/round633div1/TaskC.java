package codeforces.round633div1;



import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;

public class TaskC {
    long[] data = {1, 2, 3};
    String[] twelveBit = {"0", "0", "0", "1", "2", "3", "2", "3", "1", "3", "1", "2"};
    long[] power = new long[90];
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        power[0] = 1L;
        for(int i = 1; i < power.length; i++) {
            power[i] = power[i - 1] * 4L;
        }
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            long N = in.nextLong();
            out.println(Long.valueOf(f(N), 4));
        }
    }

    private String f(long n) {
        //System.out.println(n);
        if (n <= 3) {
            return String.valueOf(n);
        }
        int currentBit = 2;
        long nextBit = currentBit + 2;
        while ((1L << nextBit) <= n) {
            nextBit += 2;
        }

        long left = n - (1L << (nextBit - 2)) + 1;
        long firstPart = data[(int)((left - 1L) % 3)];
        return firstPart + f3((left - 1), nextBit / 2 - 1);
    }

    private String f3(long left, long bit) {
        String lastPart = twelveBit[(int)(left % 12)];
        return bit > 1 ? f4(left / 12, bit - 1) + lastPart : lastPart;
    }

    private String f4(long left, long bit) {
        if (bit == 1) {
            return twelveBit[(int)((3L * (left / 12) + (left % 3)))];
        }
        return f4(left / (12 * 4), bit - 1) + twelveBit[(int)((3L * ((left % 48) / 12) + (left % 3)))];
    }

    private String f2(long left, int bit) {
        //System.out.println(String.format("%d %d", left, bit));
        if (bit == 1) {
            assert left < twelveBit.length;
            return twelveBit[(int)left];
        }

        long singlePass = 3L * power[(bit - 1)];
        long passes = left / singlePass;
        long singleLeft = left % singlePass;
        return twelveBit[(int)(3 * passes + (singleLeft % 3))] + f2(singleLeft, bit - 1);
    }

    private String padding(int targetLen, String zero) {
        StringBuilder builder = new StringBuilder(zero);
        while (builder.length() < targetLen) {
            builder.insert(0, '0');
        }
        return builder.toString();
    }
}
