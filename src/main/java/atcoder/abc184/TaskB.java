package atcoder.abc184;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long x = in.nextInt();
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            x = Math.max(0, x + (c == 'o' ? 1 : -1));
        }
        out.println(x);
    }
}
