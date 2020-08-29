package atcoder.abc171.d;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    Map<Integer, Integer> count = new HashMap<>();
    long sum;
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            count.put(data, count.getOrDefault(data, 0) + 1);
            sum += data;
        }
        int Q = sc.nextInt();
        long result = sum;
        for (int i = 0; i < Q; i++) {
            int b = sc.nextInt();
            int c = sc.nextInt();
            result += (c - b) * count.getOrDefault(b, 0);
            count.put(c, count.getOrDefault(b, 0) + count.getOrDefault(c, 0));
            count.remove(b);
            pw.println(result);
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
