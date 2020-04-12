  //Given a binary tree root, the task is to return the maximum sum of all keys of
// any sub-tree which is also a Binary Search Tree (BST). 
//
// Assume a BST is defined as follows: 
//
// 
// The left subtree of a node contains only nodes with keys less than the node's
// key. 
// The right subtree of a node contains only nodes with keys greater than the no
//de's key. 
// Both the left and right subtrees must also be binary search trees. 
// 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//Output: 20
//Explanation: Maximum sum in a valid Binary search tree is obtained in root nod
//e with key equal to 3.
// 
//
// Example 2: 
//
// 
//
// 
//Input: root = [4,3,null,1,2]
//Output: 2
//Explanation: Maximum sum in a valid Binary search tree is obtained in a single
// root node with key equal to 2.
// 
//
// Example 3: 
//
// 
//Input: root = [-4,-2,-5]
//Output: 0
//Explanation: All values are negatives. Return an empty BST.
// 
//
// Example 4: 
//
// 
//Input: root = [2,1,3]
//Output: 6
// 
//
// Example 5: 
//
// 
//Input: root = [5,4,8,3,null,6,3]
//Output: 7
// 
//
// 
// Constraints: 
//
// 
// Each tree has at most 40000 nodes.. 
// Each node's value is between [-4 * 10^4 , 4 * 10^4]. 
// Related Topics Dynamic Programming Binary Search Tree

  
  package leetcode.editor.en;

  import java.awt.*;
  import java.util.AbstractMap;

  public class MaximumSumBstInBinaryTree{
      public static void main(String[] args) {
           Solution solution = new MaximumSumBstInBinaryTree().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Data {
    int min;
    int max;
    boolean valid;
    int sum;

    public Data(int min, int max, boolean valid, int sum) {
        this.min = min;
        this.max = max;
        this.valid = valid;
        this.sum = sum;
    }
}
class Solution {
    int result;
    public int maxSumBST(TreeNode root) {
        result = Integer.MIN_VALUE;
        if (root != null) {
            dfs(root);
        }
        return result;
    }

    private Data dfs(TreeNode root) {
        Data data = new Data(root.val, root.val, true, root.val);
        if (root.left != null) {
            Data left = dfs(root.left);
            if (left.valid) {
                if (left.max < root.val) {
                    data.min = left.min;
                    data.sum += left.sum;
                } else {
                    data.valid = false;
                }
            } else {
                data.valid = false;
            }
        }

        if (root.right != null) {
            Data right = dfs(root.right);
            if (data.valid) {
                if (right.valid) {
                    if (right.min > root.val) {
                        data.max = right.max;
                        data.sum += right.sum;
                    } else {
                        data.valid = false;
                    }
                } else {
                    data.valid = false;
                }
            }
        }

        if (data.valid) {
            result = Math.max(result, data.sum);
        }
        return data;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }