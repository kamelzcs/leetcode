//Design and implement a data structure for Least Recently Used (LRU) cache. It
//should support the following operations: get and put. 
//
// get(key) - Get the value (will always be positive) of the key if the key exis
//ts in the cache, otherwise return -1. 
//put(key, value) - Set or insert the value if the key is not already present. W
//hen the cache reached its capacity, it should invalidate the least recently used
// item before inserting a new item. 
//
// The cache is initialized with a positive capacity. 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// Example: 
//
// 
//LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
// 
//
// 
// Related Topics Design


package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    public static void main(String[] args) {
//        Solution solution = new LruCache().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList {
        Node root = initRoot();

        private Node initRoot() {
            Node root = new Node(-1, -1);
            root.next = root;
            root.prev = root;
            return root;
        }

        void remove(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        void addFirst(Node node) {
            Node afterRoot = root.next;
            root.next = node;
            node.prev = root;

            node.next = afterRoot;
            afterRoot.prev = node;
        }

        Node getLast() {
            return root.prev;
        }


        void removeLast() {
            remove(getLast());
        }
    }
    class LRUCache {
        int capacity;
        Map<Integer, Node> map = new HashMap<>();
        DoubleLinkedList list = new DoubleLinkedList();
        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Node value = map.get(key);
            if (value == null) {
                return -1;
            }
            list.remove(value);
            list.addFirst(value);
            return value.value;
        }

        public void put(int key, int v) {
            Node value = map.get(key);
            // add new
            final Node node = new Node(key, v);
            if (value == null) {
                // no capacity
                if (capacity == 0) {
                    Node lastValue = list.getLast();
                    list.removeLast();
                    map.remove(lastValue.key);

                    map.put(key, node);
                    list.addFirst(node);
                } else {
                    capacity--;
                    map.put(key, node);
                    list.addFirst(node);
                }
            } else {
                list.remove(value);
                map.put(key, node);
                list.addFirst(node);
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}