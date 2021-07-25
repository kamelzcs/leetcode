package atcoder.abc191;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if (c == 0) {
            if (a > b) {
                out.println("Takahashi");
            } else {
                out.println("Aoki");
            }
        } else {
            if (b > a) {
                out.println("Aoki");
            } else {
                out.println("Takahashi");
            }
        }
    }
}
