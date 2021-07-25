package atcoder.abc183;

import common.InputReader;

import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskC {
    class Node {
        int cost;
        int occurs;

        public Node(int cost, int occurs) {
            this.cost = cost;
            this.occurs = occurs;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = in.nextInt();
            }
        }
        Map<Integer, Integer>[][] dp = new Map[1 << n][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = new HashMap<>();
            }
        }
        // cost, occurs
        dp[1][0].put(0, 1);
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int p = 0; p < n; p++) {
                if ((mask & (1 << p)) == 0) {
                    continue;
                }
                if (dp[mask][p].isEmpty()) {
                    continue;
                }
                for (int nextP = 0; nextP < n; nextP++) {
                    if ((mask & (1 << nextP)) > 0) {
                        continue;
                    }
                    for (Map.Entry<Integer, Integer> entry : dp[mask][p].entrySet()) {
                        int nextCost = entry.getKey() + d[p][nextP];
                        if (nextCost > k) {
                            continue;
                        }

                        int nextMask = mask | (1 << nextP);
                        dp[nextMask][nextP].put(nextCost, dp[nextMask][nextP].getOrDefault(nextCost, 0) + entry.getValue());
                    }
                }


            }
        }
        long result = 0;
        for (int i = 1; i < n; i++) {
            for (Map.Entry<Integer, Integer> entry : dp[(1 << n) - 1][i].entrySet()) {
                if (entry.getKey() + d[i][0] == k) {
                    result += entry.getValue();
                }
            }
        }
        out.println(result);
    }
}
