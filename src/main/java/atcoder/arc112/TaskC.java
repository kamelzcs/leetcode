package atcoder.arc112;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskC {
    int[][] dp;
    List<Integer>[] adj;
    int[] parent;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        dp = new int[n + 1][2];
        adj = new List[n + 1];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        parent = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            parent[i] = in.nextInt();
            adj[parent[i]].add(i);
        }
        for (int i = n; i >= 1; i--) {
            if (adj[i].isEmpty()) {
                dp[i][1] = 1;
            } else if (adj[i].size() == 1){
                dp[i][0] = dp[adj[i].get(0)][0];
                dp[i][1] = dp[adj[i].get(1)][1] + 1;
            } else {
                
            }
        }
    }
}
