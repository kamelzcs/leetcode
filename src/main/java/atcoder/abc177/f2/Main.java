package atcoder.abc177.f2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[1_000_010]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
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

        public double nextDouble() throws IOException
        {
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    class Segment {
        int start;
        int length;
        Segment(int start, int length) {
            this.start = start;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Segment segment = (Segment) o;
            return start == segment.start &&
                    length == segment.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, length);
        }
    }
    TreeSet<Segment> segments = new TreeSet<>(
            Comparator.<Segment>comparingInt(s -> s.start).thenComparingInt(s -> s.length)
    );
    PriorityQueue<Segment> pq = new PriorityQueue<>(10000, Comparator.comparingInt(s -> s.length));
    int H, W;
    int INF;
    void solve(Reader sc, PrintWriter pw) throws IOException {
        H = sc.nextInt();
        W = sc.nextInt();
        INF = H * W + 1;
        for (int i = 0; i < H; i++) {
            Segment segment = new Segment(i, 1);
            segments.add(segment);
            pq.add(segment);
        }
        for (int i = 0; i < H; i++) {
            while (!pq.isEmpty() && !segments.contains(pq.peek())) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                Segment top = pq.peek();
                pw.println(i + 1 + top.length - 1);
            } else {
                pw.println(-1);
            }
            int begin = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;
            Segment prev = segments.floor(new Segment(begin - 1, end - begin));
            if (prev != null) {
                int newStart = prev.start;
                int newLen = 0;
                for (Segment segment : new ArrayList<>(segments.tailSet(prev))) {
                    if (segment.start > end) {
                        break;
                    }
                    segments.remove(segment);
                    newLen += segment.length;
                }
                newLen = Math.max(newLen, end - begin + 1 + 1);
                Segment segment = new Segment(newStart, newLen);
                segments.add(segment);
                pq.add(segment);
            }
        }
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


