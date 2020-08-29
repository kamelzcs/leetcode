package atcoder.abc176.b;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int[] sticks = new int[N];
        for (int i = 0; i < N; i++) {
            sticks[i] = sc.nextInt();
        }
        Arrays.sort(sticks);
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1 ; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (sticks[i] == sticks[j] || sticks[j] == sticks[k]) {
                        continue;
                    }
                    if (sticks[i] + sticks[j] > sticks[k])  {
                        result++;
                    }
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
