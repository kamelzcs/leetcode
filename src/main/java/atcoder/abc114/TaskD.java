package atcoder.abc114;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] count = new int[n + 1];
        int[] sum = new int[101];
        for (int i = 2; i <= n; i++) {
            count[i] = factors(i, n);
            sum[count[i]]++;
        }
//        System.out.println(Arrays.toString(count));

        for (int j = sum.length - 2; j >= 0; j--) {
            sum[j] += sum[j + 1];
        }

//        System.out.println(Arrays.toString(sum));

        int result = 0;
        // x ^ 74
        result += sum[74];

        // x^2 * y^24
        result += Math.max(0, ((sum[2] - 1) * sum[24]));

        // x^4 * y^14
        result += Math.max(0, ((sum[4] - 1) * sum[14]));

        // x^2 * y ^ 4 * z ^ 4
        result += Math.max(0, (sum[4] * (sum[4] - 1) * (sum[2] - 2)) / 2);

        out.println(result);
    }

    private int factors(int v, int n) {
        for (int i = 2; i * i <= v; i++) {
            if ((v % i) == 0) {
                return 0;
            }
        }

        int remain = n;
        int result = 0;
        while (remain > 0) {
            result += remain / v;
            remain /= v;
        }
        return result;
    }
}
