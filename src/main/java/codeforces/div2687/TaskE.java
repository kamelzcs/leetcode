package codeforces.div2687;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        Arrays.sort(data);
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k + 1; i++) {
            pq.add(0L);
        }
        long total = 0;
        for (int i = n - 1; i >= 0; i--) {
            long top = pq.poll();
            total += top;
            pq.add(top + data[i]);
        }
        out.println(total);
    }
}
