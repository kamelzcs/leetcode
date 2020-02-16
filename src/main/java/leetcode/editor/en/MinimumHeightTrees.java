//For an undirected graph with tree characteristics, we can choose any node as t
//he root. The result graph is then a rooted tree. Among all possible rooted trees
//, those with minimum height are called minimum height trees (MHTs). Given such a
// graph, write a function to find all the MHTs and return a list of their root la
//bels. 
//
// Format 
//The graph contains n nodes which are labeled from 0 to n - 1. You will be give
//n the number n and a list of undirected edges (each edge is a pair of labels). 
//
// You can assume that no duplicate edges will appear in edges. Since all edges 
//are undirected, [0, 1] is the same as [1, 0] and thus will not appear together i
//n edges. 
//
// Example 1 : 
//
// 
//Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//        0
//        |
//        1
//       / \
//      2   3 
//
//Output: [1]
// 
//
// Example 2 : 
//
// 
//Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//
//     0  1  2
//      \ | /
//        3
//        |
//        4
//        |
//        5 
//
//Output: [3, 4] 
//
// Note: 
//
// 
// According to the definition of tree on Wikipedia: “a tree is an undirected gr
//aph in which any two vertices are connected by exactly one path. In other words,
// any connected graph without simple cycles is a tree.” 
// The height of a rooted tree is the number of edges on the longest downward pa
//th between the root and a leaf. 
// 
// Related Topics Breadth-first Search Graph


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class MinimumHeightTrees {
    public static void main(String[] args) {
        Solution solution = new MinimumHeightTrees().new Solution();
        System.out.println(solution.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
        System.out.println(solution.findMinHeightTrees(8, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> length = new HashMap<>();
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n == 1) {
                return Arrays.asList(0);
            }
            Set<Integer> used = new HashSet<>();

            Queue<Integer> currentQueue = new LinkedList<>();

            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                map.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
                map.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
            }

            currentQueue.add(0);
            used.add(0);
            int longestStart = -1;
            while (!currentQueue.isEmpty()) {
                int top = currentQueue.poll();
                longestStart = top;
                List<Integer> toAdd = map.get(top).stream().filter(y -> !used.contains(y)).collect(Collectors.toList());
                currentQueue.addAll(toAdd);
                used.addAll(toAdd);
            }

            currentQueue.add(longestStart);
            length.put(longestStart, 0);
            int largestLength = -1;
            while (!currentQueue.isEmpty()) {
                int top = currentQueue.poll();
                for (int next : map.get(top)) {
                    if (!length.containsKey(next)) {
                        currentQueue.add(next);
                        length.put(next, length.get(top) + 1);
                        largestLength = Math.max(largestLength, length.get(next));
                    }
                }
            }

            int finalLargestLength = largestLength;

            Set<Integer> ends = length.entrySet().stream()
                    .filter(x -> x.getValue() == finalLargestLength)
                    .map(x -> x.getKey())
                    .collect(Collectors.toSet());

            List<Integer> middleLength = new ArrayList<>();
            middleLength.add(largestLength / 2);

            if ((largestLength & 1) > 0) {
                middleLength.add(largestLength / 2 + 1);
            }

            return length.entrySet().stream().filter(x -> middleLength.contains(x.getValue()))
                    .map(x -> x.getKey())
                    .filter(x -> reach(x, ends))
                    .collect(Collectors.toList());
        }

        private boolean reach(int start, Set<Integer> ends) {
            if (ends.contains(start)) {
                return true;
            }
            Queue<Integer> currentQueue = new LinkedList<>();
            currentQueue.add(start);

            while (!currentQueue.isEmpty()) {
                int top = currentQueue.poll();
                for (int next : map.get(top)) {
                    if (length.get(next) == length.get(top) + 1) {
                        if (ends.contains(next)) {
                            return true;
                        }
                        currentQueue.add(next);
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}