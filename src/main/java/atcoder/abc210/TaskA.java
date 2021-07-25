package atcoder.abc210;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();

        int result = 0;
        result += Math.min(n, a) * x + (n - Math.min(n, a)) * y;
        out.println(result);
    }
}
