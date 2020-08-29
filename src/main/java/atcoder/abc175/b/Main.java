package atcoder.abc175.b;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        String s = sc.next();
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            total += (s.charAt(i) - '0');
        }
        String result = (total % 9) == 0? "Yes" : "No";
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
