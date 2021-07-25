package atcoder.abc182;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        int maxCount = -1;
        int result = -1;
        for (int i = 2; i <= 1000; i++) {
            int count = 0;
            for (int j = 0 ; j< n;j++) {
                if (data[j] % i == 0) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                result = i;
            }
        }
        out.println(result);
    }
}
