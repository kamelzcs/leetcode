package atcoder.abc171.c;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        long N = sc.nextLong();
        pw.println(func(N - 1));
    }

    private String func(long n) {
        if (n < 26) {
            return String.valueOf((char)('a' + n));
        }
        return func(n / 26 - 1) + String.valueOf((char)('a' + (n % 26)));
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
