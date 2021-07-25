package codeforces;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class TwoRobots {
    class Data {
        int row0;
        int col0;
        int row1;
        int col1;

        public Data(int row0, int col0, int row1, int col1) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
        }
    }
    int[][][][] dist;
    int rows;
    int cols;
    int EMPTY = Integer.MAX_VALUE / 10;
    int[][] dxy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int move(String[] plan) {
        rows = plan.length;
        cols = plan[0].length();
        dist = new int[rows][cols][rows][cols];
        for (int[][][] part0 : dist) {
            for (int[][] part1 : part0) {
                for (int[] part2 : part1) {
                    Arrays.fill(part2, EMPTY);
                }
            }
        }
        int startARow = 0;
        int startACol= 0;
        int targetARow= 0;
        int targetACol= 0;
        int startBRow= 0;
        int startBCol= 0;
        int targetBRow= 0;
        int targetBCol= 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = plan[i].charAt(j);
                if (c == 'A') {
                    startARow = i;
                    startACol = j;
                } else if (c == 'a') {
                    targetARow = i;
                    targetACol = j;
                } else if (c == 'B') {
                    startBRow = i;
                    startBCol = j;
                } else if (c == 'b') {
                    targetBRow = i;
                    targetBCol = j;
                }
            }
        }
        dist[startARow][startACol][startBRow][startBCol] = 0;
        Deque<Data> deque = new ArrayDeque<Data>();
        deque.addLast(new Data(startARow, startACol, startBRow, startBCol));
        while (!deque.isEmpty()) {
            Data top = deque.pollFirst();
            if (top.row0 == targetARow && top.col0 == targetACol && top.row1 == targetBRow && top.col1 == targetBCol) {
                return dist[targetARow][targetACol][targetBRow][targetBCol];
            }
            for (int[] dir : dxy) {
                int nextRow0 = top.row0 + dir[0];
                int nextCol0 = top.col0 + dir[1];
                for (int[] dir1 : dxy) {
                    int nextRow1 = top.row1 + dir1[0];
                    int nextCol1 = top.col1 + dir1[1];
                    if (nextRow0 == nextRow1 && nextCol0 == nextCol1) {
                        continue;
                    }
                    if (nextRow0 == top.row1 && nextCol0 == top.col1 && nextRow1 == top.row0 && nextCol1 == top.col0) {
                        continue;
                    }
                    if (nextRow0 < 0 || nextRow0 >= rows) {
                        continue;
                    }
                    if (nextRow1 < 0 || nextRow1 >= rows) {
                        continue;
                    }
                    if (nextCol0 < 0 || nextCol0 >= cols) {
                        continue;
                    }
                    if (nextCol1 < 0 || nextCol1 >= cols) {
                        continue;
                    }
                    if (plan[nextRow0].charAt(nextCol0) == '#') {
                        continue;
                    }
                    if (plan[nextRow1].charAt(nextCol1) == '#') {
                        continue;
                    }
                    if (dist[nextRow0][nextCol0][nextRow1][nextCol1] != EMPTY) {
                        continue;
                    }
//                    System.out.println(String.format("%d %d %d %d", nextRow0, nextCol0, nextRow1, nextCol1));
                    dist[nextRow0][nextCol0][nextRow1][nextCol1] = dist[top.row0][top.col0][top.row1][top.col1] + 1;
                    deque.addLast(new Data(nextRow0, nextCol0, nextRow1, nextCol1));
                }
            }

        }
        return -1;
    }
}
