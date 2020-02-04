//Implement a basic calculator to evaluate a simple expression string.
//
// The expression string contains only non-negative integers, +, -, *, / operato
//rs and empty spaces . The integer division should truncate toward zero.
//
// Example 1:
//
//
//Input: "3+2*2"
//Output: 7
//
//
// Example 2:
//
//
//Input: " 3/2 "
//Output: 1
//
// Example 3:
//
//
//Input: " 3+5 / 2 "
//Output: 5
//
//
// Note:
//
//
// You may assume that the given expression is always valid.
// Do not use the eval built-in library function.
//
// Related Topics String


package leetcode.editor.en;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicCalculatorIi {
    public static void main(String[] args) {
        Solution solution = new BasicCalculatorIi().new Solution();
//        System.out.println(solution.calculate("3 + 2 * 2"));
//        System.out.println(solution.calculate("3 + 5 / 2"));
        System.out.println(solution.calculate("1 - 1 + 1"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    interface Binary {
        int eval(int left, int right);
    }
    enum Op implements Binary{
        Plus {
            @Override
            public int eval(int left, int right) {
                return left + right;
            }
        },

        Minus {
            @Override
            public int eval(int left, int right) {
                return left - right;
            }
        }
    }

    class Solution {
        Matcher numberMatcher;
        int index;
        Stack<Integer> numbers;
        Stack<Op> ops;
        public int calculate(String s) {
            numberMatcher = Pattern.compile("^\\s*(\\d+)\\s*").matcher(s);
            numbers = new Stack<>();
            ops = new Stack<>();
            numberMatcher.find();
            int currentNumber = Integer.parseInt(numberMatcher.group(1));
            numbers.add(currentNumber);
            index = numberMatcher.end();
            while (index < s.length()) {
                char op = s.charAt(index);
                int opIndex = index;
                index++;
                numberMatcher.region(index, numberMatcher.regionEnd()).find();
                int nextNumber = Integer.parseInt(numberMatcher.group(1));
                index = numberMatcher.end();
                if (op == '+' || op == '-') {
                    if (!ops.empty()) {
                        int right = numbers.pop();
                        int left = numbers.pop();
                        numbers.add(ops.pop().eval(left, right));
                    }
                    if (op == '+') {
                        ops.add(Op.Plus);
                        numbers.add(nextNumber);
                    } else {
                        ops.add(Op.Minus);
                        numbers.add(nextNumber);
                    }
                } else {
                    int topNumber = numbers.pop();
                    if (op == '*') {
                        numbers.add(topNumber * nextNumber);
                    } else if (op == '/') {
                        numbers.add(topNumber / nextNumber);
                    } else {
                        throw new IllegalArgumentException("Need op at " + opIndex);
                    }
                }
            }

            while (!ops.empty()) {
                Op op = ops.pop();
                int right = numbers.pop();
                int left = numbers.pop();
                numbers.add(op.eval(left, right));
            }

            return numbers.peek();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}