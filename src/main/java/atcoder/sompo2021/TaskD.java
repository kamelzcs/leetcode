package atcoder.sompo2021;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String x = in.next();
        long m = in.nextLong();
        int[] digits = new int[x.length()];
        int d = 0;
        for (int i = 0; i < x.length(); i++) {
            digits[i] = x.charAt(i) - '0';
            d = Math.max(d, digits[i] + 1);
        }
        if (digits.length == 1) {
            if (Long.parseLong(x) > m) {
                out.println(0);
            } else {
                out.println(1);
            }
            return;
        }
        long l = d;
        long r = m;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long sum = 0;
            boolean valid = true;
            for (int digit : digits) {
                if ((m - digit) / mid < sum) {
                    valid = false;
                    break;
                } else {
                    sum = (sum * mid + digit);
                }
            }
            if (!valid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
//        out.println(Math.max(0, r - d + 1));
        out.println(l - d);
    }
}
