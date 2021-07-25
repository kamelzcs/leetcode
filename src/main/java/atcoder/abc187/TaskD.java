package atcoder.abc187;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskD {
    class Data {
        long total;
        long a;

        public Data(long total, long a) {
            this.total = total;
            this.a = a;
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long result = 0;
//        PriorityQueue<Data> pq = new PriorityQueue<>((l, r) ->
//            Long.compare(r.total, l.total)
//        );
        PriorityQueue<Long> pq = new PriorityQueue<Long>((l, r) -> Long.compare(r, l));

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            pq.add(2 * a + b);
            result -= a;
        }
        int added = 0;
        while (result <= 0) {
            long top = pq.poll();
            result += top;
            added++;
        }
        out.println(added);
    }
}
