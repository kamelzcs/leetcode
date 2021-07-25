package atcoder.abc187;

import common.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskF {
    int[] dp;
    int n;
    int[] adj;
    int m;
    int[] nodes;
    int INIT = 100000;
    Map<Integer, Integer> valueToIndex = new HashMap<>();

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        dp = new int[1 << n];
        Arrays.fill(dp, INIT);
        adj = new int[n];
        nodes = new int[n];
        for (int i = 0; i < n; i++) {
            valueToIndex.put((1 << i), i);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            from--;
            int to = in.nextInt();
            to--;

            nodes[from] |= (1 << to);
            nodes[to] |= (1 << from);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((nodes[i] & (1 << j)) == 0) {
                    adj[i] |= (1 << j);
                    adj[j] |= (1 << i);
                }
            }
        }

        List<Integer> splits = split((1 << n) - 1);
        int result = splits.stream().map(this::func).max(Comparator.naturalOrder()).get();
        out.println(result);
    }

    private List<Integer> split(int mask) {
        if (mask == 0) {
            return new ArrayList<>();
        }
        int part = dfs(mask);
        List<Integer> result = split(mask ^ part);
        result.add(part);
        return result;
    }

    private int dfs(int mask) {
        int lb = lowBit(mask);
        return dfs(mask, lb);
    }

    private int dfs(int mask, int visited) {
        for (int j = 0; j < n; j++) {
            if ((visited & (1<<j)) > 0) {
                continue;
            }
            if ((adj[j] & visited) == 0) {
                continue;
            }
            if ((mask & (1 << j)) > 0) {
                return dfs(mask, visited | (1 << j));
            }
        }
        return visited;
    }

    int lowBit(int x) {
        return x & (-x);
    }

    private int func(int mask) {
//        System.out.println(mask);
        if (mask == 0) {
            return 0;
        }
        int result = dp[mask];
        if (result != INIT) {
            return result;
        }

        int lb = lowBit(mask);
        List<Integer> parts = find(mask, valueToIndex.get(lb), lb);
//        System.out.println(parts.stream().map(Object::toString).collect(Collectors.joining(",")));
        for (int part : parts) {
            result = Math.min(result, 1 + func(mask ^ part));
        }
        dp[mask] = result;
        return result;
    }

    private List<Integer> find(int mask, int lastIndex, int state) {
//        int lb = lowBit(mask);
//        int id = valueToIndex.get(lb);
        List<Integer> result = new ArrayList<>();
        if (lastIndex >= 0 && 1 << (lastIndex + 1) > mask) {
            result.add(state);
            return result;
        }
        int index = lastIndex + 1;
        if (index < n && (state & adj[index]) == 0 && ((1 << index) & mask) > 0) {
            result.addAll(find(mask, index, state | (1 << index)));
        }

        result.addAll(find(mask, index, state));
        return result;
    }
}
