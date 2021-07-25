package atcoder.abc184;

import common.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        TreeSet<Long>[] sets = new TreeSet[2];
        sets[0] = new TreeSet<>();
        sets[1] = new TreeSet<>();
        int n = in.nextInt();
        long t = in.nextLong();
        long[] data = new long[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLong();
        }

        int leftNum = n / 2;
        sets[0].add(0L);
        for (int i = 0; i < leftNum; i++) {
            final int j = i;
            sets[0].addAll(sets[0].stream().map(x -> data[j] + x).filter(x -> x <= t).collect(Collectors.toList()));
        }

        sets[1].add(0L);
        for (int i = 0; i < (n - leftNum); i++) {
            final int j = i;
            sets[1].addAll(sets[1].stream().map(x -> data[j + leftNum] + x).filter(x -> x <= t).collect(Collectors.toList()));
        }

        long result = 0;
        for (long num : sets[0]) {
            long upper = t - num;
            if (upper < 0) {
                break;
            }
            Long candidate = sets[1].floor(upper);
            if (candidate != null) {
                result = Math.max(result, num + candidate);
            } else {
                break;
            }
        }
        out.println(result);
    }
}
