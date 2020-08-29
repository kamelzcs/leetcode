package atcoder.abc173.a;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0 ; i < N; i++) {
            String str = sc.next();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        String[] results = new String[]{"AC", "WA", "TLE", "RE"};
        for (String result : results) {
            pw.println(String.format("%s x %d", result, map.getOrDefault(result, 0)));
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
