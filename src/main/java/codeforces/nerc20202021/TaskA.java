package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class TaskA {
    static class Data {
        int value;
        int index;

        public Data(int value, int index) {
            this.value = value;
            this.index = index;
        }

        static Data SMALL = new Data(0, -1);

        static Data max(Data left, Data right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }

            if (left.value != right.value) {
                return left.value > right.value ? left : right;
            } else {
                return left.index < right.index ? left : right;
            }
        }
    }
    class BIT {
        int n;
        Data[] data;
        BIT(int n) {
            this.n = n;
            this.data = new Data[n];
        }

        Data query(int right) {
            Data result = Data.SMALL;
            while (right > 0) {
                Data left = data[right];
                result = Data.max(left, result);
                right -= lowBit(right);
            }
            return result;
        }

        void update(int index, Data v) {
            while (index < n) {
                data[index] = Data.max(data[index], v);
                index += lowBit(index);
            }
        }

        int lowBit(int x) {
            return x & (-x);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int INIT = -1;
        int n = in.nextInt();
        int[] values = new int[n];
        for(int i = 0; i < n; i++) {
            values[i] = in.nextInt();
        }

        Deque<Integer> dq = new ArrayDeque<>();
        BIT bit = new BIT(n + 1);
        for (int i = 0; i < n; i++) {
            Data last = bit.query(values[i]);

            while (!dq.isEmpty() && values[dq.peekLast()] <= values[i]) {
                dq.pollLast();
            }
            int lastLarger = dq.isEmpty() ? INIT : dq.peekLast();
            dq.addLast(i);

            int delta = 0;

            if (lastLarger > last.index) {
                delta = 1;
            }
            int candidate = last.value + 1 + delta;
            bit.update(values[i], new Data(candidate, i));
        }
        out.println(bit.query(n).value);
    }
}
