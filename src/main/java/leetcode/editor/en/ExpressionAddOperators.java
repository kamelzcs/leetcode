//Given a string that contains only digits 0-9 and a target value, return all po
//ssibilities to add binary operators (not unary) +, -, or * between the digits so
// they evaluate to the target value. 
//
// Example 1: 
//
// 
//Input: num = "123", target = 6
//Output: ["1+2+3", "1*2*3"] 
// 
//
// Example 2: 
//
// 
//Input: num = "232", target = 8
//Output: ["2*3+2", "2+3*2"] 
//
// Example 3: 
//
// 
//Input: num = "105", target = 5
//Output: ["1*0+5","10-5"] 
//
// Example 4: 
//
// 
//Input: num = "00", target = 0
//Output: ["0+0", "0-0", "0*0"]
// 
//
// Example 5: 
//
// 
//Input: num = "3456237490", target = 9191
//Output: []
// 
// Related Topics Divide and Conquer


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpressionAddOperators {
    public static void main(String[] args) {
        Solution solution = new ExpressionAddOperators().new Solution();
        System.out.println(solution.addOperators("123", 6));
        System.out.println(solution.addOperators("232", 8));
        System.out.println(solution.addOperators("105", 5));
        System.out.println(solution.addOperators("00", 0));
        System.out.println(solution.addOperators("3456237490", 9191));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    interface Op {
        long eval(long left, long right);
    }

    enum Operator implements Op {
        Plus("+") {
            @Override
            public long eval(long left, long right) {
                return left + right;
            }
        },
        Times("*") {
            @Override
            public long eval(long left, long right) {
                return left * right;
            }
        },
        Minus("-") {
            @Override
            public long eval(long left, long right) {
                return left - right;
            }
        };

        String opStr;

        Operator(String str) {
            this.opStr = str;
        }
    }

    class Solution {
        String num;
        int target;

        public List<String> addOperators(String num, int target) {
            this.num = num;
            this.target = target;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(0) == '0') {
                    return dfs("0", 0, Operator.Plus, 0, 1);
                }

                String substr = num.substring(0, i + 1);
                result.addAll(dfs(substr, 0L, Operator.Plus, Long.parseLong(substr), i + 1));
            }
            return result;
        }

        private List<String> dfs(String str, long pre2, Operator op, long prev, int index) {
            if (index >= this.num.length()) {
                if (op.eval(pre2, prev) == this.target) {
                    return Collections.singletonList(str);
                } else {
                    return Collections.emptyList();
                }
            }

            List<String> result = new ArrayList<>();

            for (int right = index + 1; right <= this.num.length(); right++) {
                String nextStr = this.num.substring(index, right);
                // +
                result.addAll(dfs(str + "+" + nextStr, op.eval(pre2, prev), Operator.Plus, Long.parseLong(nextStr), right));

                // -
                result.addAll(dfs(str + "-" + nextStr, op.eval(pre2, prev), Operator.Minus, Long.parseLong(nextStr), right));

                // *
                result.addAll(dfs(str + "*" + nextStr, pre2, op, Operator.Times.eval(prev, Long.parseLong(nextStr)), right));

                if (this.num.charAt(index) == '0') {
                    break;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}