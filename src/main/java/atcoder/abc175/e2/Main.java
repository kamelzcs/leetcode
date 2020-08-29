package atcoder.abc175.e2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
            byte[] buf = new byte[1_000_000]; // line length
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

    void solve(Reader sc, PrintWriter pw) throws IOException {
        int R = sc.nextInt();
        int C = sc.nextInt();
        int K = sc.nextInt();
        R++;
        C++;
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int v = sc.nextInt();
            values.put(r * C + c, v);
        }

        long[][][] dp = new long[4][R][C];
//        long INF = Long.MAX_VALUE / 10;
//        for (long[][] arrs : dp) {
//            for (long[] arr : arrs) {
//                Arrays.fill(arr, INF);
//            }
//        }
//        dp[0][0][0] = 0;

        long result = 0;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                for (int rowCnt = 0; rowCnt <= 3; rowCnt++) {
//                    if (dp[rowCnt][row][col] == INF) {
//                        continue;
//                    }
                    //no pickup
                    // go right
                    if (col - 1 >= 0) {
                        dp[rowCnt][row][col] = Math.max(dp[rowCnt][row][col - 1], dp[rowCnt][row][col]);
                    }
                    if (row - 1 >= 0) {
                        if (rowCnt == 0) {
                            for (int prev = 0; prev <= 3; prev++)
                            dp[0][row][col] = Math.max(dp[0][row][col], dp[prev][row - 1][col]);
                        }
                    }

                    // pickup
                    int key = row * C + col;
                    if (values.containsKey(key)) {
                        int value = values.get(key);
                        // go right
                        if (rowCnt >= 1) {
                            if (col - 1 >= 0) {
                                dp[rowCnt][row][col] = Math.max(dp[rowCnt][row][col], dp[rowCnt - 1][row][col - 1] + value);
                            }
                        }
                        if (row - 1 >= 0) {
                            if (rowCnt == 1) {
                                for (int prev = 0; prev <= 3; prev++)
                                    dp[1][row][col] = Math.max(dp[1][row][col], dp[prev][row - 1][col] + value);
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i <= 3; i++) {
            result = Math.max(result, dp[i][R-1][C - 1]);
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


