package atcoder.abc174.e;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int K = sc.nextInt();
        long[] data = new long[N];
        long left = 1;
        long right = 1;
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextLong();
            right = Math.max(right, data[i]);
        }
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long totatCount = 0;
            for (int i = 0; i < N; i++) {
                totatCount += (data[i] + mid - 1) / (mid);
            }
            //System.out.println(String.format("%d %d %d %d", left, right, mid, totatCount));
            if (totatCount > N + K) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        pw.println(left);
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
