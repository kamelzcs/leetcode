//Find the total area covered by two rectilinear rectangles in a 2D plane.
//
// Each rectangle is defined by its bottom left corner and top right corner as s
//hown in the figure. 
//
// 
//
// Example: 
//
// 
//Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
//Output: 45 
//
// Note: 
//
// Assume that the total area is never beyond the maximum possible value of int.
// 
// Related Topics Math


package leetcode.editor.en;

public class RectangleArea {
    public static void main(String[] args) {
        Solution solution = new RectangleArea().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int leftX = Math.max(A, E);
            int rightX = Math.min(C, G);

            int bottomY = Math.max(B, F);
            int topY = Math.min(D, H);

            return area(A, B, C, D) + area(E, F, G, H) - area(leftX, bottomY, rightX, topY);
        }

        private int area(int a, int b, int c, int d) {
            if (c < a || d < b) {
                return 0;
            }
            return (c - a) * (d - b);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}