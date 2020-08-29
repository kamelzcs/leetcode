package atcoder.abc171.b;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }
        Arrays.sort(data);
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += data[i];
        }
        pw.println(sum);
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
