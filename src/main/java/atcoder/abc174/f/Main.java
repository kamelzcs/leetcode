package atcoder.abc174.f;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

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
            byte[] buf = new byte[64]; // line length
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
    class BIT {
        int[] value;
        int n;
        BIT(int n) {
            this.n = n;
            this.value = new int[n + 1];
        }
        void update(int index, int delta) {
            while (index <= n) {
                this.value[index] += delta;
                index += lsb(index);
            }
        }
        int lsb(int index) {
            return index & (-index);
        }
        int sum(int index) {
            int total = 0;
            while (index > 0) {
                total += value[index];
                index -= lsb(index);
            }
            return total;
        }
    }
    void solve(Reader sc, PrintWriter pw) throws IOException {
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int[] result = new int[Q];
        int[] lastVisit = new int[N + 1];
        Arrays.fill(lastVisit, -1);
        int[] c = new int[N + 1];
        List<Integer>[] ql = new List[N + 1];
        List<Integer>[] qid = new List[N + 1];
        for (int i = 0; i < N; i++) {
            c[i + 1] = sc.nextInt();
        }
        for (int i = 0; i < Q; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (ql[r] == null) {
                qid[r] = new ArrayList<>();
                ql[r] = new ArrayList<>();
            }
            qid[r].add(i);
            ql[r].add(l);
        }

        BIT bit = new BIT(N);
        for (int i = 1; i <= N; i++) {
            if (lastVisit[c[i]] != -1) {
                bit.update(lastVisit[c[i]], -1);
            }
            lastVisit[c[i]] = i;
            bit.update(i, 1);

            int currentSum = bit.sum(i);
            if (qid[i] != null) {
                for (int v = 0; v < qid[i].size(); v++) {
                    result[qid[i].get(v)] = currentSum - bit.sum(ql[i].get(v) - 1);
                }
            }
        }
        for (int i = 0; i < Q; i++) {
            pw.println(result[i]);
        }
    }
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter pw = new PrintWriter(System.out);
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}
