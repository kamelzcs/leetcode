package atcoder.abc171.e;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }
        int totalXor = 0;
        for (int i = 0; i < N - 1; i += 2) {
            totalXor ^= (data[i] ^ data[i + 1]);
        }
        List<String> result = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            result.add(String.valueOf(totalXor ^ data[i]));
        }
        pw.println(String.join(" ", result));
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
