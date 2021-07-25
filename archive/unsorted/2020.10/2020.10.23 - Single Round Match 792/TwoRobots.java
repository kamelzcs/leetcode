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
        int startARow;
        int startACol;
        int targetARow;
        int targetACol;
        int startBRow;
        int startBCol;
        int targetBRow;
        int targetBCol;
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
        return 0;
    }
}
