package atcoder.arc111;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskC {
    class Data {
        int peopleId;
        int baggageId;
        int weight;
        int index;

        public Data(int peopleId, int baggageId, int weight, int index) {
            this.peopleId = peopleId;
            this.baggageId = baggageId;
            this.weight = weight;
            this.index = index;
        }

    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] weight = new int[n];
        int[] p = new int[n];
        int[] idMap = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            weight[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt() - 1;
        }
        int[][] value = new int[n][4];
        for (int i = 0; i < n; i++) {
            int[] data = new int[]{i, p[i], a[i], -1};
            value[i] = data;
        }
        Arrays.sort(value, Comparator.comparingInt(l -> l[2]));
        for (int i = 0; i < n; i++) {
            value[i][3] = i;
        }

        for (int i = 0; i < n; i++) {
            idMap[value[i][1]] = i;
        }

        int result = 0;
        StringBuilder ops = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (value[i][1] == value[i][0]) {
                continue;
            }

            if (value[i][2] <= weight[value[i][1]]) {
                out.println(-1);
                return;
            }

            int targetId = idMap[value[i][0]];
            int[] target = value[targetId];

            ops.append(String.format("%d %d\n", value[i][0] + 1, target[0] + 1));
            result++;
            target[1] = value[i][1];
            idMap[target[1]] = target[3];
        }
        out.println(result);
        out.print(ops.toString());
    }
}
