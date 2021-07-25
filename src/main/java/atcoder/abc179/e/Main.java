package atcoder.abc179.e;

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
        long N = sc.nextLong();
        int X = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[M + 2];
        Arrays.fill(A, -1);
        long[] sum = new long[M + 2];
        A[X] = 1;
        int startIndex = -1;
        int len = -1;
        long circleSum = -1;
        int prevIndex = X;
        sum[1] = X;
        for (int i = 2; ;i++) {
            int next = (int)(((long)prevIndex * (long)prevIndex) % M);
//            System.out.println(next);
            sum[i] = sum[i - 1] + next;
            if (i == N) {
                pw.println(sum[i]);
                return;
            }
            if (A[next] == -1) {
                A[next] = i;
            } else {
                circleSum = sum[i]- sum[A[next]];
                startIndex = A[next];
                len = i - A[next];
                break;
            }
            prevIndex = next;
        }
        long W = (N - startIndex) / len;
        long l2 = N - (startIndex) - W * len;

        long firstPart = sum[startIndex];
        long middlePart = W * circleSum;
        long lastPart = sum[startIndex + (int)l2] - (sum[startIndex]);
        long result = firstPart + middlePart + lastPart;
//        System.out.println(startIndex + " " + len + " " + l2);
//        System.out.println(firstPart + " " + middlePart + " " + lastPart);
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


