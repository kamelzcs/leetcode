package atcoder.sompo2021;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x = in.nextInt();
        out.println(100 - (x % 100));
    }
}
