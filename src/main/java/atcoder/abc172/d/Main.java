package atcoder.abc172.d;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        long N = sc.nextLong();
        long result = 0;
        for (long k = 1; k <= N; k++) {
            result += (N / k) * (1 + N / k) * k / 2;
        }
        pw.println(result);
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
