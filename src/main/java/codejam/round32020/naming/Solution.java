package codejam.round32020.naming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Solution {
    int[][] dp;
    String first;
    String second;
    Map<Integer, String> cache;
    void solve(Scanner sc, PrintWriter pw) {
        dp = new int[62][62];
        cache = new HashMap<>();
        first = "#" + sc.next();
        second = "#" + sc.next();
        for (int[] value : dp) {
            Arrays.fill(value, Integer.MAX_VALUE / 4);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                int firstIndex = i - 1;
                int secondIndex = j - 1;
                if (firstIndex >= 0 && secondIndex >= 0) {
                    if (first.charAt(firstIndex) == second.charAt(secondIndex)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        String result = func(first.length(), second.length(), dp[first.length()][second.length()] / 2);
        pw.println(result.substring(1));
    }

    private String func(int firstLen, int secondLen, int mutate) {
        int cacheIndex = firstLen * 100 + secondLen;
        if (cache.containsKey(cacheIndex)) {
            return cache.get(cacheIndex);
        }
        String result = "";
        if (mutate == 0) {
            result =  second.substring(0, secondLen);
            cache.put(cacheIndex, result);
            return result;
        }
        if (dp[firstLen - 1][secondLen] + 1 == dp[firstLen][secondLen]) {
            result = func(firstLen - 1, secondLen, mutate - 1) + first.charAt(firstLen - 1);
            cache.put(cacheIndex, result);
            return result;
        }
        if (first.charAt(firstLen - 1) == second.charAt(secondLen - 1)) {
            result = func(firstLen - 1, secondLen - 1, mutate) + first.charAt(firstLen - 1);
            cache.put(cacheIndex, result);
            return result;
        }
        if (first.charAt(firstLen - 1) != second.charAt(secondLen - 1) && dp[firstLen - 1][secondLen - 1] + 1 == dp[firstLen][secondLen]) {
            result = func(firstLen - 1, secondLen - 1, mutate - 1) + first.charAt(firstLen - 1);
            cache.put(cacheIndex, result);
            return result;
        }
        result = func(firstLen, secondLen - 1, mutate - 1);
        cache.put(cacheIndex, result);
        return result;
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
