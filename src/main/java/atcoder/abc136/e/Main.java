package atcoder.abc136.e;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    int N;
    int K;
    int[] data;
    int sum;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        K = sc.nextInt();
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
            sum += data[i];
        }
        int ans = 1;
        for (int p = 1; p * p <= sum; p++) {
            if ((sum % p) != 0) {
                continue;
            }
            if (work(p)) {
                ans = Math.max(ans, p);
            }
            if (work(sum / p)) {
                ans = Math.max(ans, sum / p);
            }
        }
        pw.println(ans);
    }

    private boolean work(int n) {
        int totalDiffs = 2 * K;
        PriorityQueue<Integer> positive = new PriorityQueue<Integer>(N, Comparator.<Integer>comparingInt(x -> x).reversed());
        PriorityQueue<Integer> negative = new PriorityQueue<Integer>(N, Comparator.<Integer>comparingInt(x -> x).reversed());
        int usedSteps = 0;
        int total = 0;
        int half = 0;

        for (int i = 0; i < N; i++) {
            int remain = data[i] % n;
            if (remain * 2 > n) {
                usedSteps += (n - remain);
                total += (n - remain);
                positive.add(n - remain);
            } else if (remain * 2 < n){
                usedSteps += remain;
                total -= remain;
                negative.add(remain);
            } else {
                usedSteps += remain;
                half++;
            }
        }
        if (usedSteps > totalDiffs) {
            return false;
        }
        if (total == 0) {
            return true;
        }
        if (total > 0) {
            total -= n * half;
            if (total <= 0) {
                return true;
            }
            while (!positive.isEmpty() && usedSteps <= totalDiffs && total > 0) {
                int top = positive.poll();
                usedSteps += (n - 2 * top);
                total -= n;
            }

            if (usedSteps > totalDiffs) {
                return false;
            }
            if (total <= 0) {
                return true;
            }

            return usedSteps + total <= totalDiffs;
        } else {
            total *= -1;
            total -= n * half;
            if (total <= 0) {
                return true;
            }
            while (!negative.isEmpty() && usedSteps <= totalDiffs && total > 0) {
                int top = negative.poll();
                usedSteps += (n - 2 * top);
                total -= n;
            }

            if (usedSteps > totalDiffs) {
                return false;
            }
            if (total <= 0) {
                return true;
            }

            return usedSteps + total <= totalDiffs;
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
