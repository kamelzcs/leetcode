//Given a 2D board and a list of words from the dictionary, find all words in th
//e board. 
//
// Each word must be constructed from letters of sequentially adjacent cell, whe
//re "adjacent" cells are those horizontally or vertically neighboring. The same l
//etter cell may not be used more than once in a word. 
//
// 
//
// Example: 
//
// 
//Input: 
//board = [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//words = ["oath","pea","eat","rain"]
//
//Output: ["eat","oath"]
// 
//
// 
//
// Note: 
//
// 
// All inputs are consist of lowercase letters a-z. 
// The values of words are distinct. 
// 
// Related Topics Backtracking Trie


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearchIi {
    public static void main(String[] args) {
        Solution solution = new WordSearchIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Node {
        Map<Character, Node> map = new HashMap<>();
        String word;
    }

    class Trie {
        Node root = new Node();

        void insert(String word) {
            Node current = root;
            for (char c : word.toCharArray()) {
                current = current.map.computeIfAbsent(c, x -> new Node());
            }
            current.word = word;
        }
    }

    class Solution {
        char[][] board;
        Trie trie = new Trie();
        boolean[][] visited;
        int rows;
        int cols;
        int[][] dxy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        public List<String> findWords(char[][] board, String[] words) {
            this.board = board;
            this.rows = board.length;
            if (rows == 0) {
                return Collections.emptyList();
            }
            this.cols = board[0].length;
            if (cols == 0) {
                return Collections.emptyList();
            }
            visited = new boolean[rows][cols];
            for (String word : words) {
                trie.insert(word);
            }
            List<String> result = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result.addAll(dfs(trie.root, i, j));
                }
            }
            return result;
        }

        private List<String> dfs(Node current, int row, int col) {
            List<String> result = new ArrayList<>();
            if (current.word != null) {
                result.add(current.word);
                current.word = null;
            }
            if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) {
                return result;
            }
            char c = board[row][col];
            if (!current.map.containsKey(c)) {
                return result;
            }
            visited[row][col] = true;
            for (int[] xy : dxy) {
                result.addAll(dfs(current.map.get(c), row + xy[0], col + xy[1]));
            }
            visited[row][col] = false;
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}