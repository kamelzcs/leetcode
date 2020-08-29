package atcoder.abc171.f;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    long MOD = 1_000_000_007;
    void solve(Scanner sc, PrintWriter pw) {
        int K = sc.nextInt();
        String S = sc.next();
        int L = S.length();
        long combination = 1;
        long a = 1;
        long b = power(26, K);
        long result = (combination * a * b) % MOD;
        long inverse26 = power(26, MOD - 2);
        //System.out.println(String.format("%d %d %d", combination, a, b));
        for (int w = 1; w <= K; w++) {
            combination = (((combination * (w - 1 + L)) % MOD) * power(w, MOD - 2)) % MOD;
            a = (a * 25) % MOD;
            b = (b * inverse26) % MOD;
            result = (result + (combination * ((a * b) % MOD))%MOD) % MOD;
            //System.out.println(String.format("%d %d %d", combination, a, b));
        }
        pw.println(result);
    }

    private long power(int v, long k) {
        if (k == 0) {
            return 1;
        }
        long part = power(v, k / 2);
        long candidate = (part * part) % MOD;
        if ((k % 2) > 0) {
            candidate = (candidate * v) % MOD;
        }
        return candidate;
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
