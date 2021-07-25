package atcoder.abc188;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] value = new int[n];
        List<Integer>[] nodes = new List[n];
        int[] max = new int[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            value[i] = in.nextInt();
            max[i] = value[i];
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            from--;
            int to = in.nextInt();
            to--;
            nodes[from].add(to);
        }
        int delta = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < nodes[i].size(); j++) {
                int v = nodes[i].get(j);
                delta = Math.max(delta, -value[i] + max[v]);
                max[i] = Math.max(max[i], max[v]);
            }
        }
        out.println(delta);
    }
}
