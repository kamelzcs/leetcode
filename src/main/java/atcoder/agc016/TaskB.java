package atcoder.agc016;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        boolean allEqual = false;
        if (n == 1) {
            allEqual = true;
        } else {
            allEqual = a[0] == a[n - 1];
        }
        if (allEqual) {
            if (n == a[0] + 1 || n >= 2 * a[0]) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        } else {
            int countT = 0;
            for (int i = 0; i < n; i++) {
                countT += a[i] - a[0];
            }

            int countTMinus = n - countT;
            if (countT + 2 * countTMinus >= 2 * a[n - 1] && a[n - 1] > countTMinus) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
    }
}
