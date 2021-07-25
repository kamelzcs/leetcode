package atcoder.abc178.f;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[1_000_010]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    class Pair implements Comparable<Pair> {
        int key;
        int count;

        Pair(int key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            return Comparator.<Pair>comparingInt(x -> x.count).reversed().thenComparingInt(x -> x.key).compare(this, o);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", count=" + count +
                    '}';
        }
    }

    Map<Integer, Integer> firstMap = new HashMap<>();
    Map<Integer, Integer> secondMap = new HashMap<>();

    void solve(Reader sc, PrintWriter pw) throws IOException {
        int N = sc.nextInt();
        int[] result = new int[N];
        int[] first = new int[N];
        int[] second = new int[N];
        for (int i = 0; i < N; i++) {
            first[i] = sc.nextInt();
            firstMap.put(first[i], firstMap.getOrDefault(first[i], 0) + 1);
        }

        for (int i = 0; i < N; i++) {
            second[i] = sc.nextInt();
            secondMap.put(second[i], secondMap.getOrDefault(second[i], 0) + 1);
        }

        Map<Integer, Integer> merged = Stream.of(firstMap, secondMap)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));

        if (merged.values().stream().max(Comparator.comparingInt(x -> x)).get() > N) {
            pw.println("No");
            return;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.addAll(merged.entrySet().stream().map(entry -> new Pair(entry.getKey(), entry.getValue())).collect(Collectors.toList()));
        pw.println("Yes");
        for (int i = 0; i < N; i++) {
            while (!pq.isEmpty()) {
                Pair top = pq.poll();
                if (merged.get(top.key) < top.count) {
                    continue;
                }
                if (secondMap.get(top.key) == 0) {
                    continue;
                }
                if (first[i] == top.key) {
                    while (!pq.isEmpty()) {
                        Pair nextTop = pq.poll();
                        if (merged.get(nextTop.key) < nextTop.count) {
                            continue;
                        }
                        if (secondMap.get(nextTop.key) == 0) {
                            continue;
                        }
                        pq.add(top);
                        top = nextTop;
                        break;
                    }
                }
//                System.out.println(top);
                result[i] = top.key;
                merged.put(first[i], merged.get(first[i]) - 1);
                merged.put(top.key, merged.get(top.key) - 1);
                pq.add(new Pair(top.key, top.count - 1));
                pq.add(new Pair(first[i], merged.get(first[i])));
                secondMap.put(top.key, secondMap.get(top.key) - 1);
                break;
            }

            if (i > 0) {
                pw.print(" ");
            }
            pw.print(result[i]);
        }
        pw.println();
    }

    private PriorityQueue<Pair> construct(int[] data) {
        PriorityQueue<Pair> result = new PriorityQueue<>();
        int currentIndex = 0;
        while (currentIndex < data.length) {
            int nextIndex = currentIndex + 1;
            while (nextIndex < data.length && data[nextIndex] == data[currentIndex]) {
                nextIndex++;
            }

            result.add(new Pair(currentIndex, nextIndex - currentIndex));
            currentIndex = nextIndex;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter pw = new PrintWriter(System.out);
//        PrintWriter pw = new PrintWriter(System.out, true);
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}


