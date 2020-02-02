//Given an m x n matrix of non-negative integers representing the height of each
// unit cell in a continent, the "Pacific ocean" touches the left and top edges of
// the matrix and the "Atlantic ocean" touches the right and bottom edges. 
//
// Water can only flow in four directions (up, down, left, or right) from a cell
// to another one with height equal or lower. 
//
// Find the list of grid coordinates where water can flow to both the Pacific an
//d Atlantic ocean. 
//
// Note: 
//
// 
// The order of returned grid coordinates does not matter. 
// Both m and n are less than 150. 
// 
//
// 
//
// Example: 
//
// 
//Given the following 5x5 matrix:
//
//  Pacific ~   ~   ~   ~   ~ 
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
//
//Return:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with paren
//theses in above matrix).
// 
//
// 
// Related Topics Depth-first Search Breadth-first Search


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        Solution solution = new PacificAtlanticWaterFlow().new Solution();
        solution.pacificAtlantic(new int[][] {{1, 1}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (! (obj instanceof  Pair)) {
                return false;
            }
            Pair that = (Pair) obj;
            return Objects.equals(row, that.row) && Objects.equals(col, that.col);
        }

        @Override
        public int hashCode() {
            return this.row ^ this.col;
        }
    }
    class Solution {
        int rows;
        int cols;
        int[][] matrix;
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            this.matrix = matrix;
            this.rows = matrix.length;
            this.cols = matrix[0].length;
            Set<Pair> pacific = new HashSet<>();
            Set<Pair> atlantic = new HashSet<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == 0 || j == 0) {
                        pacific.add(new Pair(i, j));
                    }
                    if (i == rows - 1 || j == cols - 1) {
                        atlantic.add(new Pair(i, j));
                    }
                }
            }

            bfs(pacific);
            bfs(atlantic);

            pacific.retainAll(atlantic);

            return pacific.stream().map(p -> Arrays.asList(p.row, p.col)).collect(Collectors.toList());
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        private void bfs(Set<Pair> pacific) {
            Queue<Pair> current = new LinkedList<>(pacific);
            while (!current.isEmpty()) {
                Pair top = current.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nextRow = top.row + dx[i];
                    int nextCol = top.col + dy[i];

                    if (nextRow < 0 || nextRow >= this.rows) {
                        continue;
                    }

                    if (nextCol < 0 || nextCol >= this.cols) {
                        continue;
                    }

                    Pair candidate = new Pair(nextRow, nextCol);

                    if (pacific.contains(candidate)) {
                        continue;
                    }

                    if (matrix[candidate.row][candidate.col] < matrix[top.row][top.col]) {
                        continue;
                    }

                    pacific.add(candidate);
                    current.add(candidate);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}