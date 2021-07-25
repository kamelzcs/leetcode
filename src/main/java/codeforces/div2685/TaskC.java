package codeforces.div2685;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        String from = in.next();
        String to = in.next();
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            char f = from.charAt(i);
            char t = to.charAt(i);
            map.put(f, map.getOrDefault(f, 0) + 1);
            map.put(t, map.getOrDefault(t, 0) - 1);
        }

        int remain = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            remain += entry.getValue();
            if (remain < 0 || (remain % k) != 0) {
                out.println("No");
                return;
            }
        }
        out.println("Yes");
    }

    private String sort(String next) {
        char[] content = next.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
}
