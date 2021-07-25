package codeforces.vk2020div2;

import common.InputReader;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskF {
    int[] pos = new int[2_00_001];
    int[] data = new int[2_00_001];
    int[] toUse = new int[2_00_001];
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long MOD = 998244353;
        int n = in.nextInt();
        int k = in.nextInt();
        long result = 1;
        Set<Integer> leftSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
            pos[data[i]] =  i;
        }
        for (int i = 0; i < k; i++) {
            toUse[i] = in.nextInt();
            leftSet.add(toUse[i]);
        }
        for (int i = 0; i < n; i++) {
            if (!leftSet.contains(data[i])) {
                data[i] = 0;
            }
        }

        for (int i = 0; i < k; i++) {
            long v = toUse[i];
            long mul = 0;
            int index = pos[(int)v];
            if (index + 1 < n && data[index + 1] == 0) {
                mul++;
            }
            if (index - 1 >= 0 && data[index - 1] == 0) {
                mul++;
            }
            result = (result * mul) % MOD;
            data[index] = 0;
        }
        out.println(result);
    }
}
