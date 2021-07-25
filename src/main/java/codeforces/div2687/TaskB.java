package codeforces.div2687;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskB {
    int[] colors;
    int n;
    int k;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
//        Map<Integer, Integer> map = new HashMap<>();
//        int maxOccur = 0;
//        int maxColor = 0;
        colors = new int[n];
        Set<Integer> colorSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            colors[i] = in.nextInt();
            colorSet.add(colors[i]);
//            int color = colors[i];
//            int prev = map.getOrDefault(color, 0);
//            int nextOccur = prev + 1;
//            map.put(color, nextOccur);
//            if (nextOccur > maxOccur) {
//                maxOccur = nextOccur;
//                maxColor = color;
//            }
        }
//        System.out.println(maxColor);
        int result = n;
        for (int color : colorSet) {
            result = Math.min(result, paint(color));
        }
        out.println(result);
    }
    int paint(int targetColor) {
        int result = 0;
        int lastPos = -1;
        for (int i = 0; i < n; i++) {
            if (i <= lastPos) {
                continue;
            }
            if (colors[i] != targetColor) {
                result++;
                lastPos = i + k - 1;
            }
        }
        return result;
    }
}
