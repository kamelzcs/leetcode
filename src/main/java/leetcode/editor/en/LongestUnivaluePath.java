//Given a binary tree, find the length of the longest path where each node in th
//e path has the same value. This path may or may not pass through the root. 
//
// The length of path between two nodes is represented by the number of edges be
//tween them. 
//
// 
//
// Example 1: 
//
// Input: 
//
// 
//              5
//             / \
//            4   5
//           / \   \
//          1   1   5
// 
//
// Output: 2 
//
// 
//
// Example 2: 
//
// Input: 
//
// 
//              1
//             / \
//            4   5
//           / \   \
//          4   4   5
// 
//
// Output: 2 
//
// 
//
// Note: The given binary tree has not more than 10000 nodes. The height of the 
//tree is not more than 1000. 
// Related Topics Tree Recursion


package leetcode.editor.en;

public class LongestUnivaluePath {
    public static void main(String[] args) {
        Solution solution = new LongestUnivaluePath().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int result;
        public int longestUnivaluePath(TreeNode root) {
            dfs(root);
            return result;
        }

        private int dfs(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftCandidate = dfs(root.left);
            int rightCandidate = dfs(root.right);

            int currentLen = 0;
            int singleLen = 0;
            if (root.left != null && root.val == root.left.val) {
                currentLen = currentLen + 1 + leftCandidate;
                singleLen = Math.max(singleLen, leftCandidate + 1);
            }

            if (root.right != null && root.val == root.right.val) {
                currentLen = currentLen + 1 + rightCandidate;
                singleLen = Math.max(singleLen, rightCandidate + 1);
            }

            result = Math.max(result, currentLen);
            return singleLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}