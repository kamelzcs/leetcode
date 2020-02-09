//Design a data structure that supports all following operations in average O(1)
// time. 
//
// 
// 
// insert(val): Inserts an item val to the set if not already present. 
// remove(val): Removes an item val from the set if present. 
// getRandom: Returns a random element from current set of elements. Each elemen
//t must have the same probability of being returned. 
// 
// 
//
// Example:
// 
//// Init an empty set.
//RandomizedSet randomSet = new RandomizedSet();
//
//// Inserts 1 to the set. Returns true as 1 was inserted successfully.
//randomSet.insert(1);
//
//// Returns false as 2 does not exist in the set.
//randomSet.remove(2);
//
//// Inserts 2 to the set, returns true. Set now contains [1,2].
//randomSet.insert(2);
//
//// getRandom should return either 1 or 2 randomly.
//randomSet.getRandom();
//
//// Removes 1 from the set, returns true. Set now contains [2].
//randomSet.remove(1);
//
//// 2 was already in the set, so return false.
//randomSet.insert(2);
//
//// Since 2 is the only number in the set, getRandom always return 2.
//randomSet.getRandom();
// 
// Related Topics Array Hash Table Design


package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetrandomO1 {
    public static void main(String[] args) {
        RandomizedSet solution = new InsertDeleteGetrandomO1().new RandomizedSet();
        solution.insert(1);
        solution.remove(2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Node {
        int value;
    }
    class RandomizedSet {
        int size;
        int capacity = 10000;
        Random random = new Random();
        Map<Integer, Node> map;
        Node[] nodes;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            nodes = new Node[capacity];
            map = new HashMap<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (size == capacity) {
                doubleCapacity();
            }

            if (map.containsKey(val)) {
                return false;
            }

            nodes[size] = new Node();
            nodes[size].value = val;
            map.put(val, nodes[size]);
            size++;

            return true;
        }

        private void doubleCapacity() {
            Node[] newNodes = new Node[2 * capacity];
            for (int i = 0; i < nodes.length; i++) {
                newNodes[i] = new Node();
                newNodes[i].value = nodes[i].value;
                map.put(nodes[i].value, newNodes[i]);
            }
            nodes = newNodes;
            capacity *= 2;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            if (size > 1) {
                Node newNode = map.get(val);
                Node originNode = nodes[size - 1];

                newNode.value = originNode.value;
                map.put(originNode.value, newNode);
            }
            map.remove(val);
            size--;
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return nodes[random.nextInt(size)].value;
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)

}