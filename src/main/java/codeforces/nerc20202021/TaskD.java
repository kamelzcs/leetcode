package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        if (a > b) {
            a = n + 1 - a;
            b = n + 1 - b;
        }

        int[] data = new int[m];
        for (int i = 0; i < m; i++) {
            data[i] = in.nextInt();
        }

        Arrays.sort(data);
        for (int i = 0; i < m; i++) {
            data[i] = i + 1 - data[i];
        }

        int s = b - 1;

        int max = Math.min(b - a - 1, m);
        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        while (result < max) {
            pq.add(data[result]);
            int top = pq.peek();
            if (s - 1 + top < result + 1) {
                break;
            }
            result++;
        }
        out.println(result);
    }
}
