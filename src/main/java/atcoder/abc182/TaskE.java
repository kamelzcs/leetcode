package atcoder.abc182;

import common.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskE {
    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    int rows;
    int cols;
    int[][] grid;
    int BULB = 2;
    int BLOCK = 3;
    int LIGHT = 1;
    List<Pair> lights = new ArrayList<>();
    int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        rows = in.nextInt();
        cols = in.nextInt();
        grid = new int[rows][cols];
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            int row = in.nextInt();
            row--;
            int col = in.nextInt();
            col--;
            grid[row][col] = BULB;
            lights.add(new Pair(row, col));
        }
        for (int i = 0; i < m; i++) {
            int row = in.nextInt();
            row--;
            int col = in.nextInt();
            col--;
            grid[row][col] = BLOCK;
        }
        for (Pair pair : lights) {
            dfs(pair.row, pair.col, -1);
        }

        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == BULB || grid[i][j] == LIGHT) {
                    result++;
                }
            }
        }
        out.println(result);
    }

    private void dfs(int row, int col, int lastDir) {
        for (int i = 0; i < dir.length; i++) {
            if (grid[row][col] == LIGHT) {
                if (i != lastDir) {
                    continue;
                }
            }
            int nextRow = row + dir[i][0];
            int nextCol = col + dir[i][1];
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                continue;
            }
            if (grid[nextRow][nextCol] == BLOCK || grid[nextRow][nextCol] == BULB) {
                continue;
            }
            grid[nextRow][nextCol] = LIGHT;
            dfs(nextRow, nextCol, i);
        }
    }
}
