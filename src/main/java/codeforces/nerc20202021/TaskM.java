package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TaskM {
    int n;
    Map<Integer, Set<Integer>> adj;
    Set<Integer> visited;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        visited = new HashSet<>();
        adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                int v = in.nextInt();
                int index = v + n;
                adj.computeIfAbsent(i, x -> new HashSet<>()).add(index);
                adj.computeIfAbsent(index, x -> new HashSet<>()).add(i);
            }
        }
        boolean found = false;
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                visited.add(i);
                Deque<Integer> result = dfs(i, new ArrayDeque<>());
                if (result.size() == 4) {
                    List<Integer> part = new ArrayList<>();
                    while (!result.isEmpty()) {
                        int v = result.pollFirst();
                        if (v <= n) {
                            part.add(v);
                        }
                    }
                    out.println(String.format("%d %d", part.get(0), part.get(1)));
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            out.println(-1);
        }
    }

    private Deque<Integer> dfs(int id, ArrayDeque<Integer> parents) {
//        System.out.println(String.format("%d %s", id, toString(parents)));
        if (parents.size() == 3 && adj.getOrDefault(id, Collections.emptySet()).contains(parents.peekFirst())) {
            parents.addLast(id);
            return parents;
        }

        parents.addLast(id);
        int prev = -1;
        if (parents.size() >= 4) {
            prev = parents.pollFirst();
        }

        for (int v : adj.getOrDefault(id, Collections.emptySet())) {
            if (visited.contains(v)) {
                continue;
            }
            visited.add(v);
            Deque<Integer> part = dfs(v, parents);
            if (part.size() >= 4) {
                return part;
            }
        }

        if (prev > 0) {
            parents.addFirst(prev);
        }
        parents.removeLast();
        return parents;
    }

    private String toString(ArrayDeque<Integer> parents) {
        StringBuilder result = new StringBuilder();
        ArrayDeque<Integer> dumb = new ArrayDeque<>(parents);
        while (!dumb.isEmpty()) {
            result.append(" ").append(dumb.pollFirst());
        }
        return result.toString();
    }
}
