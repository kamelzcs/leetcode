//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted in ascending from left to right. 
// Integers in each column are sorted in ascending from top to bottom. 
// 
//
// Example: 
//
// Consider the following matrix: 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// Given target = 5, return true. 
//
// Given target = 20, return false. 
// Related Topics Binary Search Divide and Conquer


package leetcode.editor.en;

public class SearchA2dMatrixIi {
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrixIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int rows = matrix.length;
            if (rows == 0) {
                return false;
            }

            int cols = matrix[0].length;

            int currentRow = 0;
            int currentCol = cols - 1;

            while (true) {
                if (matrix[currentRow][currentCol] == target) {
                    return true;
                }

                if (matrix[currentRow][currentCol] > target) {
                    currentCol--;
                    if (currentCol < 0) {
                        return false;
                    }
                } else {
                    currentRow++;
                    if (currentRow >= rows) {
                        return false;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}