package atcoder.abc174.c;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        long K = sc.nextInt();
        long current = 0;
        Set<Long> exist = new HashSet<>();
        int result = -1;
        for (int i = 1; i <= K ;i++) {
            current = (current * 10 + 7) % K;
            if (exist.contains(current)) {
                break;
            } else {
                if (current == 0) {
                    result = i;
                    break;
                } else {
                    exist.add(current);
                }
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
