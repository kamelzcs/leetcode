package atcoder.arc108;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TaskC {
    class Node {
        int v;
        int c;

        public Node(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }
    int[] color;
    Set<Integer> remain = new HashSet<>();
    int n;
    int m;
    List<Node>[] adj;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        color = new int[n + 1];
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            remain.add(i);
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            adj[u].add(new Node(v, c));
            adj[v].add(new Node(u, c));
        }

        color[1] = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int top = queue.poll();
            for (int i = 0; i < adj[top].size(); i++) {
                Node node = adj[top].get(i);
                if (color[node.v] == 0) {
                    int nextColor = color[top] == node.c ? (1 + (node.c % n)): node.c;
                    color[node.v] = nextColor;
                    queue.add(node.v);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            out.println(color[i]);
        }
    }

    private void dfs(int v, int lastC, int state) {
        remain.remove(lastC);
        if (state == 1) {
            color[v] = -1; // special
        } else {
            color[v] = lastC;
        }
        for (Node node : adj[v]) {
            if (color[node.v] == 0) {
                dfs(node.v, node.c, state ^ 1);
            }
        }
    }
}
