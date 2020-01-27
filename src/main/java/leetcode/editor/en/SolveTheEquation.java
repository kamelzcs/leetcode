//
//Solve a given equation and return the value of x in the form of string "x=#val
//ue". The equation contains only '+', '-' operation, the variable x and its coeff
//icient.
// 
//
// 
//If there is no solution for the equation, return "No solution".
// 
// 
//If there are infinite solutions for the equation, return "Infinite solutions".
//
// 
// 
//If there is exactly one solution for the equation, we ensure that the value of
// x is an integer.
// 
//
// Example 1: 
// 
//Input: "x+5-3+x=6+x-2"
//Output: "x=2"
// 
// 
//
// Example 2: 
// 
//Input: "x=x"
//Output: "Infinite solutions"
// 
// 
//
// Example 3: 
// 
//Input: "2x=x"
//Output: "x=0"
// 
// 
//
// Example 4: 
// 
//Input: "2x+3x-6x=x+2"
//Output: "x=-1"
// 
// 
//
// Example 5: 
// 
//Input: "x=x+2"
//Output: "No solution"
// 
// Related Topics Math


package leetcode.editor.en;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolveTheEquation {
    public static void main(String[] args) {
        Solution solution = new SolveTheEquation().new Solution();
//        System.out.println(solution.solveEquation("-x=-2"));
        System.out.println(solution.solveEquation("x+5-3+x=6+x-2"));
        Matcher matcher = Pattern.compile("^-?x").matcher("2-x");
        matcher.region(1, matcher.regionEnd());
        matcher.find();
        System.out.println(matcher.start());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Pair {
        int coefficient;
        int value;
        Pair(int c, int v) {
            this.coefficient = c;
            this.value = v;
        }

        Pair combine(Pair right) {
            return new Pair(this.coefficient + right.coefficient, this.value + right.value);
        }
    }

    interface Evaluator {
        Pair eval(UnkownExp exp);
        Pair eval(Data data);
        Pair eval(PlusOp op);
        Pair eval(MinusOp op);
    }

    class NormalEval implements Evaluator {
        @Override
        public Pair eval(UnkownExp exp) {
            return new Pair(exp.coefficient, 0);
        }

        @Override
        public Pair eval(Data data) {
            return new Pair(0, data.value);
        }

        @Override
        public Pair eval(PlusOp op) {
            Pair leftResult = op.left.eval(this);
            Pair rightResult = op.right.eval(this);
            return leftResult.combine(rightResult);
        }

        @Override
        public Pair eval(MinusOp op) {
            Pair leftResult = op.left.eval(this);
            Pair rightResult = op.right.eval(this);
            return leftResult.combine(new Pair(-rightResult.coefficient, -rightResult.value));
        }
    }

    interface Exp {
        Pair eval(Evaluator evaluator);
    }

    class UnkownExp implements Exp {
        int coefficient;
        UnkownExp(int coefficient) {
            this.coefficient = coefficient;
        }
        @Override
        public Pair eval(Evaluator evaluator) {
            return evaluator.eval(this);
        }
    }

    class Data implements Exp {
        int value;
        Data(int value) {
            this.value = value;
        }

        @Override
        public Pair eval(Evaluator evaluator) {
            return evaluator.eval(this);
        }
    }

    abstract class Op implements Exp {
        Exp left;
        Exp right;
        Op(Exp left, Exp right) {
            this.left = left;
            this.right = right;
        }
        Op() {
        }
    }

    class PlusOp extends Op {
        @Override
        public Pair eval(Evaluator evaluator) {
            return evaluator.eval(this);
        }
    }

    class MinusOp extends Op {
        @Override
        public Pair eval(Evaluator evaluator) {
            return evaluator.eval(this);
        }
    }

    class Parser {
        String content;
        int currentIndex;
        Pattern NUMBER = Pattern.compile("^-?\\d+");
        Matcher singleUnkown;
        Matcher multipleUnkown;
        Matcher numberMatcher;
        Parser(String s) {
            this.content = s;
            this.singleUnkown = Pattern.compile("^-?x").matcher(content);
            this.multipleUnkown = Pattern.compile("^(-?\\d+)x").matcher(content);
            this.numberMatcher = NUMBER.matcher(content);
        }

        Exp parse() {
            Exp firstSingle = this.parseSingle();
            Exp result = firstSingle;
            while (currentIndex < content.length()) {
                Op op = this.parseOp();
                Exp right = this.parseSingle();
                op.left = result;
                op.right = right;
                result = op;
            }
            return result;
        }

        private Op parseOp() {
            char c = content.charAt(currentIndex);
            currentIndex++;
            if (c == '+') {
                return new PlusOp();
            } else if (c == '-') {
                return new MinusOp();
            } else {
                throw new IllegalArgumentException("Expect op at " + currentIndex);
            }
        }

        Exp parseSingle() {
            singleUnkown.region(currentIndex, singleUnkown.regionEnd());
            multipleUnkown.region(currentIndex, multipleUnkown.regionEnd());
            numberMatcher.region(currentIndex, numberMatcher.regionEnd());
            if (singleUnkown.find()) {
                currentIndex = singleUnkown.end();
                if (content.charAt(singleUnkown.start()) == '-') {
                    return new UnkownExp(-1);
                } else {
                    return new UnkownExp(1);
                }
            } else if (multipleUnkown.find()) {
                currentIndex = multipleUnkown.end();
                return new UnkownExp(Integer.valueOf(multipleUnkown.group(1)));
            } else if (numberMatcher.find()) {
                currentIndex = numberMatcher.end();
                return new Data(Integer.valueOf(numberMatcher.group()));
            } else {
                throw new IllegalArgumentException("Expect single at " + currentIndex);
            }
        }
    }

    class Solution {
        public String solveEquation(String equation) {
            Evaluator evaluator = new NormalEval();
            String[] parts = equation.split("=");
            Exp leftExp = new Parser(parts[0]).parse();
            Pair leftResult = leftExp.eval(evaluator);
            Exp rightExp = new Parser(parts[1]).parse();
            Pair rightResult = rightExp.eval(evaluator);

            int leftCoe = leftResult.coefficient - rightResult.coefficient;
            int rightNumber = rightResult.value - leftResult.value;

            if (leftCoe == 0) {
                if (rightNumber == 0) {
                    return "Infinite solutions";
                } else {
                    return "No solution";
                }
            }
            return "x=" + rightNumber / leftCoe;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}