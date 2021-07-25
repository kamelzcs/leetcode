package atcoder.abc188;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskF {
    Map<Long, Long> dp = new HashMap<>();
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long  x = in.nextLong();
        long y = in.nextLong();
        out.println(f(x, y));
    }

    private long f(long x, long y) {
        if (y == 1) {
            return Math.abs(x - 1);
        }
        if (dp.containsKey(y)) {
            return dp.get(y);
        }
        long ans = Math.abs(y - x);
        if ((y & 1) == 0) {
            ans = Math.min(ans, f(x, y / 2) + 1);
        } else {
            ans = Math.min(ans, Math.min(f(x, (y + 1) / 2) + 2, f(x, (y - 1) / 2) + 2));
        }
        dp.put(y, ans);
        return ans;
    }
}
