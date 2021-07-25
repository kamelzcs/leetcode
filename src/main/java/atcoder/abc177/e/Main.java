package atcoder.abc177.e;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    List<Integer> primeFactors(int number) {
        int n = number;
        List<Integer> factors = new ArrayList<>();
        while (n > 1) {
            factors.add(minFactor[n]);
            n /= minFactor[n];
        }
        return factors;
    }

    List<Integer> primes = new ArrayList<>();
    int[] minFactor;
    int N;
    int maxValue = 1_000_000;
    void solve(Reader sc, PrintWriter pw) throws IOException {
        this.N = sc.nextInt();
        minFactor = new int[maxValue + 1];
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }
        sieve();
        int commonGCD = data[0];
        for (int i = 1; i < N; i++) {
            commonGCD = gcd(commonGCD, data[i]);
        }
        if (commonGCD != 1) {
            pw.println("not coprime");
            return;
        }

        Set<Integer> factors = new HashSet<>();
        for (int i = 0; i < N; i++) {
            List<Integer> newFactors = primeFactors(data[i]);
            for (int v : newFactors) {
                if (factors.contains(v)) {
                    pw.println("setwise coprime");
                    return;
                }
            }
            factors.addAll(newFactors);
        }
        pw.println("pairwise coprime");
    }

    private void sieve() {
        for (int i = 2; i <= maxValue; i++) {
            minFactor[i] = i;
        }
        for (int i = 2; i <= maxValue; i++) {
            if (minFactor[i] == i) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && primes.get(j) <= i; j++) {
                int v = (int)Math.min((long)i * primes.get(j) , Integer.MAX_VALUE);
                if (v > maxValue) {
                    break;
                }
                minFactor[v] = primes.get(j);
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


