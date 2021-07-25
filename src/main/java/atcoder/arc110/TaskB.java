package atcoder.arc110;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String t = in.next();
        int[] match = match(t);
        int sum  = Arrays.stream(match).sum();
        long endIndex = 3L * 10_000_000_000L - n;
        long result = endIndex / 3 * sum;
        for (int i = 0; i <= endIndex % 3; i++) {
            result += match[i];
        }
        out.println(result);
    }

    private int[] match(String t) {
        int[] result = new int[3];
        for (int i =0 ; i < result.length; i++) {
            result[i] = match(t, i);
        }
        return result;
    }

    private int match(String t, int offset) {
        for (int i = 0; i < t.length(); i++) {
            int targetIndex = (i + offset) % "110".length();
            if (t.charAt(i) != "110".charAt(targetIndex)) {
                return 0;
            }
        }
        return 1;
    }
}
