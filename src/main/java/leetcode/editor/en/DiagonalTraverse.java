//Given a matrix of M x N elements (M rows, N columns), return all elements of t
//he matrix in diagonal order as shown in the below image. 
//
// 
//
// Example: 
//
// 
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//Output:  [1,2,4,7,5,3,6,8,9]
//
//Explanation:
//
// 
//
// 
//
// Note: 
//
// The total number of elements of the given matrix will not exceed 10,000. 
//


package leetcode.editor.en;

public class DiagonalTraverse {
    public static void main(String[] args) {
        Solution solution = new DiagonalTraverse().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int rows;
        int cols;

        public int[] findDiagonalOrder(int[][] matrix) {
            rows = matrix.length;
            cols = matrix[0].length;
            int currentRow = 0;
            int currentCol = 0;
            int dx = -1;
            int dy = 1;
            int[] result = new int[rows * cols];
            int index = 0;
            while (index < rows * cols) {
                result[index++] = matrix[currentRow][currentCol];

                int nextRow = currentRow + dx;
                int nextCol = currentCol + dy;

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    currentRow = nextRow;
                    currentCol = nextCol;
                } else {
                    nextRow = currentRow + Math.max(0, dx);
                    nextCol = currentCol + Math.max(0, dy);
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                        currentRow = nextRow;
                        currentCol = nextCol;
                    } else {
                        currentRow = currentRow + Math.max(0, -dx);
                        currentCol = currentCol + Math.max(0, -dy);
                    }
                    dx *= -1;
                    dy *= -1;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}