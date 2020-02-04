//Given an m x n matrix of positive integers representing the height of each uni
//t cell in a 2D elevation map, compute the volume of water it is able to trap aft
//er raining. 
//
// 
//
// Note: 
//
// Both m and n are less than 110. The height of each unit cell is greater than 
//0 and is less than 20,000. 
//
// 
//
// Example: 
//
// 
//Given the following 3x6 height map:
//[
//  [1,4,3,1,3,2],
//  [3,2,1,3,2,4],
//  [2,3,3,2,3,1]
//]
//
//Return 4.
// 
//
// 
//
// The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,
//3,3,2,3,1]] before the rain. 
//
// 
//
// 
//
// After the rain, water is trapped between the blocks. The total volume of wate
//r trapped is 4. 
// Related Topics Heap Breadth-first Search


package leetcode.editor.en;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWaterIi {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWaterIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Tripple {
        int height;
        int row;
        int col;
        Tripple(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }
    class Solution {
        PriorityQueue<Tripple> pq;
        int rows;
        int cols;
        boolean[][] visisted;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        public int trapRainWater(int[][] heightMap) {
            if (heightMap.length == 0) {
                return 0;
            }
            int result = 0;
            rows = heightMap.length;
            cols = heightMap[0].length;
            visisted = new boolean[rows][cols];
            pq = new PriorityQueue<>(rows * cols, Comparator.comparingInt(a -> a.height));
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                        pq.add(new Tripple(heightMap[row][col], row, col));
                        visisted[row][col] = true;
                    }
                }
            }

            while (!pq.isEmpty()) {
                Tripple top = pq.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nextRow = top.row + dx[i];
                    int nextCol = top.col + dy[i];

                    if (nextRow < 0 || nextRow >= rows) {
                        continue;
                    }

                    if (nextCol < 0 || nextCol >= cols) {
                        continue;
                    }

                    if (visisted[nextRow][nextCol]) {
                        continue;
                    }

                    visisted[nextRow][nextCol] = true;
                    result += Math.max(0, top.height - heightMap[nextRow][nextCol]);
                    pq.add(new Tripple(Math.max(heightMap[nextRow][nextCol], top.height), nextRow, nextCol));
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}