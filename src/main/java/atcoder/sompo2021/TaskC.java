package atcoder.sompo2021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        if (k == 0) {
            out.println(n);
            return;
        }
        long v = n;
        for (int i = 1; i <= k; i++) {
            String str = String.valueOf(v);
            char[] array = str.toCharArray();
            Arrays.sort(array);
            long g1 = Long.parseLong(new String(array));
            for (int j = 0; j < array.length / 2; j++) {
                char tmp = array[j];
                array[j] = array[array.length - 1 - j];
                array[array.length - 1 - j] = tmp;
            }
            long g2 = Long.parseLong(new String(array));
            long f = g2 - g1;
            v = f;
//            System.out.println(g1 + " " + g2 + "" + v);
        }
        out.println(v);
    }
}
