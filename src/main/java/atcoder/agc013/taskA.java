package atcoder.agc013;

import common.InputReader;
import java.io.PrintWriter;

public class taskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int N = in.nextInt();
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = in.nextInt();
        }
        int result = 0;
        int state = 0; // 1 increase, 2 decrease
        for (int i = 1; i < N; i++) {
            if (data[i] == data[i - 1]) {
                continue;
            }

            if (data[i] > data[i - 1]) {
                if (state == 1) {
                } else {
                    if (state == 0) {
                        state = 1;
                    } else {
                        state = 0;
                        result++;
                    }
                }
            } else {
                if (state == 0) {
                    state = 2;
                } else if (state == 1) {
                    state = 0;
                    result++;
                }
            }
        }
        out.println(result + 1);
    }
}
