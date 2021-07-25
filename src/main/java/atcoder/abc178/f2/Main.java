package atcoder.abc178.f2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
            byte[] buf = new byte[1_000]; // line length
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

    class Pair {
        int index;
        int group;
        int value;

        public Pair(int index, int group, int value) {
            this.index = index;
            this.group = group;
            this.value = value;
        }
    }

    Map<Integer, Integer> map = new HashMap<>();

    void solve(Reader sc, PrintWriter pw) throws IOException {
        int N = sc.nextInt();
        int[] result = new int[N];
        int[][] data = new int[2][N];
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data[0][i] = sc.nextInt();
            map.put(data[0][i], map.getOrDefault(data[0][i], 0) + 1);
            list.add(new Pair(i, 0, data[0][i]));
        }

        for (int i = 0; i < N; i++) {
            data[1][i] = sc.nextInt();
            map.put(data[1][i], map.getOrDefault(data[1][i], 0) + 1);
            list.add(new Pair(i, 1, data[1][i]));
        }


        if (map.values().stream().max(Comparator.comparingInt(x -> x)).get() > N) {
            pw.println("No");
            return;
        }

        list.sort(Comparator.comparingInt((Pair x) -> x.value));
        List<Integer>[] allSame = new List[2];
        for (int i = 0; i < allSame.length; i++) {
            allSame[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int pairIndex = (i + N);
            Pair firstPair = list.get(i);
            Pair secondPair = list.get(pairIndex);
            if (firstPair.group != secondPair.group) {
                if (secondPair.group == 0) {
                    Pair tmp = firstPair;
                    firstPair = secondPair;
                    secondPair = tmp;
                }
                result[firstPair.index] = secondPair.value;
            } else {
                allSame[firstPair.group].add(firstPair.index);
                allSame[firstPair.group].add(secondPair.index);
            }
        }

        if (allSame[0].size() != allSame[1].size()) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < allSame[0].size(); i += 2) {
            int secondIndex = allSame[1].get(i);
            int secondPairIndex = allSame[1].get(i + 1);
            result[allSame[0].get(i)] = data[1][secondIndex];
            result[allSame[0].get(i + 1)] = data[1][secondPairIndex];
            if (data[0][allSame[0].get(i)] == data[1][secondIndex] || data[0][allSame[0].get(i + 1)] == data[1][secondPairIndex]) {
                result[allSame[0].get(i)] = data[1][secondPairIndex];
                result[allSame[0].get(i + 1)] = data[1][secondIndex];
            }
        }
        pw.println("Yes");
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                pw.print(" ");
            }
            pw.print(result[i]);
        }
        pw.println();
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


