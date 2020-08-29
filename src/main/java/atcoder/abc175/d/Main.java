package atcoder.abc175.d;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
        int INF = Integer.MAX_VALUE / 10;
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] dist = new int[rows][cols];
        for (int[] arr : dist) {
            Arrays.fill(arr, INF);
        }
        int sRow = sc.nextInt();
        sRow--;
        int sCol = sc.nextInt();
        sCol--;
        int eRow = sc.nextInt();
        eRow--;
        int eCol = sc.nextInt();
        eCol--;

        String[] board = new String[rows];
        for (int i = 0; i < rows; i++) {
            board[i] = sc.readLine();
        }

        dist[sRow][sCol] = 0;
        PriorityQueue<Triple> pq = new PriorityQueue<>(rows, Comparator.comparingInt(a -> a.cost));
        pq.add(new Triple(0, sRow, sCol));

        while (!pq.isEmpty()) {
            Triple top = pq.poll();
            if (top.row == eRow && top.col == eCol) {
                break;
            }
            if (top.cost > dist[top.row][top.col]) {
                continue;
            }
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <=2; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    int nextRow = top.row + i;
                    int nextCol = top.col + j;
                    if (nextRow < 0 || nextRow >= rows) {
                        continue;
                    }
                    if (nextCol < 0 || nextCol >= cols) {
                        continue;
                    }

                    if (board[nextRow].charAt(nextCol) == '#') {
                        continue;
                    }

                    int newCost = 0;
                    if (Math.abs(i) + Math.abs(j) > 1) {
                        newCost = 1;
                    }
                    if (top.cost + newCost >= dist[nextRow][nextCol]) {
                        continue;
                    }

                    dist[nextRow][nextCol] = top.cost + newCost;
                    pq.add(new Triple(top.cost + newCost, nextRow, nextCol));
                }
            }
        }

        int result = dist[eRow][eCol] == INF ? -1 : dist[eRow][eCol];
        pw.println(result);
    }
    class Triple {
        int cost;
        int row;
        int col;
        Triple(int cost, int row, int col) {
            this.cost = cost;
            this.row = row;
            this.col = col;
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


