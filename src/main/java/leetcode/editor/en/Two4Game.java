//
//You have 4 cards each containing a number from 1 to 9. You need to judge wheth
//er they could operated through *, /, +, -, (, ) to get the value of 24.
// 
//
// Example 1: 
// 
//Input: [4, 1, 8, 7]
//Output: True
//Explanation: (8-4) * (7-1) = 24
// 
// 
//
// Example 2: 
// 
//Input: [1, 2, 1, 2]
//Output: False
// 
// 
//
// Note: 
// 
// The division operator / represents real division, not integer division. For e
//xample, 4 / (1 - 2/3) = 12. 
// Every operation done is between two numbers. In particular, we cannot use - a
//s a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 -
// 1 - 1 - 1 is not allowed. 
// You cannot concatenate numbers together. For example, if the input is [1, 2, 
//1, 2], we cannot write this as 12 + 12. 
// 
// 
// Related Topics Depth-first Search


package leetcode.editor.en;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Two4Game {
    public static void main(String[] args) {
        Solution solution = new Two4Game().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] nums;

        public boolean judgePoint24(int[] nums) {
            this.nums = nums;
            return dfs(0, 4).contains(24L);
        }

        private Set<Long> dfs(int start, int end) {
            if (end - start == 1) {
                return new HashSet<>(Arrays.asList((long) nums[start]));
            }

            for (int i = start + 1; i < end; i++) {
            }
            return null;
        }

        Map<Integer, Integer> cache = new HashMap();
        int[] nums1;
        int[] nums2;

        public int maxDotProduct(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;

            int candidate = Integer.MIN_VALUE;
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    candidate = Math.max(candidate, nums1[i] * nums2[j]);
                }
            }

            if (candidate < 0) {
                return candidate;
            }
            return dp(nums1.length - 1, nums2.length - 1);
        }

        private int dp(int lIndex, int rIndex) {
            int key = 1001 * lIndex + rIndex;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            if (lIndex < 0 || rIndex < 0) {
                return 0;
            }

            int result = nums1[0] * nums2[0];
            result = Math.max(result, dp(lIndex, rIndex - 1));
            result = Math.max(result, dp(lIndex - 1, rIndex - 1) + nums1[lIndex] * nums2[rIndex]);
            result = Math.max(result, dp(lIndex - 1, rIndex));

            cache.put(key, result);
            return result;
        }

        int result;

        public int pseudoPalindromicPaths(TreeNode root) {
            search(root, new int[9]);
            return result;
        }


        private void search(TreeNode root, int[] map) {
            if (root == null) {
                return;
            }
            map[root.val - 1]++;
            if (root.left == null && root.right == null) {
                result += Arrays.stream(map).filter(x -> x % 2 == 1).count() > 1 ? 0 : 1;
                map[root.val - 1]--;
                return;
            }
            search(root.left, map);
            search(root.right, map);
            map[root.val - 1]--;
        }

        public int maxVowels(String s, int k) {
            Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

            int count = 0;
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (vowel.contains(c)) {
                    count++;
                }
                if (i >= k) {
                    char toRemove = s.charAt(i - k);
                    if (vowel.contains(toRemove)) {
                        count--;
                    }
                }
                result = Math.max(result, count);
            }
            return result;
        }

        public int isPrefixOfWord(String sentence, String searchWord) {
            String[] parts = sentence.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].startsWith(searchWord)) {
                    return i + 1;
                }
            }
            return -1;
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int indexA = 0;
        int indexB = 0;
        while (indexA < A.length && indexB < B.length) {
            int[] eventA = A[indexA];
            int[] eventB = B[indexB];
            if (eventA[0] <= eventB[1] && eventB[0] <= eventA[1]) {
                result.add(new int[]{Math.max(eventA[0], eventB[0]), Math.min(eventA[1], eventB[1])});
            }
            if (eventA[1] != eventB[1]) {
                if (eventA[1] < eventB[1]) {
                    indexA++;
                } else {
                    indexB++;
                }
            } else {
                indexA++;
                indexB++;
            }
        }
        int[][] toResult = new int[result.size()][2];
        return result.toArray(toResult);
    }

    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> from = new HashMap<>();
        Map<Integer, List<Integer>> to = new HashMap<>();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int[] connection : connections) {
            from.computeIfAbsent(connection[0], x -> new ArrayList<>()).add(connection[1]);
            to.computeIfAbsent(connection[1], x -> new ArrayList<>()).add(connection[0]);
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        queue.add(0);
        int result = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            for (int reverse : from.getOrDefault(top, Collections.emptyList())) {
                if (!visited.contains(reverse)) {
                    visited.add(reverse);
                    result++;
                    queue.add(reverse);
                }
            }
            for (int toNeighbor : to.getOrDefault(top, Collections.emptyList())) {
                if (!visited.contains(toNeighbor)) {
                    visited.add(toNeighbor);
                    queue.add(toNeighbor);
                }
            }
        }
        return result;
    }

    int sum;
    Map<Integer, Double> dp;
    int[] balls;

    public double getProbability(int[] balls) {
        dp = new HashMap<>();
        sum = Arrays.stream(balls).sum();
        this.balls = balls;
        return func(Arrays.copyOf(balls, balls.length));
    }

    private double func(int[] asList) {
        int state = getState(asList);
        if (dp.containsKey(state)) {
            return dp.get(state);
        }

        double result = 0.0;
        int left = Arrays.stream(asList).sum();
        int nonZero = (int) Arrays.stream(asList)
                .filter(x -> x > 0).count();
        int used = 0;
        for (int i = 0; i < balls.length; i++) {
            if (balls[i] != asList[i]) {
                used++;
            }
        }
        if (left * 2 == sum) {
            if (used == nonZero) {
                result = 1.0;
            }
            dp.put(state, result);
            return result;
        }
        for (int index = 0; index < asList.length; index++) {
            if (asList[index] == 0) {
                continue;
            }
            asList[index]--;
            result += 1.0 / left * func(asList);
            asList[index]++;
        }
        dp.put(state, result);
        return result;
    }

    private int getState(int[] asList) {
        return Arrays.stream(asList).reduce(0, (x, y) -> 7 * x + y);
    }

    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> lastOccurPos = new HashMap<>();
        Map<Integer, Integer> nextPos = new HashMap<>();
        Set<Integer> occurs = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = rains.length - 1; i >= 0; i--) {
            if (lastOccurPos.containsKey(rains[i])) {
                nextPos.put(i, lastOccurPos.get(rains[i]));
            }
            lastOccurPos.put(rains[i], i);
        }
        int[] result = new int[rains.length];
        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain == 0) {
                if (pq.isEmpty()) {
                    result[i] = 1;
                } else {
                    int top = pq.poll();
                    result[i] = rains[top];
                    occurs.remove(rains[top]);
                }
            } else {
                if (occurs.contains(rains[i])) {
                    return new int[0];
                }
                if (nextPos.containsKey(rains[i])) {
                    pq.add(nextPos.get(rains[i]));
                }
                result[i] = -1;
            }
        }
        return result;
    }

    int[] count = new int[11];
    int[] newCount = new int[11];
    TreeMap<Integer, Integer>[] maps = new TreeMap[10];

    public String minInteger(String num, int k) {
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new TreeMap<>();
        }
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            int cId = c - '0';
            int larger = (i) - query(count, cId);
            update(count, cId, 1);
            maps[cId].put(larger, maps[cId].getOrDefault(larger, 0) + 1);
        }

        int remainOps = k;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            for (int id = 0; id < maps.length; id++) {
                if (maps[id].isEmpty()) {
                    continue;
                }
                int larger = i - query(newCount, id);
                Map.Entry<Integer, Integer> firstEntry = maps[id].firstEntry();
                if (larger + remainOps >= firstEntry.getKey()) {
                    result.append(id);
                    update(newCount, id, 1);
                    remainOps -= (firstEntry.getKey() - larger);
                    if (firstEntry.getValue() == 1) {
                        maps[id].pollFirstEntry();
                    } else {
                        maps[id].put(firstEntry.getKey(), firstEntry.getValue() - 1);
                    }
                    break;
                }
            }
        }
        return result.toString();
    }

    private void update(int[] count, int cId, int delta) {
        int id = cId + 1;
        while (id <= 10) {
            count[id] += delta;
            id += (id & (-id));
        }
    }

    private int query(int[] count, int index) {
        int id = index + 1;
        int sum = 0;
        while (id > 0) {
            sum += count[id];
            id -= (id & (-id));
        }
        return sum;
    }

    public int numSub(String s) {
        long MOD = 1_000_000_007;
        long result = 0;
        for (int i = 0; i < s.length(); ){
            if (s.charAt(i) == '0') {
                continue;
            }
            int next = i + 1;
            while (next < s.length() && s.charAt(next) == '1') {
                next++;
            }
            long len = next - i;
            result = (result + ((len + 1) * len / 2) % MOD) % MOD;
            i = next;
        }
        return (int) result;
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<SimpleEntry<Integer, Double>>> neighbors = new HashMap<>();
        double[] successRate = new double[n + 1];
        double negative = -1.0f;
        Arrays.fill(successRate, negative);
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0];
            int to = edge[1];
            double prob = succProb[i];
            neighbors.computeIfAbsent(from, x -> new ArrayList<>()).add(new SimpleEntry<>(to, prob));
            neighbors.computeIfAbsent(to, x -> new ArrayList<>()).add(new SimpleEntry<>(from, prob));
        }
        successRate[start] = 1.0;
        PriorityQueue<SimpleEntry<Integer, Double>> pq = new PriorityQueue<>(n, Comparator.<SimpleEntry<Integer, Double>>comparingDouble(x -> x.getValue()).reversed());
        pq.add(new SimpleEntry<>(start, 1.0));

        while (!pq.isEmpty()) {
            SimpleEntry<Integer, Double> top = pq.poll();
            int nodeIndex = top.getKey();
            if (nodeIndex == end) {
                return top.getValue();
            }
            for (SimpleEntry<Integer, Double> neighbor : neighbors.getOrDefault(nodeIndex, Collections.emptyList())) {
                int neighborIndex = neighbor.getKey();
                double nextProb = top.getValue() * neighbor.getValue();
                if (nextProb < successRate[neighborIndex]) {
                    continue;
                }
                successRate[neighborIndex] = nextProb;
                pq.add(new SimpleEntry<>(neighborIndex, nextProb));
            }
        }
        return 0.0f;
    }

    public double getMinDistSum(int[][] positions) {
        double result = Integer.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            result = Math.min(result, getMinDistSum2(positions));
        }
        return result;
    }

    public double getMinDistSum2(int[][] positions) {
        int maxX = Arrays.stream(positions).max(Comparator.comparingInt(x -> x[0])).get()[0];
        int minX = Arrays.stream(positions).min(Comparator.comparingInt(x -> x[0])).get()[0];
        int maxY = Arrays.stream(positions).max(Comparator.comparingInt(x -> x[1])).get()[1];
        int minY = Arrays.stream(positions).min(Comparator.comparingInt(x -> x[1])).get()[1];
        int random = 1 + new Random().nextInt(100);
        double oldX = 0;
        double oldY = 0;
        double newX = 1.0 * (minX + maxX) / random;
        double newY = 1.0 * (minY + maxY) / random;

        int iteration = 0;
        while (iteration < 1000 && distance(oldX, oldY, newX, newY) > 1e-5){
            double sumX = 0.0;
            double sumY = 0.0;
            double denom = 0;
            for (int[] position : positions) {
                double dist = distance(position[0], position[1], newX, newY);
                sumX += position[0] / dist;
                sumY += position[1] / dist;

                denom += 1.0 / dist;
            }
            oldX = newX;
            oldY = newY;
            newX = sumX / denom;
            newY = sumY / denom;
            iteration++;
        }

        double result = 0.0;
        for (int[] position : positions) {
            result += distance(newX, newY, position[0], position[1]);
        }
        return result;

    }

    private double distance(double oldX, double oldY, double newX, double newY) {
        double distanceX = newX - oldX;
        double distanceY = newY - oldY;
        return Math.max(Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)), 1e-8);
    }

    public List<String> maxNumOfSubstrings(String s) {
        Map<Character, Integer> first = new HashMap<>();
        Map<Character, Integer> last = new HashMap<>();
        Map<Character, TreeSet<Integer>> pos = new HashMap<>();
        Set<Character> used = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!first.containsKey(c)) {
                first.put(c, i);
            }
            pos.computeIfAbsent(c, x -> new TreeSet<>()).add(i);
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!last.containsKey(c)) {
                last.put(c, i);
            }
        }

        List<String> result = new ArrayList<>();
        Set<Character> current = new HashSet<>(first.keySet());
        current.addAll(last.keySet());

        int start = 0;
        while (start < s.length()) {
            boolean found = false;
            int bestEnd = s.length() - 1;
            int bestStart = start;
            for (char startCandidate : current) {
                if (used.contains(startCandidate)) {
                    continue;
                }
                for (char endCandidate : current) {
                    if (used.contains(endCandidate)) {
                        continue;
                    }
                    boolean valid = true;
                    int startIndex = first.get(startCandidate);
                    int endIndex = last.get(endCandidate);
                    if (startIndex > endIndex) {
                        continue;
                    }

                    for (char middle : current) {
                        TreeSet<Integer> p = pos.get(middle);
                        Integer larger = p.ceiling(startIndex);
                        if (larger != null && larger <= endIndex) {
                            if (p.first() < startIndex || p.last() > endIndex) {
                                valid = false;
                                break;
                            }
                        }
                    }
                    if (valid) {
                        found = true;
                        if (endIndex <= bestEnd) {
                            if (endIndex < bestEnd) {
                                bestStart = startIndex;
                                bestEnd = endIndex;
                            } else {
                                if (startIndex > bestStart) {
                                    bestStart = startIndex;
                                    bestEnd = endIndex;
                                }
                            }
                        }
                    }
//                    System.out.println(String.format("%d %d %b", startIndex, endIndex, valid));
                }
            }
            if (found) {
                result.add(s.substring(bestStart, bestEnd + 1));
            }
            System.out.println(s.substring(bestStart, bestEnd + 1));
            for (int index = start; index <= bestEnd; index++) {
                used.add(s.charAt(index));
            }
            start = bestEnd + 1;
        }
        return result;
    }

//leetcode submit region end(Prohibit modification and deletion)

}