package atcoder.abc185;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] data = new int[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.nextInt();
        }
        out.println(Arrays.stream(data).min().getAsInt());
    }
}
