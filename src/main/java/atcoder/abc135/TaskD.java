package atcoder.abc135;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int MODULE = 1_000_000_007;
        String str = in.next();
        int len = str.length();
        int[] mod = new int[len];
        mod[len - 1] = 1;
        int MOD = 13;
        for (int i = len - 2; i >= 0; i--){
            mod[i] = (mod[i + 1] * 10) % MOD;
        }

        long[][] count = new long[len + 1][MOD];
        count[len][0] = 1;
        for (int i = len - 1; i >= 0; i--) {
            char c = str.charAt(i);
            for (int next = 0; next < MOD; next++) {
                if (c == '?') {
                    for (int j = 0; j < 10; j++) {
                        int nextState = (next + j * mod[i]) % MOD;
                        count[i][nextState] += count[i + 1][next];
                        count[i][nextState] %= MODULE;
                    }
                } else {
                    int nextState = (next + (c - '0') * mod[i]) % MOD;
                    count[i][nextState] += count[i + 1][next];
                    count[i][nextState] %= MODULE;
                }
            }
        }
        out.println(count[0][5]);
    }
}
