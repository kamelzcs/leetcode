//
//You are given a string expression representing a Lisp-like expression to retur
//n the integer value of.
// 
//The syntax for these expressions is given as follows.
// 
// An expression is either an integer, a let-expression, an add-expression, a mu
//lt-expression, or an assigned variable. Expressions always evaluate to a single 
//integer. 
// 
// (An integer could be positive or negative.) 
// 
// A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let i
//s always the string "let", then there are 1 or more pairs of alternating variabl
//es and expressions, meaning that the first variable v1 is assigned the value of 
//the expression e1, the second variable v2 is assigned the value of the expressio
//n e2, and so on sequentially; and then the value of this let-expression is the v
//alue of the expression expr. 
// 
// An add-expression takes the form (add e1 e2) where add is always the string "
//add", there are always two expressions e1, e2, and this expression evaluates to 
//the addition of the evaluation of e1 and the evaluation of e2. 
// 
// A mult-expression takes the form (mult e1 e2) where mult is always the string
// "mult", there are always two expressions e1, e2, and this expression evaluates 
//to the multiplication of the evaluation of e1 and the evaluation of e2. 
// 
// For the purposes of this question, we will use a smaller subset of variable n
//ames. A variable starts with a lowercase letter, then zero or more lowercase let
//ters or digits. Additionally for your convenience, the names "add", "let", or "m
//ult" are protected and will never be used as variable names. 
// 
// Finally, there is the concept of scope. When an expression of a variable name
// is evaluated, within the context of that evaluation, the innermost scope (in te
//rms of parentheses) is checked first for the value of that variable, and then ou
//ter scopes are checked sequentially. It is guaranteed that every expression is l
//egal. Please see the examples for more details on scope. 
// 
//
// Evaluation Examples: 
// 
//Input: (add 1 2)
//Output: 3
//
//Input: (mult 3 (add 2 3))
//Output: 15
//
//Input: (let x 2 (mult x 5))
//Output: 10
//
//Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
//Output: 14
//Explanation: In the expression (add x y), when checking for the value of the v
//ariable x,
//we check from the innermost scope to the outermost in the context of the varia
//ble we are trying to evaluate.
//Since x = 3 is found first, the value of x is 3.
//
//Input: (let x 3 x 2 x)
//Output: 2
//Explanation: Assignment in let statements is processed sequentially.
//
//Input: (let x 1 y 2 x (add x y) (add x y))
//Output: 5
//Explanation: The first (add x y) evaluates as 3, and is assigned to x.
//The second (add x y) evaluates as 3+2 = 5.
//
//Input: (let x 2 (add (let x 3 (let x 4 x)) x))
//Output: 6
//Explanation: Even though (let x 4 x) has a deeper scope, it is outside the con
//text
//of the final x in the add-expression.  That final x will equal 2.
//
//Input: (let a1 3 b2 (add a1 1) b2) 
//Output 4
//Explanation: Variable names can contain digits after the first character.
//
// 
//
// Note:
// The given string expression is well formatted: There are no leading or traili
//ng spaces, there is only a single space separating different components of the s
//tring, and no space between adjacent parentheses. The expression is guaranteed t
//o be legal and evaluate to an integer. 
// The length of expression is at most 2000. (It is also non-empty, as that woul
//d not be a legal expression.) 
// The answer and all intermediate calculations of that answer are guaranteed to
// fit in a 32-bit integer. 
// Related Topics String 
// 👍 294 👎 226


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseLispExpression {
    public static void main(String[] args) {
        Solution solution = new ParseLispExpression().new Solution();
//           System.out.println(solution.evaluate("(add 1 2)"));
//          System.out.println(solution.evaluate("(mult 3 (add 2 3))"));
//        System.out.println(solution.evaluate("(let x 2 (mult x 5))"));
//        System.out.println(solution.evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
//        System.out.println(solution.evaluate("(let x 3 x 2 x)"));
//        System.out.println(solution.evaluate("(let x 1 y 2 x (add x y) (add x y))"));
//        System.out.println(solution.evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
//        System.out.println(solution.evaluate("(let a1 3 b2 (add a1 1) b2)"));
        System.out.println(solution.evaluate("(let x (add 12 -7) (mult x x))"));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class InternalMatcher {
            Matcher matcher;
            String type;

            public InternalMatcher(Matcher matcher, String type) {
                this.matcher = matcher;
                this.type = type;
            }
        }

        class Token {
            String type;
            String content;

            public Token(String type, String content) {
                this.type = type;
                this.content = content;
            }
        }

        class Context {
            Map<String, Integer> context;
            Context parent;

            Context(Context parent) {
                this.context = new HashMap<>();
                this.parent = parent;
            }

            Context() {
                this(null);
            }

            Integer resolve(String v) {
                if (context.containsKey(v)) {
                    return context.get(v);
                }

                return parent == null ? null : parent.resolve(v);
            }

            void put(String v, int value) {
                context.put(v, value);
            }
        }

        String exp;
        Pattern leftParenPattern = Pattern.compile("^\\s*(\\()");
        Matcher leftParen;
        Pattern rightParenPattern = Pattern.compile("^\\s*(\\))");
        Matcher rightParen;
        Pattern letPattern = Pattern.compile("^\\s*(let)");
        Matcher let;
        Pattern addPattern = Pattern.compile("^\\s*(add)");
        Matcher add;
        Pattern mulPattern = Pattern.compile("^\\s*(mult)");
        Matcher mul;
        Pattern variablePattern = Pattern.compile("^\\s*([a-z]+\\w*)");
        Matcher variable;
        Pattern valuePattern = Pattern.compile("^\\s*(-?[0-9]+)");
        Matcher value;

        String leftToken = "left";
        String rightToken = "right";
        String letToken = "let";
        String addToken = "add";
        String mulToken = "mul";
        String variableToken = "variable";
        String valueToken = "value";
        List<InternalMatcher> internalMatchers;
        List<Token> tokens;

        int eval(Context context, int start, int end) {
//            System.out.println(pos);
//            if (context.parent != null) {
//                context.parent.context.forEach((key, value) -> System.out.print("[" + key + ":" + value + "]"));
//                System.out.println();
//            }
//            context.context.forEach((key, value) -> System.out.print("[" + key + ":" + value + "]"));
//            System.out.println();
            int nextStart = start;
            if (leftToken.equals(tokens.get(nextStart).type)) {
                nextStart++;
            }
            Token token = tokens.get(nextStart);
            String tokenType = token.type;
            int result = Integer.MAX_VALUE / 10;
            if (addToken.equals(tokenType)) {
                nextStart++;
                int firstEnd = nextStart + parse(nextStart);
                result = eval(new Context(context), nextStart, firstEnd) + eval(new Context(context), firstEnd, end - 1);
            } else if (mulToken.equals(tokenType)) {
                nextStart++;
                int firstEnd = nextStart + parse(nextStart);
                result = eval(new Context(context), nextStart, firstEnd) * eval(new Context(context), firstEnd, end - 1);
            } else {
                if (letToken.equals(tokenType)) {
                    nextStart++;
                    while (nextStart < end - 1) {
                        Token firstToken = tokens.get(nextStart);
                        String firstTokenType = firstToken.type;
                        if (variableToken.equals(firstTokenType)) {
                            String variableName = firstToken.content;
                            nextStart++;
                            int variableEnd = nextStart + parse(nextStart);
                            if (nextStart >= end - 1) {
                                result = context.resolve(variableName);
                                break;
                            } else {
                                int value = eval(new Context(context), nextStart, variableEnd);
                                context.put(variableName, value);
                                nextStart = variableEnd;
                            }
                        } else {
                            result = eval(new Context(context), nextStart, end - 1);
                            break;
                        }
                    }
                } else {
                    if (variableToken.equals(tokenType)) {
                        String variableName = token.content;
                        result = context.resolve(variableName);
                    } else {
                        result = Integer.parseInt(token.content);
                    }
                }
            }
            return result;
        }

        private int parse(int nextStart) {
            if (leftToken.equals(tokens.get(nextStart).type)) {
                int count = 0;
                for (int i = nextStart;;i++) {
                    String type  = tokens.get(i).type;
                    if (type.equals(leftToken)) {
                        count++;
                    } else if (type.equals(rightToken)) {
                        count--;
                    }
                    if (count == 0) {
                        return i - nextStart + 1;
                    }
                }
            } else {
                return 1;
            }
        }

        public int evaluate(String expression) {
            exp = expression;
            this.leftParen = leftParenPattern.matcher(exp);
            this.rightParen = rightParenPattern.matcher(exp);
            this.let = letPattern.matcher(exp);
            this.add = addPattern.matcher(exp);
            this.mul = mulPattern.matcher(exp);
            this.variable = variablePattern.matcher(exp);
            this.value = valuePattern.matcher(exp);
            internalMatchers = Arrays.asList(new InternalMatcher(leftParen, leftToken),
                    new InternalMatcher(rightParen, rightToken), new InternalMatcher(let, letToken), new InternalMatcher(add, addToken),
                    new InternalMatcher(mul, mulToken), new InternalMatcher(variable, variableToken), new InternalMatcher(value, valueToken));
            Context context = new Context();
            tokens = tokenize(0);
            return eval(context, 0, tokens.size());
        }

        private List<Token> tokenize(int pos) {
            if (pos >= exp.length()) {
                return new ArrayList<>();
            }
            List<Token> result = new ArrayList<>();
            for (InternalMatcher matcher : internalMatchers) {
                if (matcher.matcher.region(pos, exp.length()).find()) {
                    result.add(new Token(matcher.type, matcher.matcher.group(1)));
                    result.addAll(tokenize(matcher.matcher.end()));
                    return result;
                }
            }
            return new ArrayList<>();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}