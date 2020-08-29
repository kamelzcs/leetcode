package atcoder.agc046.a;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
    void solve(Scanner sc, PrintWriter pw) {
        int x = sc.nextInt();
        int g = gcd(360, x);
        pw.println((360 / g));
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
