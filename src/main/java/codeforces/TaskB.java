package codeforces;

import common.InputReader;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskB {
    int n;
    int m ;
    boolean[] visited;
    Map<Integer, List<Integer>> adj = new HashMap<>();
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            adj.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            adj.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }
        Deque<Integer> path = dfs(1);
        Deque<Integer> secondPart = dfs(1);
        int total = path.size() + secondPart.size() - 1;
        out.println(total);
        while (secondPart.size() > 1) {
            out.print(secondPart.getLast());
            out.print(" ");
            secondPart.removeLast();
        }
        while (!path.isEmpty()) {
            out.print(path.getFirst());
            out.print(" ");
            path.removeFirst();
        }
    }

    private Deque<Integer> dfs(int v) {
        visited[v] = true;
        Deque<Integer> result = new ArrayDeque<>();
        for (int neighbor : adj.get(v)) {
            if (visited[neighbor]) {
                continue;
            }
            result =  dfs(neighbor);
            break;
        }
        result.addFirst(v);
        return result;
    }
}
