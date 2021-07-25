package atcoder.abc187;

import common.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TaskE {
    class Edge {
        int from;
        int to;
//        int index;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return from * to;
        }
    }

    int n;
    List<Integer>[] adj;
    long[] values;
    long[] weight;
    int[][] points;
    long[] remove;
    Map<Edge, Integer> edgeIndex = new HashMap<>();
    long[] dp;
    long INIT = -1;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        adj = new List[n + 1];
        points = new int[n + 1][2];
        values = new long[n + 1];
        dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            points[i][0] = from;
            points[i][1] = to;
            adj[from].add(to);
            adj[to].add(from);
        }

        int q = in.nextInt();
        weight = new long[q];
        for (int i = 0; i < q; i++) {
            int type = in.nextInt();
            int pointIndex = in.nextInt();
            Edge edge = new Edge(points[pointIndex][1], points[pointIndex][0]);
            if (type == 1) {
            } else {
                edge = new Edge(points[pointIndex][0], points[pointIndex][1]);
            }
            int lastIndex = edgeIndex.getOrDefault(edge, -1);
            edgeIndex.put(edge, i);
            weight[i] = in.nextInt() + (lastIndex < 0 ? 0 : weight[lastIndex]);
        }

        func(-1, 1);
        dfs1(-1, 1);
        for (int i = 1; i <= n; i++) {
            out.println(dp[i]);
        }
    }

    private long func(int prev, int cur) {
        long result = 0;
        for (int v : adj[cur]) {
            if (v == prev) {
                continue;
            }

            result += func(cur, v);
            Edge back = new Edge(v, cur);
            Integer backId = edgeIndex.get(back);
            if (backId != null) {
                result += weight[backId];
            }
        }
        dp[cur] = result;
        return result;
    }

    private void dfs1(int prev, int cur) {
//        System.out.println(String.format("%d %d", prev, cur));
        Edge edge = new Edge(prev, cur);
        Integer id = edgeIndex.get(edge);
        long delta = 0;
        if (id != null) {
            delta += weight[id];
        }
        Edge backEdge = new Edge(cur, prev);
        Integer backId = edgeIndex.get(backEdge);
        if (backId != null) {
            delta -= weight[backId];
        }
        if (prev > 0) {
            dp[cur] = dp[prev] + delta;
        }
        List<Integer> nodes = adj[cur];
        for (int v : nodes) {
            if (v == prev) {
                continue;
            }
            dfs1(cur, v);
        }
    }
}
