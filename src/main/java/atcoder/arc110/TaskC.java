package atcoder.arc110;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskC {
    int n;
    int[] data;
    List<Integer> result = new ArrayList<>();
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt() - 1;
        }
        split(0, n);
//        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(",")));
//        System.out.println(Arrays.toString(data));
        if (result.size() != n - 1) {
            out.println(-1);
        } else {
            for (int i = 0; i < n; i++) {
                if (data[i] != i) {
                    out.println(-1);
                    return;
                }
            }
            for(int v : result) {
                out.println(v + 1);
            }
        }
    }

    private void split(int s, int e) {
        if (s >= e) {
            return;
        }
        int point = -1;
        for (int i = s; i < e - 1; i++) {
            if (data[i] > i && data[i] > data[i + 1]) {
                point = i;
                break;
            }
        }
        if (point == -1) {
            return;
        }
        int tmp = data[point];
        data[point] = data[point + 1];
        data[point + 1] = tmp;
        result.add(point);
//        split(s, point + 1);
        for (int i = point - 1; i >= s; i--){
            result.add(i);
            tmp = data[i];
            data[i] = data[i + 1];
            data[i + 1] = tmp;
        }
        split(point + 1, e);
    }
}
