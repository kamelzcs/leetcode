package atcoder.abc210;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int result = 0;
        Map<Integer, Integer> count = new HashMap<>();
        int[] data = new int[n];
        for (int i = 0; i < k; i++) {
            int v = in.nextInt();
            data[i] = v;
            count.put(v, count.getOrDefault(v, 0) + 1);
        }
        result = count.keySet().size();
        for (int i = k; i < n; i++) {
            int v = in.nextInt();
            data[i] = v;

            int remove = data[i - k];
            int preCount = count.get(remove);
            if (preCount == 1) {
                count.remove(remove);
            } else {
                count.put(remove, preCount - 1);
            }
            count.put(v, count.getOrDefault(v, 0) + 1);
            result = Math.max(result, count.keySet().size());
        }
        out.println(result);
    }
}
