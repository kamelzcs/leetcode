package atcoder.aising2020.b;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int num = sc.nextInt();
            if ((i % 2) == 1 && (num % 2 == 1)) {
                result++;
            }
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
