package atcoder.arc108;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String str = in.next();
        Deque<Character> stk = new ArrayDeque<>();
        int removeCount = 0;
        for (char c : str.toCharArray()) {
            if (c == 'f' || c == 'o' || c == 'x') {
                if (c == 'x') {
                    if (stk.size() >= 2) {
                        char last = stk.pollLast();
                        char llast = stk.pollLast();
                        if (last == 'o' && llast == 'f') {
                            removeCount += 3;
                            continue;
                        } else {
                            stk.addLast(llast);
                            stk.addLast(last);
                        }
                    }
                }
            }
            stk.addLast(c);
        }
        out.println(n - removeCount);
    }
}
