package atcoder.arc109;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
    int[][] dp;
    int INIT = -1;
    int[] values;
    int n;
    int[] mod;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        int k = in.nextInt();
        values = new int[n];
        String str = in.next();
        for (int i = 0 ; i < str.length(); i++) {
            values[i] = convert(str.charAt(i));
        }
        dp = new int[k + 1][n];
        mod = new int[k + 1];
        mod[0] = 1;
        for
        (int i = 1; i <= k; i++) {
            mod[i] = (mod[i - 1] * 2) % n;
        }
        for (int[] d : dp) {
            Arrays.fill(d, INIT);
        }
        out.println(convert(func(k, 0)));
    }

    private int func(int k, int pos) {
        if (k == 0) {
            return values[pos];
        }
        if (dp[k][pos] != INIT) {
            return dp[k][pos];
        }
        int leftWin = func(k - 1, pos);
        int rightWin = func(k - 1, (pos + mod[k - 1]) % n);

        int win = leftWin;
        if (rightWin == (leftWin + 1) % 3) {
            win =  rightWin;
        }
        dp[k][pos] = win;
        return dp[k][pos];
    }

    private int convert(char c) {
        switch (c) {
            case 'R':
                return  2;
            case 'S':
                return 1;
            case 'P':
                return 0;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    private char convert(int c) {
        switch (c) {
            case 2:
                return  'R';
            case 1:
                return 'S';
            case 0:
                return 'P';
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }
}
