package atcoder.template.b;

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
    class UnionFind {
        int[] parent;
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        int find(int v) {
            int p = parent[v];
            if (p == v) {
                return p;
            }
            parent[v] = find(p);
            return parent[v];
        }
        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }
            parent[py] = px;
        }
    }
    void solve(Reader sc, PrintWriter pw) throws IOException {
        int N = sc.nextInt();
        int M = sc.nextInt();
        UnionFind unionFind = new UnionFind(N + 1);
        long[] sumA = new long[N + 1];
        long[] sumB = new long[N + 1];
        long[] a = new long[N + 1];
        long[] b = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = sc.nextLong();
        }
        for (int i = 1; i <= N; i++) {
            b[i] = sc.nextLong();
        }
        for (int i = 0 ; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            unionFind.union(from, to);
        }
        for (int i = 1; i <= N; i++) {
            int p = unionFind.find(i);
            sumA[p] += a[i];
            sumB[p] += b[i];
        }
        boolean ok = true;
        for (int i = 1; i <=N ;i++) {
            int p = unionFind.find(i);
            if (sumA[p] != sumB[p]) {
                ok = false;
                break;
            }
        }
        pw.println(ok ? "Yes" : "No");
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


