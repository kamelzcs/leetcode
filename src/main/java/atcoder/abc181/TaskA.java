package atcoder.abc181;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String result = (n % 2) == 0 ? "White" : "Black";
        out.println(result);
    }
}
