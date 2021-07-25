package codeforces.div2687;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
    class Node {
        int n;
        int p;
        int k;
        int[] data;

        public Node(int n, int p, int k, int[] data) {
            this.n = n;
            this.p = p;
            this.k = k;
            this.data = data;
        }
    }
    long x;
    long y;
    long INF = Long.MAX_VALUE / 10;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
//        int t = in.nextInt();
//        List<Node> cases = new ArrayList<>();
//        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int p = in.nextInt();
            int k = in.nextInt();
            String str = in.next();
//            cases.add(new Node(n, p, k, convert(str)));
            int[] data = convert(str);
//        }
        x = in.nextLong();
        y = in.nextLong();
        long[] dp = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int next = i + k;
            long nextCost = 0;
            if (next < n) {
                nextCost = dp[next];
            }
            if (data[i] == 0) {
                nextCost += x;
            }
            dp[i] = nextCost;
        }
        long result = INF;
//        System.out.println(Arrays.toString(dp));
        for (int i = p - 1; i < n; i++) {
            if (p - 1 + n - i < p) {
                break;
            }
            result = Math.min(result, dp[i] + (i - p + 1) * y);
        }
        out.println(result);
//        for (Node node : cases) {
//            out.println(func(node));
//        }
    }

    private long func(Node node) {
        long[] dp = new long[node.n];
        for (int i = node.n - 1; i >= 0; i--) {
            int next = i + node.k - 1;
            long nextCost = 0;
            if (next < node.n) {
                nextCost = dp[next];
            }
            if (node.data[i] == 0) {
                nextCost += x;
            }
            dp[i] = nextCost;
        }
        long result = INF;
        for (int i = 0; i <= node.n - node.p; i++) {
            result = Math.min(result, dp[i] + (i * y));
        }
        return result;
    }

    private int[] convert(String str) {
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = str.charAt(i) - '0';
        }
        return result;
    }
}
