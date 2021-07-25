package atcoder.arc111;

import common.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskB {
    class DSJ {
        int[] parent;
        int n;

        DSJ(int n) {
            this.n = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int query(int l) {
            int ll = parent[l];
            if (ll == l) {
                return l;
            }
            parent[l] = query(ll);
            return parent[l];
        }

        void merge(int l, int r) {
            int ll = query(l);
            int rr = query(r);

            if (ll == rr) {
                return;
            }

            parent[rr] = ll;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int INF = 400000;
        DSJ dsj = new DSJ(n + INF);
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            from = from - 1 + n;
            to = to - 1 + n;
            data[i][0] = from;
            data[i][1] = to;
            dsj.merge(from, i);
            dsj.merge(to, i);
        }
        Map<Integer, List<Set<Integer>>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(dsj.query(i), x -> {
                List<Set<Integer>> sets = new ArrayList<>();
                sets.add(new HashSet<>());
                sets.add(new HashSet<>());
                return sets;
            }).get(0).add(i);

            map.computeIfAbsent(dsj.query(data[i][0]), x -> {
                List<Set<Integer>> sets = new ArrayList<>();
                sets.add(new HashSet<>());
                sets.add(new HashSet<>());
                return sets;
            }).get(1).add(data[i][0]);

            map.computeIfAbsent(dsj.query(data[i][1]), x -> {
                List<Set<Integer>> sets = new ArrayList<>();
                sets.add(new HashSet<>());
                sets.add(new HashSet<>());
                return sets;
            }).get(1).add(data[i][1]);
        }

        int result = 0;
        for (int i = 0; i < n + INF; i++) {
            if (dsj.parent[i] == i && map.containsKey(i)) {
                result += Math.min(map.get(i).get(0).size(), map.get(i).get(1).size());
            }
        }

        out.println(result);
    }
}
