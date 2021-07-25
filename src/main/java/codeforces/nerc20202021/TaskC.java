package codeforces.nerc20202021;

import common.InputReader;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class TaskC {
    class Data {
        int id;
        int value;

        public Data(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Set<Integer> used = new HashSet<>();
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> {
            if (a.value != b.value) {
                return Integer.compare(b.value, a.value);
            } else {
                return Integer.compare(a.id, b.id);
            }
        });
        int q = in.nextInt();
        int id = 1;
        Deque<Integer> candidates = new ArrayDeque<>();
        for (int i = 0; i < q; i++) {
            int type = in.nextInt();
            if (type == 1) {
                int value = in.nextInt();
                candidates.add(id);
                pq.add(new Data(id++, value));
            } else if (type == 2) {
                while (!candidates.isEmpty()) {
                    int top = candidates.poll();
                    if (!used.contains(top)) {
                        used.add(top);
                        out.print(top + " ");
                        break;
                    }
                }
            } else {
                while (!pq.isEmpty()) {
                    Data top = pq.poll();
                    if (!used.contains(top.id)) {
                        used.add(top.id);
                        out.print(top.id + " ");
                        break;
                    }
                }
            }
        }
        out.println();
    }
}
