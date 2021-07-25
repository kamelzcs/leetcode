package codeforces;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TaskD2 {

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(InputStream inputStream)
        {
            din = new DataInputStream(inputStream);
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
    public void solve(int testNumber, Reader in, PrintWriter out) {
        try {
            realSolve(testNumber, in, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void realSolve(int testNumber, Reader in, PrintWriter out) throws IOException {
        TreeMap<Integer, List<Integer>> valueToPos = new TreeMap<>(Comparator.reverseOrder());
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int value = in.nextInt();
            valueToPos.computeIfAbsent(value, k -> new ArrayList<>()).add(i);
        }
        int[] result = new int[N]; // for x, result[x] = max(min(x[i], ... x[i + x - 1]))
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : valueToPos.entrySet()) {
            int value = entry.getKey();
            for (int pos : entry.getValue()) {
                int rightPos = right.getOrDefault(pos + 1, pos);
                int leftPos = left.getOrDefault(pos - 1, pos);
                int len = rightPos - leftPos + 1;
                right.put(leftPos, rightPos);
                left.put(rightPos, leftPos);
                result[len - 1] = Math.max(result[len - 1], value);
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            result[i] = Math.max(result[i], result[i + 1]);
        }
        for (int i = 0; i < N; i++) {
            out.print(result[i]);
            if (i < N - 1) {
                out.print(" ");
            }
        }
        out.println();
    }
}
