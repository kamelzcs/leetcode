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
        Map<String, Set<String>> parent = new HashMap<>();
        Map<SimpleEntry<String, String>, Boolean> valid = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Map<String, Set<String>> neighbors;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            neighbors = buildNeighbors(beginWord, wordList);
            Set<String> currentLayer = new HashSet<>(Arrays.asList(beginWord));
            visited.add(beginWord);
            while (!(currentLayer.isEmpty() || currentLayer.contains(endWord))) {
                Set<String> nextLayer = currentLayer.stream().flatMap(str -> match(str, neighbors.getOrDefault(str, Collections.emptySet())).stream())
                        .collect(Collectors.toSet());
                currentLayer = nextLayer;
                System.out.println(currentLayer);
                visited.addAll(currentLayer);
            }

            if (!parent.containsKey(endWord)) {
                return Collections.emptyList();
            }

            return dfs(endWord, beginWord);
        }

        private Map<String, Set<String>> buildNeighbors(String beginWord, List<String> wordList) {
            Map<String, Set<String>> result = new HashMap<>();
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);
            Queue<String> current = new LinkedList<>();
            current.add(beginWord);
            while (!current.isEmpty()) {
                String top = current.poll();
                for (String word : wordList) {
                    if (valid(top, word)) {
                        result.computeIfAbsent(top, w -> new HashSet<>()).add(word);
                        if (!visited.contains(word)) {
                            visited.add(word);
                            current.add(word);
                        }
                    }
                }
            }
            return result;
        }

        private List<List<String>> dfs(String word, String target) {
            if (Objects.equals(word, target)) {
                List<List<String>> result = new ArrayList<>();
                result.add(new ArrayList<>(Arrays.asList(target)));
                return result;
            }

            List<List<String>> result = new ArrayList<>();

            for (String str : parent.get(word)) {
                List<List<String>> part = dfs(str, target);
                for (List<String> path : part) {
                    path.add(word);
                    result.add(path);
                }
            }
            return result;
        }

        private Set<String> match(String str, Set<String> wordList) {
            Set<String> result = new HashSet<>();
            for (String word : wordList) {
                if (visited.contains(word)) {
                    continue;
                }
                parent.computeIfAbsent(word, w -> new HashSet<>()).add(str);
                result.add(word);
            }
            return result;
        }

        private boolean valid(String str, String word) {
            SimpleEntry<String, String> pair = new SimpleEntry<>(str, word);
            return valid.computeIfAbsent(pair, this::validDiff);
        }

        private boolean validDiff(SimpleEntry<String, String> p) {
            String key = p.getKey();
            String value = p.getValue();
            int count = 0;
            for (int i = 0; i < key.length(); i++) {
                if (key.charAt(i) != value.charAt(i)) {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                }
            }
            return count == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}