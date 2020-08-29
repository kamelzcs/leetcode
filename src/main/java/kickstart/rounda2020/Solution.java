package kickstart.rounda2020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    int count;
    TrieNode insert(String str, int index) {
        int insertIndex = str.charAt(index) - 'A';
        if (children[insertIndex] == null) {
            children[insertIndex] = new TrieNode();
        }
        if (index == str.length() - 1) {
            children[insertIndex].count++;
            return children[insertIndex];
        }
        return children[insertIndex].insert(str, index + 1);
    }
}
public class Solution {
    int N, K;
    TrieNode root;
    int result;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        K = sc.nextInt();
        root = new TrieNode();
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            root.insert(str, 0);
        }
        dfs(root, 0);
        pw.println(String.format("%d", result));
    }

    private int dfs(TrieNode root, int level) {
        int left = root.count;
        for (TrieNode child : root.children) {
            if (child == null) {
                continue;
            }
            left += dfs(child, level + 1);
        }
        this.result += left / K * level;
        return left % K;
    }

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
//        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
//                + OUTPUT_FILE_NAME));
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
