package atcoder.abc172.f;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = 0;
        for (int i = 0; i + 2 < N; i++) {
            C ^= sc.nextLong();
        }
        pw.println(func(A, B, C));
    }

    private long func(long a, long b, long c) {
        long delta = a + b - c;
        if ((delta % 2) > 0) {
            return -1;
        }
        long andResult = delta / 2;
        if (andResult > a) {
            return -1;
        }
        long remain = a + b - 2L * andResult;
        if ((remain & andResult) > 0) {
            return -1;
        }
        long aResult = andResult;
        for (int i = 40; i >= 0; i--) {
            long bit = (1L << i);
            if ((remain & bit) > 0) {
                if ((aResult | bit) <= a) {
                    aResult |= bit;
                }
            }
        }
        return aResult == 0 ? -1 : a - aResult;
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
