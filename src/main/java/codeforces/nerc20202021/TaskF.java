package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TaskF {
    class Data {
        long x;
        long y;

        public Data(long x, long y) {
            long g = gcd(Math.abs(x), Math.abs(y));
            this.x = x / g;
            this.y = y / g;
        }

        long gcd(long x, long y) {
            if (y == 0) {
                return x;
            }
            return gcd(y, x % y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return x == data.x && y == data.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Map<Data, Integer> map = new HashMap<>();
        int n = in.nextInt();
        long result = 0;
        Data[] data = new Data[n];
        for (int i = 0; i < n; i++) {
            long x = in.nextLong();
            long y = in.nextLong();
            long u = in.nextLong();
            long v = in.nextLong();

            long dx = u - x;
            long dy = v - y;

            Data entry = new Data(dx, dy);
            data[i] = entry;
            int prev = map.getOrDefault(entry, 0);
            map.put(entry, prev + 1);
        }

        for (Data entry : data) {
            Data key = new Data(-1L * entry.x, -1L * entry.y);
            result += map.getOrDefault(key, 0);
        }
        out.println(result / 2);
    }
}
