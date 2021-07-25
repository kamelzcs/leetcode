package atcoder.hhkb.e;

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
    void solve(Reader sc, PrintWriter pw) throws IOException {
        int H = sc.nextInt();
        int W = sc.nextInt();
        long MOD = 1_000_000_007L;
        String[] data = new String[H];
        int K = 0;
        for (int i = 0; i < H; i++) {
            data[i] = sc.readLine();
            for (int j = 0; j < W; j++) {
                K += data[i].charAt(j) == '.' ? 1 : 0;
            }
        }
        long[] numbers = new long[K + 1];
        numbers[0] = 1L;
        for (int i = 1; i <= K; i++) {
            numbers[i] = (numbers[i - 1] << 1) % MOD;
        }
        int[][] fromTop = new int[H + 2][W + 2];
        int[][] fromBottom = new int[H + 2][W + 2];
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (data[i - 1].charAt(j - 1) == '#') {
                    continue;
                }
                fromTop[i][j] = fromTop[i - 1][j] + 1;
            }
        }
        for (int i = H; i >= 1; i--) {
            for (int j = 1; j <= W; j++) {
                if (data[i - 1].charAt(j - 1) == '#') {
                    continue;
                }
                fromBottom[i][j] = fromBottom[i + 1][j] + 1;
            }
        }

        int[][] fromLeft = new int[H + 2][W + 2];
        int[][] fromRight = new int[H + 2][W + 2];
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (data[i - 1].charAt(j - 1) == '#') {
                    continue;
                }
                fromLeft[i][j] = fromLeft[i][j - 1] + 1;
            }
        }
        for (int i = 1; i <= H; i++) {
            for (int j = W; j >= 1; j--) {
                if (data[i - 1].charAt(j - 1) == '#') {
                    continue;
                }
                fromRight[i][j] = fromRight[i][j + 1] + 1;
            }
        }
        long result = (K * numbers[K]) % MOD;
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                int row = i - 1;
                int col = j - 1;
                if (data[row].charAt(col) == '#') {
                    continue;
                }
                int connected = 1 + fromLeft[i][j - 1] + fromRight[i][j + 1] + fromTop[i - 1][j] + fromBottom[i + 1][j];
                int notConnected = K - connected;
//                System.out.println(String.format("%d %d %d", i, j, connected));
                result = (((result - numbers[notConnected]) % MOD) + MOD) % MOD;
            }
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


