package atcoder.abc176.c;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        long X = sc.nextLong();
        long K = sc.nextLong();
        long D = sc.nextLong();
        BigInteger[] B = new BigInteger[4];
        B[0] = BigInteger.ZERO;
        B[1] = BigInteger.valueOf(K);
        BigInteger[] divideAndRemain = BigInteger.valueOf(X).add(BigInteger.valueOf(K).multiply(BigInteger.valueOf(D))).divideAndRemainder(BigInteger.valueOf(2L * D));
        B[2] = divideAndRemain[0];
        B[3] = B[2].add(BigInteger.ONE);
        BigInteger result = BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.valueOf(Long.MAX_VALUE));
        for (int i = 0; i < B.length; i++) {
            if (B[i].compareTo(BigInteger.valueOf(K)) > 0 || B[i].compareTo(BigInteger.ZERO) < 0) {
                continue;
            }
            BigInteger candidate = BigInteger.valueOf(X).add(BigInteger.valueOf(K).subtract(BigInteger.valueOf(2L).multiply(B[i])).multiply(BigInteger.valueOf(D)));
            if (candidate.abs().compareTo(result.abs()) < 0) {
                result = candidate;
//                System.out.println(B[i]);
            }
        }
        pw.println(result.abs());
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}
