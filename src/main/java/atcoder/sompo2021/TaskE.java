package atcoder.sompo2021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskE {
    class Data {
        int index;
        long totalCost;

        public Data(int index, long totalCost) {
            this.index = index;
            this.totalCost = totalCost;
        }
    }
    class Node {
        int index;
        int cost;
        int periodic;

        public Node(int index, int cost, int periodic) {
            this.index = index;
            this.cost = cost;
            this.periodic = periodic;
        }
    }
    List<Node>[] adj;
    long[] cost;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        x--;
        y--;
        adj = new List[n];
        cost = new long[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m ;i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            a--;
            b--;
            int t = in.nextInt();
            int k = in.nextInt();
            adj[a].add(new Node(b, t, k));
            adj[b].add(new Node(a, t, k));
        }
        PriorityQueue<Data> pq = new PriorityQueue<>(Comparator.comparingLong(data -> data.totalCost));
        long INF = Long.MAX_VALUE / 10;
        Arrays.fill(cost, INF);
        cost[x] = 0;
        pq.add(new Data(x, 0));
        while (!pq.isEmpty()) {
            Data top = pq.poll();
            if (cost[top.index] < top.totalCost) {
                continue;
            }
            if (top.index == y) {
                out.println(cost[y]);
                return;
            }
            for (int i = 0; i < adj[top.index].size(); i++) {
                Node node = adj[top.index].get(i);
                long nxtCost = (cost[top.index] + node.periodic - 1) / node.periodic * node.periodic + node.cost;
                if (nxtCost >= cost[node.index]) {
                    continue;
                }
                cost[node.index] = nxtCost;
                pq.add(new Data(node.index, nxtCost));
            }
        }
        out.println(-1);
    }
}
