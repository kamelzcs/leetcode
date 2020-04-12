//Given the array restaurants where restaurants[i] = [idi, ratingi, veganFriendl
//yi, pricei, distancei]. You have to filter the restaurants using three filters. 
//
//
// The veganFriendly filter will be either true (meaning you should only include
// restaurants with veganFriendlyi set to true) or false (meaning you can include 
//any restaurant). In addition, you have the filters maxPrice and maxDistance whic
//h are the maximum value for price and distance of restaurants you should conside
//r respectively. 
//
// Return the array of restaurant IDs after filtering, ordered by rating from hi
//ghest to lowest. For restaurants with the same rating, order them by id from hig
//hest to lowest. For simplicity veganFriendlyi and veganFriendly take value 1 whe
//n it is true, and 0 when it is false. 
//
// 
// Example 1: 
//
// 
//Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5
//,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
//Output: [3,1,5] 
//Explanation: 
//The restaurants are:
//Restaurant 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
//Restaurant 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
//Restaurant 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
//Restaurant 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
//Restaurant 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1] 
//After filter restaurants with veganFriendly = 1, maxPrice = 50 and maxDistance
// = 10 we have restaurant 3, restaurant 1 and restaurant 5 (ordered by rating fro
//m highest to lowest). 
// 
//
// Example 2: 
//
// 
//Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5
//,1,1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
//Output: [4,3,2,1,5]
//Explanation: The restaurants are the same as in example 1, but in this case th
//e filter veganFriendly = 0, therefore all restaurants are considered.
// 
//
// Example 3: 
//
// 
//Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5
//,1,1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
//Output: [4,5]
// 
//
// 
// Constraints: 
//
// 
// 1 <= restaurants.length <= 10^4 
// restaurants[i].length == 5 
// 1 <= idi, ratingi, pricei, distancei <= 10^5 
// 1 <= maxPrice, maxDistance <= 10^5 
// veganFriendlyi and veganFriendly are 0 or 1. 
// All idi are distinct. 
// Related Topics Array Sort


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterRestaurantsByVeganFriendlyPriceAndDistance {
    public static void main(String[] args) {
        Solution solution = new FilterRestaurantsByVeganFriendlyPriceAndDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
            return Arrays.stream(restaurants)
                    .filter(x -> x[2] == (veganFriendly == 1 ? 1 : x[2]))
                    .filter(x -> x[3] <= maxPrice)
                    .filter(x -> x[4] <= maxDistance)
                    .sorted((a, b) -> {
                        if (a[1] != b[1]) {
                            return Integer.compare(b[1], a[1]);
                        } else {
                            return Integer.compare(b[0], a[0]);
                        }
                    })
                    .map(x -> x[0])
                    .collect(Collectors.toList());
        }
        public String rankTeams(String[] votes) {
            Map<Character, List<Integer>> map = new HashMap<>();
            for (String vote : votes) {
                for (int i = 0; i < vote.length(); i++) {
                    char c = vote.charAt(i);
                    map.computeIfAbsent(c, x -> {
                        List<Integer> pos = new ArrayList<>();
                        for (int j = 0; j < vote.length(); j++) {
                            pos.add(0);
                        }
                        return pos;
                    }).set(i, map.get(c).get(i) + 1);

                }
            }

            List<Character> part = votes[0].chars().mapToObj(x -> (char)x).collect(Collectors.toList());
            part = part.stream().sorted((x, y) -> {
                List<Integer> posX = map.get(x);
                List<Integer> posY = map.get(y);

                for (int i = 0; i < posX.size(); i++) {
                    if (posX.get(i) != posY.get(i)) {
                        if (posX.get(i) > posY.get(i)) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                }
                return 0;
            }).collect(Collectors.toList());

            StringBuilder result = new StringBuilder();
            for (char c : part) {
                result.append(c);
            }
            return result.toString();
        }

        private boolean isSubPath2(ListNode head, TreeNode root) {
            if (head == null) {
                return root == null;
            }

            if (root == null) {
                return false;
            }

            if (root.val == head.val) {
                return (isSubPath2(head.next, root.left) || isSubPath2(head.next, root.right));
            }
            return false;
        }
        public boolean isSubPath(ListNode head, TreeNode root) {
            if (head == null) {
                return true;
            }
            if (root == null) {
                return false;
            }

            if (root.val == head.val) {
                if (isSubPath2(head.next, root.left) || isSubPath2(head.next, root.right)) {
                    return true;
                }
            }
            return isSubPath(head, root.left) || isSubPath(head, root.right);
        }

        public int minCost(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dxy = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            Set<SimpleEntry<Integer, Integer>> visited = new HashSet<>();
            PriorityQueue<SimpleEntry<Integer, SimpleEntry<Integer, Integer>>> pq = new PriorityQueue<>(m + n, Comparator.comparing(SimpleEntry::getKey));
            SimpleEntry<Integer, Integer> target = new SimpleEntry<>(m - 1, n - 1);
            visited.add(new SimpleEntry<>(0, 0));
            pq.add(new SimpleEntry<>(0, new SimpleEntry<>(0, 0)));

            while (!pq.isEmpty()) {
                SimpleEntry<Integer, SimpleEntry<Integer, Integer>> top = pq.poll();
                visited.add(top.getValue());
                if (target.equals(top.getValue())) {
                    return top.getKey();
                }
                SimpleEntry<Integer, Integer> pos = top.getValue();
                for (int i = 1; i <= 4; i++) {
                    int nextX = pos.getKey() + dxy[i - 1][0];
                    int nextY = pos.getValue() + dxy[i - 1][1];

                    if (nextX < 0 || nextX >= m) {
                        continue;
                    }

                    if (nextY < 0 || nextY >= n) {
                        continue;
                    }

                    SimpleEntry<Integer, Integer> candidate = new SimpleEntry<>(nextX, nextY);
                    if (visited.contains(candidate)) {
                        continue;
                    }

                    if (i == grid[pos.getKey()][pos.getValue()]) {
                        pq.add(new SimpleEntry<>(top.getKey(), candidate));
                    } else {
                        pq.add(new SimpleEntry<>(top.getKey() + 1, candidate));
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}