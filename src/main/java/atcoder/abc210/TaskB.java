package atcoder.abc210;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String str = in.next();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '1') {
                if ((i % 2) == 0) {
                    out.println("Takahashi");
                } else {
                    out.println("Aoki");
                }
                break;
            }
        }
    }
}
