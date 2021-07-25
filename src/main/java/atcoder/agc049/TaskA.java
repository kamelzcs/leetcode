package atcoder.agc049;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    int n;
    int[][] adj;
    String[] graph;
    boolean[] visited;
    double result = 0;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        adj = new int[n][n];
        graph =  new String[n];
        visited  = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = in.next();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adj[i][j] = j == i ? 1 : graph[i].charAt(j) - '0';
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adj[i][j] = adj[i][j] | (adj[i][k] & adj[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            int groupSize = 0;
            int parentSize = 0;
            visited[i] = true;
            for (int j = 0; j < n; j++) {
                if (adj[i][j] == 1 && adj[j][i] == 1) {
                    visited[j] = true;
                    groupSize++;
                }

                if (adj[j][i] == 1 && adj[i][j] == 0) {
                    parentSize++;
                }
            }
            result += (groupSize) * 1.0 / (groupSize + parentSize);
        }

        out.println(result);
    }
}
