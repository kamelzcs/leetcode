package atcoder.agc026.b;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            new Main().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    void solve(Scanner sc, PrintWriter pw) {
        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = sc.nextLong();
        long D = sc.nextLong();
        pw.write(solve(A, B, C, D) ? "Yes" : "No");
        pw.write("\n");
    }

    boolean solve(long A, long B, long C, long D) {
        if (A < B) {
            return false;
        }
        long g = gcd(B, D);
        long remainderA = A % B;
        if (remainderA > C) {
            return false;
        }
        long delta = remainderA % g;
        if (delta + D < B) {
            return false;
        }
        long cPlusOne = C - delta;
        long bMinusOne = B - delta;
        long candidate = (cPlusOne + g - 1) / g;
        long candidate2 = (cPlusOne) / g + 1;
        return !(valide(candidate, g, cPlusOne, bMinusOne) || valide(candidate2, g, cPlusOne, bMinusOne));
    }

    private boolean valide(long candidate2, long g, long cPlusOne, long bMinusOne) {
        long value = candidate2 * g;
        return value > cPlusOne && value < bMinusOne;
    }
}
