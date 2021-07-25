package atcoder.abc177.f;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

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
    void solve(Reader sc, PrintWriter pw) throws IOException {
        int H, W;
        H = sc.nextInt();
        W = sc.nextInt();
        int INF = H * W + 1;
        int[][] grid = new int[H + 1][W + 1];
        for (int i = 1; i < grid.length; i++) {
            Arrays.fill(grid[i], INF);
        }
        int[] begin = new int[W];
        int[] end = new int[W];
        for (int i = 0; i < H; i++) {
            begin[i] = sc.nextInt() - 1;
            end[i] = sc.nextInt() - 1;
        }
        int[] result = new int[H];
        for (int i = 0; i < H; i++) {
            int rowMin = INF;
            for (int j = 0; j < W; j++) {
                if (i > 0) {
                    rowMin = Math.min(rowMin, grid[i][j]);
                }
                System.out.print(grid[i][j] + " ");
                if (grid[i][j] == INF) {
                    continue;
                }
                grid[i][j + 1] = Math.min(grid[i][j + 1], grid[i][j] + 1);
                if (j < begin[i] || j > end[i]) {
                    grid[i + 1][j] = Math.min(grid[i + 1][j], grid[i][j] + 1);
                }
            }
            System.out.println();
            if (i > 0)
            pw.println( rowMin == INF ? -1 : rowMin);
        }
        pw.println(-1);
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


