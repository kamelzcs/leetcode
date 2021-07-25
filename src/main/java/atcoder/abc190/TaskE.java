package atcoder.abc190;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            from--;
            to--;

            adj[from].add(to);
            adj[to].add(from);
        }
        int k = in.nextInt();
        int[] colors = new int[k];
        Set<Integer> colorSet = new HashSet<>();
        for (int i = 0; i < k; i++) {
            colors[i] = in.nextInt();
            colors[i]--;
            colorSet.add(colors[i]);
        }
        int INF = Integer.MAX_VALUE / 10;
        Map<Integer, int[]> dist = new HashMap<>();
        for (int c : colors) {
            int[] dArray = new int[n];
            Arrays.fill(dArray, INF);
            dist.computeIfAbsent(c, x -> dArray)[c] =  1;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(c);
            int[] pointDist = dist.get(c);
            while (!queue.isEmpty()) {
                int color = queue.poll();
                int d = pointDist[color];
                for (int next : adj[color]) {
                    if (pointDist[next] == INF) {
                        queue.add(next);
                        pointDist[next] = d + 1;
                    }
                }
            }
        }
        int[][] dp = new int[1<<k][k];
        for(int[] d : dp) {
            Arrays.fill(d, INF);
        }
        for (int i = 0; i < k; i++) {
            dp[1<<i][i] = 1;
        }

        for (int mask = 0; mask < 1<<k; mask++) {
            for (int i = 0; i < k; i++) {
                int from = colors[i];
                if (dp[mask][i] == INF) {
                    continue;
                }
                int[] pointDist = dist.get(from);
                for (int j = 0; j < k; j++) {
                    int to = colors[j];
                    dp[mask | (1<<j)][j] = Math.min(dp[mask | (1<<j)][j], dp[mask][i] + pointDist[to] - 1);
                }
            }
        }
        int result = INF;
        for (int i = 0; i < k; i++) {
            result = Math.min(result, dp[(1<<k) - 1][i]);
        }
        if (result == INF) {
            out.println(-1);
        } else {
            out.println(result);
        }
    }
}
