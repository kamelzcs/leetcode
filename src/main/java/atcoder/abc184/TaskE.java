package atcoder.abc184;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TaskE {
    int rows;
    int cols;
    int[][] dp;
    int INF = 1_000_000_000;
    String[] map;
    Map<Character, List<Integer>> pos = new HashMap<>();
    int[][] dxy = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    Set<Character> processed = new HashSet<>();
    int start;
    int end;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        rows = in.nextInt();
        cols = in.nextInt();
        dp = new int[rows + 1][cols + 1];
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }
        map = new String[rows];
        for (int i = 0; i < rows; i++) {
            map[i] = in.next();
            for (int j = 0; j < cols; j++) {
                char c = map[i].charAt(j);
                pos.computeIfAbsent(c, x -> new ArrayList<>()).add(i * cols + j);
                if (c == 'S') {
                    start = i * cols + j;
                }
                if (c == 'G') {
                    end = i * cols + j;
                }
            }
        }
        dp[start / cols][start % cols] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int top = queue.pollFirst();
            int x = top / cols;
            int y = top % cols;
//            System.out.printf("%d %d %d%n", x, y, dp[x][y]);
            if (top == end) {
                out.println(dp[x][y]);
                return;
            }
            char c = map[x].charAt(y);
            if (c >= 'a' && c <= 'z') {
                if (!processed.contains(c)) {
                    for (int p : pos.get(c)) {
                        int nx = p / cols;
                        int ny = p % cols;
                        if (dp[nx][ny] == INF) {
                            dp[nx][ny] = dp[x][y] + 1;
                            int nextPos = nx * cols + ny;
                            queue.addLast(nextPos);
                        }
                    }
                    processed.add(c);
                }
            }

            for (int[] dir : dxy) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                if (nextX <0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                    continue;
                }

                if (map[nextX].charAt(nextY) == '#') {
                    continue;
                }
                if (dp[nextX][nextY] != INF) {
                    continue;
                }

                int nextPos = nextX * cols + nextY;
                dp[nextX][nextY] = dp[x][y] + 1;
                queue.addLast(nextPos);
            }
        }
        out.println(-1);
    }
}
