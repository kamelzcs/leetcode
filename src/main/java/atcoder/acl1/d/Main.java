package atcoder.acl1.d;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

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

            long result = nextLong();
            return Math.toIntExact(result);
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

    class SegmentTree {
        int[] maxValue;
        int max = 300_000;
//        int max = 10;
        SegmentTree() {
            maxValue = new int[4 * max + 10];
        }

        int query(int id, int l, int r, int x, int y) {
            if (x >= r || y <= l) {
                return 0;
            }
            if (x <= l && y >= r) {
//                System.out.println(String.format("query id[%d], l[%d], r[%d] x[%d] y[%d] maxValue[%d]", id, l, r, x, y, maxValue[id]));
                return maxValue[id];
            }
            int mid = (l + r) / 2;
            return Math.max(query(2 * id, l, mid, x, y), query(2 * id + 1, mid, r, x, y));
        }

        int update(int id, int l, int r, int x, int val) {
            if (x >= r || x < l) {
                return maxValue[id];
            }
            if (r == l + 1) {
                assert x == l;
                maxValue[id] = Math.max(val, maxValue[id]);
                return maxValue[id] ;
            }
            int mid = (l + r) / 2;
            maxValue[id] = Math.max(update(2 * id, l, mid, x, val), update(2 * id + 1, mid, r, x, val));
//            System.out.println(String.format("id[%d], l[%d], r[%d] x[%d] maxValue[%d]", id, l, r, x, maxValue[id]));
            return  maxValue[id];
        }
    }
    void solve(Reader sc, PrintWriter pw) throws IOException {
        int result = 1;
        int N = sc.nextInt();
        int K = sc.nextInt();
        SegmentTree segmentTree = new SegmentTree();
        for (int i = 0; i < N; i++) {
            int A = sc.nextInt();
            int maxValue = segmentTree.query(1, 0, segmentTree.max + 1, A - K, A + K + 1);
            segmentTree.update(1, 0, segmentTree.max + 1, A, maxValue + 1);
//            System.out.println(maxValue);
            result = Math.max(maxValue + 1, result);
        }
        pw.println(result);
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


