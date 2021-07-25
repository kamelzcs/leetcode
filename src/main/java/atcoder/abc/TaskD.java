package atcoder.abc;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskD {
    Map<Integer, Integer> count = new HashMap<>();
    boolean exist = false;
    String number;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        number = in.next();
        for (int i = 0; i < number.length(); i++) {
            int d = number.charAt(i) - '0';
            count.put(d, count.getOrDefault(d, 0) + 1);
        }
        for (int i = 1; i <= 9; i++) {
            Map<Integer, Integer> curCount = new HashMap<>();
            curCount.put(i, 1);
            dfs(1, i % 8, 1,curCount);
        }
        out.println(exist ? "Yes" : "No");
    }

    private void dfs(int len, int v, int base, Map<Integer, Integer> curCount) {
        if (len == number.length()) {
            if (v == 0) {
                if (contains(curCount)) {
                    exist = true;
                }
            }
            return;
        }
        if (len == 3) {
            if (v == 0) {
                if (contains(curCount)) {
                    exist = true;
                }
            }
            return;
        }
        int nextBase = base * 10;
        for (int i = 1; i <= 9; i++) {
            curCount.put(i, curCount.getOrDefault(i, 0) + 1);
            dfs(len + 1, (v + i * nextBase) % 8, nextBase, curCount);
            curCount.put(i, curCount.get(i) - 1);
        }
    }

    private boolean contains(Map<Integer, Integer> curCount) {
        for (int key : curCount.keySet()) {
            if (count.getOrDefault(key, 0) < curCount.get(key)) {
                return false;
            }
        }
        return true;
    }
}
