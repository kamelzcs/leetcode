package atcoder.arc108;

import common.InputReader;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskD {
    Map<String, Map<Integer, Set<String>>> map = new HashMap<>();
    public void solve(int testNumber, InputReader in, PrintWriter out) {
//        List<int[]> candidates = new ArrayList<>();
//        int size = 1001;
//        int MOD = 1_000_000_007;
//        int[] allOne = new int[size];
//        Arrays.fill(allOne, 1);
//        candidates.add(allOne);
//
//        int[] power = new int[size];
//        power[2] = power[3] = 1;
//        for (int i = 4; i < size; i++) {
//            power[i] = (power[i - 1] * 2) % MOD;
//        }
//        candidates.add(power);
//
//        int[] fib = new int[size];
//        fib[2] = fib[3] = 1;
//        for (int i = 4; i < size; i++) {
//            fib[i] = (fib[i - 2] + fib[i - 1]) % MOD;
//        }
//        candidates.add(fib);

        for (int j = 0; j < 16; j++) {
            String binaryString = Integer.toBinaryString(j);
            String str = ("0000"+binaryString).substring(binaryString.length());
            out.println(str);
            for (int i = 2; i <= 10; i++) {
                out.println(i + " " + dfs(str, i).size());
            }
        }
//        int n = in.nextInt();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0 ; i < 4; i++) {
//            String p = in.next();
//            stringBuilder.append(p.equals("A") ? '0' : '1');
//        }
//        String str = stringBuilder.toString();
//        int index = 5;
//        if (n <= index) {
//            out.println(dfs(str, n).size());
//        } else {
//            int l = dfs(str, index).size();
//            for (int[] p : candidates) {
//                if (p[index] == l) {
//                    out.println(p[n]);
//                    return;
//                }
//            }
//        }
    }

    private Set<String> dfs(String str, int len) {
        if (len == 2) {
            return Collections.singleton("01");
        }
        Map<Integer, Set<String>> part = map.computeIfAbsent(str, s -> new HashMap<>());
        if (part.containsKey(len)) {
            return part.get(len);
        }
        Set<String> prevResult = dfs(str, len - 1);
        return prevResult.stream().flatMap(s -> {
            Set<String> p = new HashSet<>();
            for (int i = 0; i < s.length() - 1; i++) {
                int pos = 2 * (s.charAt(i) - '0') + (s.charAt(i + 1) - '0');
                p.add(s.substring(0, i + 1) + str.charAt(pos) + s.substring(i + 1));
            }
            return p.stream();
        }).collect(Collectors.toSet());
    }
}
