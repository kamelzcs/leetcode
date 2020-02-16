//Design a Skiplist without using any built-in libraries.
//
// A Skiplist is a data structure that takes O(log(n)) time to add, erase and se
//arch. Comparing with treap and red-black tree which has the same function and pe
//rformance, the code length of Skiplist can be comparatively short and the idea b
//ehind Skiplists are just simple linked lists. 
//
// For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to
// add 80 and 45 into it. The Skiplist works this way: 
//
// 
//Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons 
//
// You can see there are many layers in the Skiplist. Each layer is a sorted lin
//ked list. With the help of the top layers, add , erase and search can be faster 
//than O(n). It can be proven that the average time complexity for each operation 
//is O(log(n)) and space complexity is O(n). 
//
// To be specific, your design should include these functions: 
//
// 
// bool search(int target) : Return whether the target exists in the Skiplist or
// not. 
// void add(int num): Insert a value into the SkipList. 
// bool erase(int num): Remove a value in the Skiplist. If num does not exist in
// the Skiplist, do nothing and return false. If there exists multiple num values,
// removing any one of them is fine. 
// 
//
// See more about Skiplist : https://en.wikipedia.org/wiki/Skip_list 
//
// Note that duplicates may exist in the Skiplist, your code needs to handle thi
//s situation. 
//
// 
//
// Example: 
//
// 
//Skiplist skiplist = new Skiplist();
//
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // return false.
//skiplist.add(4);
//skiplist.search(1);   // return true.
//skiplist.erase(0);    // return false, 0 is not in skiplist.
//skiplist.erase(1);    // return true.
//skiplist.search(1);   // return false, 1 has already been erased. 
//
// 
// Constraints: 
//
// 
// 0 <= num, target <= 20000 
// At most 50000 calls will be made to search, add, and erase. 
// Related Topics Design


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DesignSkiplist {
    public static void main(String[] args) {
        Skiplist solution = new DesignSkiplist().new Skiplist();
        solution.add(1);
        solution.add(2);
        solution.add(3);
        System.out.println(solution.search(0));
        solution.add(4);
        System.out.println(solution.search(1));
        System.out.println(solution.erase(0));
        System.out.println(solution.erase(1));
        System.out.println(solution.search(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class ListNode {
        int value;
        List<Data> children;

        ListNode(int value) {
            this.value = value;
            children = new ArrayList<>();
        }

    }

    class Data {
        int index;
        ListNode parent;
        Data next;
    }

    class Skiplist {
        ListNode root;

        void insertAfter(Data current, int value) {
            ListNode node = new ListNode(value);
            Data data = new Data();
            data.parent = node;
            data.next = current.next;
            node.children.add(data);
            current.next = data;
        }

        public Skiplist() {
            ListNode endNode = new ListNode(Integer.MAX_VALUE);
            Data end = new Data();
            end.index = 0;
            end.parent = endNode;
            endNode.children.add(end);

            root = new ListNode(Integer.MIN_VALUE);
            Data rootData = new Data();
            rootData.parent = root;
            rootData.next = end;
            root.children.add(rootData);
        }

        public boolean search(int target) {
            Data current = root.children.get(root.children.size() - 1);

            while (current.parent.value < target) {
                if (current.next.parent.value < target) {
                    current = current.next;
                } else {
                    if (current.next.parent.value == target) {
                        return true;
                    } else {
                        if (current.index > 0) {
                            current = current.parent.children.get(current.index - 1);
                        } else {
                            current = current.next;
                        }
                    }
                }
            }
            if (current.parent.value == target) {
                return true;
            }
            return false;
        }

        public void add(int num) {
            Data current = root.children.get(root.children.size() - 1);
            Stack<Data> used = new Stack<>();
            while (current.next.parent.value < num || current.index > 0) {
                if (current.next.parent.value < num) {
                    current = current.next;
                } else {
                    used.add(current);
                    current = current.parent.children.get(current.index - 1);
                }
            }
            insertAfter(current, num);
            Random random = new Random();
            ListNode newNode = current.next.parent;
            while (random.nextBoolean() && !used.isEmpty()) {
                Data prev = used.pop();
                Data data = new Data();
                data.parent = newNode;
                data.next = prev.next;
                data.index = newNode.children.size();
                prev.next = data;
                newNode.children.add(data);
            }
        }

        public boolean erase(int num) {
            Data current = root.children.get(root.children.size() - 1);
            boolean result = false;
            while (current.next.parent.value < num || current.index > 0) {
                if (current.next.parent.value < num) {
                    current = current.next;
                } else {
                    if (current.next.parent.value == num) {
                        Data toDelete = current.next;
                        current.next = toDelete.next;
                        toDelete.parent.children.remove(toDelete.index);
                        result = true;
                    }
                    if (current.index > 0) {
                        current = current.parent.children.get(current.index - 1);
                    } else {
                        break;
                    }
                }
            }

            if (current.next.parent.value == num) {
                Data toDelete = current.next;
                current.next = toDelete.next;
                toDelete.parent.children.remove(toDelete.index);
                result = true;
            }
            return result;
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
//leetcode submit region end(Prohibit modification and deletion)

}