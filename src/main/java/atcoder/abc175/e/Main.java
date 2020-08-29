package atcoder.abc175.e;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    int H, W;
    int total;
    List<Integer>[] next;
    boolean[] visited;
    int result;
    Set<Long> pos = new HashSet<>();
    void solve(Reader sc, PrintWriter pw) throws IOException {
        H = sc.nextInt();
        W = sc.nextInt();
        total = H + W;
        next = new List[total + 10];
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            int colIndex = H + col;
            pos.add((long)(row - 1) * W + col - 1);
            if (next[row] == null) {
                next[row] = new ArrayList<>();
            }
            if (next[colIndex] == null) {
                next[colIndex] = new ArrayList<>();
            }

            next[row].add(colIndex);
            next[colIndex].add(row);
        }
        int largestRowCount = 0;
        List<Integer> rowCandidates = new ArrayList<>();
        int largestColCount = 0;
        List<Integer> colCandidates = new ArrayList<>();
        for (int i = 1; i <= H; i++) {
            if (next[i] == null) {
                continue;
            }
            if (next[i].size() > largestRowCount) {
                largestRowCount = next[i].size();
                rowCandidates = new ArrayList<>();
                rowCandidates.add(i);
            } else if (next[i].size() == largestRowCount) {
                rowCandidates.add(i);
            }
        }

        for (int i = H + 1; i <= H + W; i++) {
            if (next[i] == null) {
                continue;
            }
            if (next[i].size() > largestColCount) {
                largestColCount = next[i].size();
                colCandidates = new ArrayList<>();
                colCandidates.add(i - H);
            } else if (next[i].size() == largestColCount) {
                colCandidates.add(i - H);
            }
        }

        int result = largestColCount + largestRowCount - 1;
//        System.out.println(String.format("%d %s %d %s", largestRowCount, rowCandidates, largestColCount, colCandidates));
//        System.out.println(pos);
        boolean canAdd = false;
        for (int row : rowCandidates) {
            if (canAdd) {
                break;
            }
            for (int col : colCandidates) {
                long index = (long)(row - 1) * W + col - 1;
                if (!pos.contains(index)) {
//                    System.out.println(row + " " + col);
                    result++;
                    canAdd = true;
                    break;
                }
            }
        }
        pw.println(result);
    }

    private void dfs(int index) {
        visited[index] = true;
        if (next[index] == null) {
            return;
        }
        for (int neighbor : next[index]) {
            if (visited[neighbor]) {
                continue;
            }
            System.out.println(index + " " + neighbor);
            result++;
            dfs(neighbor);
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


