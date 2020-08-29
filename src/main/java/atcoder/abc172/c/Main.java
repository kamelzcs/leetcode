package atcoder.abc172.c;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int M = sc.nextInt();
        long K = sc.nextLong();
        long[] dataA = new long[N];
        long[] dataB = new long[M];
        for (int i = 0; i < N; i++) {
            dataA[i] = sc.nextInt();
        }
        for (int i = 1; i < N; i++) {
            dataA[i] += dataA[i - 1];
        }

        for (int i = 0; i < M; i++) {
            dataB[i] = sc.nextInt();
        }

        for (int i = 1; i < M; i++) {
            dataB[i] += dataB[i - 1];
        }

        int result = 0;
        int nextB = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (nextB < M && dataA[i] + dataB[nextB] <= K) {
                result = Math.max(result, i + 1 + nextB + 1);
                nextB++;
            }
        }
        for (int i = 0; i < N; i++) {
            if (dataA[i] <= K) {
                result = Math.max(result, i + 1);
            }
        }
        for (int i = 0; i < M; i++) {
            if (dataB[i] <= K) {
                result = Math.max(result, i + 1);
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
