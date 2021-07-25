package atcoder.acl1.e;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

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
    long MOD = 998_244_353L;
    int N;
    long[] tenSum;
    long[] ten;
    class Node {
        boolean same;
        int val;
        long sum;

        public Node() {
        }
        public Node(boolean same, int val, long sum) {
            this.same = same;
            this.val = val;
            this.sum = sum;
        }
    }
    class SegmentTree {
        Node[] data;
        SegmentTree() {
            data = new Node[4 * N + 10];
//            data[1] = new Node(true, 1, (tenSum[N]) % MOD);
            init(1, 1, N + 1);
        }

        private void init(int id, int l, int r) {
            data[id] = new Node(true, 1,((((tenSum[r - 1] - tenSum[l - 1])) % MOD) + MOD) % MOD);
            if (r > l + 1) {
                int mid = (l + r) / 2;
                init(2 * id, l, mid);
                init(2 * id + 1, mid, r);
            }
        }

        void propagate(int id, int l, int r, int val) {
            data[id].same = true;
            data[id].val = val;
            data[id].sum = ((((tenSum[r - 1] - tenSum[l - 1]) * val) % MOD) + MOD) % MOD;
        }
        public long query(int id, int l, int r, int x, int y) {
            if (x >= r || y <= l) {
                return 0;
            }
            if (x <= l && y >= r) {
                return data[id].sum;
            }
            int mid = (l + r) / 2;
            if (data[id].same) {
                data[id].same = false;
                propagate(2 * id, l, mid, data[id].val);
                propagate(2 * id + 1, mid, r, data[id].val);
            }
            return query(2 * id, l, mid, x, y) + query(2 * id + 1, mid, r, x, y);
        }

        private long update(int id, int l, int r, int x, int y, int val) {
            if (x >= r || y <= l) {
                return data[id].sum;
            }
            if (r == l + 1) {
                data[id].same = true;
                data[id].val = val;
                data[id].sum = (data[id].val * ten[l]) % MOD;
//                System.out.println(String.format("id[%d] l[%d] r[%d] x[%d] y[%d] val[%d] sum[%d]", id, l, r, x, y, val, data[id].sum));
                return data[id].sum;
            }
            int mid = (l + r) / 2;
            if (data[id].same) {
                data[id].same = false;
                propagate(2 * id, l, mid, data[id].val);
                propagate(2 * id + 1, mid, r, data[id].val);
            }
            if (x <= l && y >= r) {
                data[id].same = true;
                data[id].val = val;
                data[id].sum = ((((tenSum[r - 1] - tenSum[l - 1]) * val) % MOD) + MOD) % MOD;
//                System.out.println(String.format("id[%d] l[%d] r[%d] x[%d] y[%d] val[%d] sum[%d]", id, l, r, x, y, val, data[id].sum));
                return data[id].sum;
            }
//            data[id].same = false;
            data[id].sum = (update(2 * id, l, mid, x, y, val) + update(2 * id + 1, mid, r, x, y, val)) % MOD;
//            System.out.println(String.format("id[%d] l[%d] r[%d] x[%d] y[%d] val[%d] sum[%d]", id, l, r, x, y, val, data[id].sum));
            return data[id].sum;
        }
    }
    void solve(Reader sc, PrintWriter pw) throws IOException {
        N = sc.nextInt();
        tenSum = new long[N + 1];
        ten = new long[N + 1];
        ten[N] = 1;
        for (int i = N - 1; i >= 1; i--) {
            ten[i] = (ten[i + 1] * 10) % MOD;
        }
        tenSum[1] = ten[1];
        for (int i = 2; i <= N; i++) {
            tenSum[i] = (tenSum[i - 1] + ten[i]) % MOD;
        }
        SegmentTree segmentTree = new SegmentTree();
        int Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            int L = sc.nextInt();
            int R = sc.nextInt();
            int D = sc.nextInt();
            segmentTree.update(1, 1, N + 1, L, R + 1, D);
            pw.println(segmentTree.query(1, 1, N + 1, 1, N + 1));
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


