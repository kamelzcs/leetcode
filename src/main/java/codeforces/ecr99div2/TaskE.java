package codeforces.ecr99div2;

import common.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskE {
    int[] x = new int[4];
    int[] y = new int[4];
    static List<int[]> allPermutation = new ArrayList<>();

    static {
        permute(new int[]{0, 1, 2, 3}, 0);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        for (int i = 0; i < 4; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        long result = Long.MAX_VALUE;
        for (int[] permute : allPermutation) {
            List<Integer> parts = new ArrayList<>();
            int[] small = {x[permute[0]], x[permute[1]]};
            int[] large = {x[permute[2]], x[permute[3]]};
            for (int s : small) {
                for (int l : large) {
                    parts.add(Math.abs(s - l));
                }
            }
            int[] small2 = {y[permute[0]], y[permute[1]]};
            int[] large2 = {y[permute[2]], y[permute[3]]};
            for (int s : small2) {
                for (int l : large2) {
                    parts.add(Math.abs(s - l));
                }
            }
            for (int l : parts) {
                result = Math.min(result, eval(l, permute));
            }
        }
        out.println(result);
    }

    private long eval(long l, int[] permute) {
        return eval(l, x, permute, 0, 2) + eval(l, y, permute, 3, 1);
    }

    private static void permute(int[] arr, int k) {
        if (k == arr.length - 1) {
            allPermutation.add(Arrays.copyOf(arr, arr.length));
        }
        for (int i = k; i < arr.length; i++) {
            int tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
            permute(arr, k + 1);
            tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
        }
    }

    private long eval(long l, int[] value, int[] permute, int small, int large) {
        long result = Long.MAX_VALUE;
        long v = value[permute[large]];
        long left = v - l;
        long sum = 0;
        for (int i = 0; i < 2; i++) {
            sum += Math.abs(value[permute[(i + small) % 4]] - left);
            sum += Math.abs(value[permute[(i + large) % 4]] - v);
        }

        result = sum;

        v = value[permute[small]];
        long right = l + v;
        sum = 0;
        for (int i = 0; i < 2; i++) {
            sum += Math.abs(value[permute[(i + small) % 4]] - v);
            sum += Math.abs(value[permute[(i + large) % 4]] - right);
        }

        result = Math.min(result, sum);
//        System.out.printf("%d %s %d\n", l, Arrays.toString(value), result);
        return result;
    }
}
