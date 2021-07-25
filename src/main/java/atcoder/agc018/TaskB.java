package atcoder.agc018;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Queue<Integer>[] queues = new Queue[n];
        Set<Integer> removed = new HashSet<>();
        for (int i = 0; i < n; i++) {
            queues[i] = new ArrayDeque<>();

            for (int j = 0; j < m; j++) {
                queues[i].add(in.nextInt() - 1);
            }
        }
        int result = n;
        int left = n * m;
        boolean exit = false;
        while (!exit) {
            int[] count = new int[m];
            for (int i = 0; i < n; i++) {
                while (!queues[i].isEmpty() && removed.contains(queues[i].peek())) {
                    queues[i].poll();
                }
                if (queues[i].isEmpty()) {
                    exit = true;
                    break;
                }
                count[queues[i].peek()]++;
            }
            if (!exit) {
                int maxValue = 0;
                for (int i = 0; i < m; i++) {
                    maxValue = Math.max(maxValue, count[i]);
                }

                result = Math.min(result, maxValue);
                for (int i = 0; i < n; i++) {
                    if (queues[i].isEmpty()) {
                        exit = true;
                        break;
                    }
                    if (count[queues[i].peek()] == maxValue) {
                        left--;
                        removed.add(queues[i].poll());
                    }
                }
            }
        }
        out.println(result);
    }
}
