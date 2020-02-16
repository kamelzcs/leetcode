  //Given a binary tree, return all duplicate subtrees. For each kind of duplicate
// subtrees, you only need to return the root node of any one of them. 
//
// Two trees are duplicate if they have the same structure with same node values
//. 
//
// Example 1: 
//
// 
//        1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
// 
//
// The following are two duplicate subtrees: 
//
// 
//      2
//     /
//    4
// 
//
// and 
//
// 
//    4
// 
//Therefore, you need to return above trees' root in the form of a list. Related
// Topics Tree

  
  package leetcode.editor.en;

  import java.util.ArrayList;
  import java.util.HashMap;
  import java.util.List;
  import java.util.Map;
  import java.util.stream.Collectors;

  public class FindDuplicateSubtrees{
      public static void main(String[] args) {
           Solution solution = new FindDuplicateSubtrees().new Solution();
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
    Map<String, List<TreeNode>> subtreeMap = new HashMap<>();
    String separator = "#";
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return subtreeMap.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .map(x -> x.getValue().get(0))
                .collect(Collectors.toList());
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return separator;
        }

        String left = dfs(root.left);
        String right = dfs(root.right);
        String currentStr = left + separator + right;
        subtreeMap.computeIfAbsent(currentStr, x -> new ArrayList<>()).add(root);
        return currentStr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }