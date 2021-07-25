package codeforces.div2699;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TaskC {
    static int size = 100_001;
    static int[] inPlace = new int[size];
    static Queue<Integer>[] outPlace = new Queue[size];
    static {
        for (int i = 0; i < outPlace.length; i++) {
            outPlace[i] = new ArrayDeque<>();
        }
    }
    static int[] a = new int[size];
    static int[] b = new int[size];
    static int[] c = new int[size];
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int outPlaceCount = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            c[i] = in.nextInt();
        }
        for (int i = 0; i <= n; i++) {
            outPlace[i].clear();
        }
        Arrays.fill(inPlace, 0);
        for (int i = 0; i < n; i++) {
            if (a[i] == b[i]) {
                inPlace[a[i]] = i + 1;
            } else {
                outPlace[b[i]].add(i + 1);
                outPlaceCount++;
            }
        }
        int[] result = new int[m];
        int last = -1;
        boolean valid = true;
        for (int i = m - 1; i >= 0; i--) {
            int v = c[i];
            if (outPlace[v].isEmpty()) {
                if (last > 0) {
                    result[i] = last;
                } else {
                    if (inPlace[v] <= 0) {
                        valid = false;
                        break;
                    } else {
                        last = inPlace[v];
                        result[i] = last;
                    }
                }
            } else {
                int toPlace = outPlace[v].poll();
                outPlaceCount--;
                last = toPlace;
                result[i] = last;
            }
        }
        if (!valid || outPlaceCount > 0) {
            out.println("NO");
        } else {
            out.println("YES");
            String ans = Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            out.println(ans);
        }
    }
}
