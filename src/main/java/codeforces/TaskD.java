package codeforces;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        TreeMap<Integer, List<Integer>> valueToPos = new TreeMap<>(Comparator.reverseOrder());
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int value = in.nextInt();
            valueToPos.computeIfAbsent(value, k -> new ArrayList<>()).add(i);
        }
        int[] result = new int[N]; // for x, result[x] = max(min(x[i], ... x[i + x - 1]))
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : valueToPos.entrySet()) {
            int value = entry.getKey();
            for (int pos : entry.getValue()) {
                int rightPos = right.getOrDefault(pos + 1, pos);
                int leftPos = left.getOrDefault(pos - 1, pos);
                int len = rightPos - leftPos + 1;
                right.put(leftPos, rightPos);
                left.put(rightPos, leftPos);
                result[len - 1] = Math.max(result[len - 1], value);
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            result[i] = Math.max(result[i], result[i + 1]);
        }
        for (int i = 0; i < N; i++) {
            out.print(result[i]);
            if (i < N - 1) {
                out.print(" ");
            }
        }
        out.println();
    }
}
