package atcoder.abc176.a;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        String a = sc.next();
        if ("RRR".equals(a)) {
            pw.println(3);
        } else if (a.startsWith("RR") || a.endsWith("RR")) {
            pw.println(2);
        } else if (a.contains("R")) {
            pw.println(1);
        } else {
            pw.println(0);
        }
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
