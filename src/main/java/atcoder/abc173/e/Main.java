package atcoder.abc173.e;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    long MOD = 1_000_000_007;

    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int K = sc.nextInt();
        List<Long> positive = new ArrayList<>();
        List<Long> negative = new ArrayList<>();
        int zeroCount = 0;
        for (int i = 0; i < N; i++) {
            long data = sc.nextLong();
            if (data == 0) {
                zeroCount++;
            } else if (data > 0) {
                positive.add(data);
            } else {
                negative.add(-1L * data);
            }
        }
        if (K % 2 == 0) {
            int positiveUsable = getEven(positive.size()) + getEven(negative.size());
            if (positiveUsable >= K) {
                positive.sort(Comparator.reverseOrder());
                negative.sort(Comparator.reverseOrder());
                List<Long> total = new ArrayList<>();
                for (int i = 0; i + 1 < positive.size(); i += 2) {
                    total.add(((long) positive.get(i) * positive.get(i + 1)));
                }
                for (int i = 0; i + 1 < negative.size(); i += 2) {
                    total.add(((long) negative.get(i) * negative.get(i + 1)));
                }
                total.sort(Collections.reverseOrder());
                long result = 1L;
                for (int i = 0; i < K / 2; i++) {
                    result = (result * (total.get(i) % MOD)) % MOD;
                }
                pw.println(result);
            } else {
                if (zeroCount > 0) {
                    pw.println("0");
                } else {
                    List<Long> total = new ArrayList<>();
                    total.addAll(positive);
                    total.addAll(negative);
                    Collections.sort(total);
                    long result = -1L;
                    for (int i = 0; i < K; i++) {
                        result = (result * total.get(i)) % MOD;
                    }
                    pw.println((result + MOD) % MOD);
                }
            }
        } else {
            int positiveUsable = getOdd(positive.size()) + getEven(negative.size());
            if (getOdd(positive.size()) >= 0 && positiveUsable >= K) {
                List<Long> total = new ArrayList<>();
                positive.sort(Comparator.reverseOrder());
                negative.sort(Comparator.reverseOrder());
                long result = positive.get(0);
                for (int i = 1; i + 1 < positive.size(); i += 2) {
                    total.add((positive.get(i) * positive.get(i + 1)));
                }
                for (int i = 0; i + 1 < negative.size(); i += 2) {
                    total.add((negative.get(i) * negative.get(i + 1)));
                }
                total.sort(Collections.reverseOrder());
                for (int i = 0; i < K / 2; i++) {
                    result = (result * (total.get(i) % MOD)) % MOD;
                }
                pw.println((result + MOD) % MOD);
            } else {
                if (zeroCount > 0) {
                    pw.println("0");
                } else {
                    List<Long> total = new ArrayList<>();
                    total.addAll(positive);
                    total.addAll(negative);
                    Collections.sort(total);
                    long result = -1L;
                    for (int i = 0; i < K; i++) {
                        result = (result * total.get(i)) % MOD;
                    }
                    pw.println((result + MOD) % MOD);
                }
            }
        }
    }

    private int getEven(int size) {
        return size - (size % 2);
    }

    private int getOdd(int size) {
        if (size == 0) {
            return -1;
        }
        return size - ((size % 2) == 0 ? 1 : 0);
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
