  //Given a binary tree, flatten it to a linked list in-place. 
//
// For example, given the following tree: 
//
// 
//    1
//   / \
//  2   5
// / \   \
//3   4   6
// 
//
// The flattened tree should look like: 
//
// 
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
// 
// Related Topics Tree Depth-first Search

  
  package leetcode.editor.en;

  import javax.swing.tree.TreeNode;
  import java.util.AbstractMap;
  import java.util.AbstractMap.SimpleEntry;

  public class FlattenBinaryTreeToLinkedList{
      public static void main(String[] args) {
           Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
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
class Solution {
    public void flatten(TreeNode root) {
        flat(root);
    }

    SimpleEntry<TreeNode, TreeNode> flat(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tail = root;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        if (leftNode != null) {
            SimpleEntry<TreeNode, TreeNode> leftPart = flat(leftNode);
            root.right = leftPart.getKey();
            tail = leftPart.getValue();
            root.left = null;
        }
        if (rightNode != null) {
            SimpleEntry<TreeNode, TreeNode> rightPart = flat(rightNode);
            tail.right = rightPart.getKey();
            tail = rightPart.getValue();
        }
        return new SimpleEntry<>(root, tail);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

  }