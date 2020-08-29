package atcoder.abc175.c;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        long total = 0;
        int currentMax = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int val = sc.nextInt();
            int nextMax = Math.max(currentMax, val);
            if (i > 0) {
                total += nextMax - val;
            }
            currentMax = nextMax;
        }
        pw.println(total);
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
