package atcoder.abc182;

import common.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        String data = String.valueOf(n);
        if (n % 3 == 0) {
            out.println("0");
            return;
        }

        int len = data.length();
        int result = len + 1;
        for (int i = 1; i < (1<<(len)); i++) {
            int sum = 0;
            for (int j = 0; j < len;j++) {
                if ((i & (1<<j)) > 0) {
                    sum = (sum + data.charAt(j) - '0') % 3;
                }
            }
            if (sum == 0) {
                result = Math.min(result, len - (Integer.bitCount(i)));
            }
        }
        out.println(result >= len ? -1 : result);
    }
}
