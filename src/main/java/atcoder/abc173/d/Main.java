package atcoder.abc173.d;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        String st = sc.next();
        pw.println(String.format("%d %s", a + b + c, st));
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
