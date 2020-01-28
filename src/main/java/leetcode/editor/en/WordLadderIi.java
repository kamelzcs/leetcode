//Given two words (beginWord and endWord), and a dictionary's word list, find al
//l shortest transformation sequence(s) from beginWord to endWord, such that: 
//
// 
// Only one letter can be changed at a time 
// Each transformed word must exist in the word list. Note that beginWord is not
// a transformed word. 
// 
//
// Note: 
//
// 
// Return an empty list if there is no such transformation sequence. 
// All words have the same length. 
// All words contain only lowercase alphabetic characters. 
// You may assume no duplicates in the word list. 
// You may assume beginWord and endWord are non-empty and are not the same. 
// 
//
// Example 1: 
//
// 
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// Example 2: 
//
// 
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: []
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
// 
//
// 
// 
// Related Topics Array String Backtracking Breadth-first Search


package leetcode.editor.en;

import java.io.StringWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class WordLadderIi {
    public static void main(String[] args) {
        Solution solution = new WordLadderIi().new Solution();
        solution.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<String, Integer> distance = new HashMap<>();
        Set<String> dict;
        Map<String, Set<String>> cachedNeighbors = new HashMap<>();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            dict = new HashSet<>(wordList);
            bfs(beginWord, endWord);
            return dfs(beginWord, endWord);
        }

        private List<List<String>> dfs(String beginWord, String endWord) {
            if (beginWord.endsWith(endWord)) {
                List<List<String>> result = new ArrayList<>();
                result.add(new LinkedList<>(Arrays.asList(endWord)));
                return result;
            }

            Set<String> neighbors = getNeighbors(beginWord);
            List<List<String>> result = new ArrayList<>();
            for (String neighbor : neighbors) {
                if (distance.getOrDefault(neighbor, Integer.MIN_VALUE) == distance.get(beginWord) + 1) {
                    List<List<String>> part = dfs(neighbor, endWord);
                    part.forEach(l -> {
                        l.add(0, beginWord);
                        result.add(l);
                    });
                }
            }

            return result;
        }

        private void bfs(String beginWord, String endWord) {
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            distance.put(beginWord, 0);
            while (!queue.isEmpty()) {
                String top = queue.poll();
                if (endWord.equals(top)) {
                    return;
                }

                Set<String> neightbors = getNeighbors(top);
                for (String neighbor : neightbors) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, distance.get(top) + 1);
                        queue.add(neighbor);
                    }
                }
            }
        }

        private Set<String> getNeighbors(String beginWord) {
            return cachedNeighbors.computeIfAbsent(beginWord, w -> getNeighborsUncached(beginWord));
        }

        private Set<String> getNeighborsUncached(String beginWord) {
            Set<String> neightbors = new HashSet<>();
            char[] chars = beginWord.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                for (int i = 0; i < chars.length; i++) {
                    if (c == chars[i]) {
                        continue;
                    }

                    chars[i] = c;
                    String candidate = new String(chars);
                    if (dict.contains(candidate)) {
                        neightbors.add(candidate);
                    }
                    chars[i] = beginWord.charAt(i);
                }
            }
            return neightbors;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
