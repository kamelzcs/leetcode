package atcoder.abc174.a;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int a = sc.nextInt();
        pw.println(a >= 30 ? "Yes" : "No");
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
