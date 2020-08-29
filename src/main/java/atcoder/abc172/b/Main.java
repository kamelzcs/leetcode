package atcoder.abc172.b;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        String a = sc.next();
        String b = sc.next();
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result += (a.charAt(i) == b.charAt(i)) ? 0 : 1;
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
