package codeforces.education98;

import common.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String str = in.next();
        out.println(f(str, '(', ')') + f(str, '[', ']'));
    }

    private int f(String str, char l, char r) {
        int result = 0;
        int numL = 0;
        for (char c : str.toCharArray()) {
            if (c == l) {
                numL++;
            } else if (c == r) {
                if (numL > 0) {
                    numL--;
                    result++;
                }
            }
        }
        return result;
    }
}
