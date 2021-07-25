package atcoder.abc179.f;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.TreeSet;

class Pair {
    int endPos;
    int pos;

    public Pair(int lenPos, int pos) {
        this.endPos = lenPos;
        this.pos = pos;
    }
}
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
        int N = sc.nextInt();
        int Q = sc.nextInt();
        TreeSet<Pair> cols = new TreeSet<>(Comparator.<Pair>comparingInt(x -> x.endPos).thenComparing(x -> x.pos));
        TreeSet<Pair> rows = new TreeSet<>(Comparator.<Pair>comparingInt(x -> x.endPos).thenComparing(x -> x.pos));
        cols.add(new Pair(N, N));
        rows.add(new Pair(N, N));
        long result = (long)(N - 2) * (N - 2);
        for (int i = 0; i < Q; i++) {
            int type = sc.nextInt();
            int x = sc.nextInt();
            if (type == 1) {
                Pair cross = rows.ceiling(new Pair(x, 0));
                int rowIndex = cross.pos;
//                System.out.println(rowIndex - 2);
                result -= rowIndex - 2;
                cols.add(new Pair(rowIndex - 1, x));
            } else {
                Pair cross = cols.ceiling(new Pair(x, 0));
                int rowIndex = cross.pos;
                result -= rowIndex - 2;
//                System.out.println(rowIndex - 2);
                rows.add(new Pair(rowIndex - 1, x));
            }
        }
        pw.println(result);
    }
    static int f(int x) {
        for (int i = 1; ;i++) {
            if ((i * (i + 1)) % x == 0) {
                return i;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter pw = new PrintWriter(System.out);
//        PrintWriter pw = new PrintWriter(System.out, true);
        for (int i = 1; i <= 100; i++) {
            pw.println(i + " " + f(i));
        }
        pw.flush();
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}


