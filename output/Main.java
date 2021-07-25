import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author kamel
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int result = 0;
            Map<Integer, Integer> count = new HashMap<>();
            int[] data = new int[n];
            for (int i = 0; i < k; i++) {
                int v = in.nextInt();
                data[i] = v;
                count.put(v, count.getOrDefault(v, 0) + 1);
            }
            result = count.keySet().size();
            for (int i = k; i < n; i++) {
                int v = in.nextInt();
                data[i] = v;

                int remove = data[i - k];
                int preCount = count.get(remove);
                if (preCount == 1) {
                    count.remove(remove);
                } else {
                    count.put(remove, preCount - 1);
                }
                count.put(v, count.getOrDefault(v, 0) + 1);
                result = Math.max(result, count.keySet().size());
            }
            out.println(result);
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

