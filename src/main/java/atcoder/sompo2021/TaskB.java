package atcoder.sompo2021;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String str = in.next();
        out.println(f(str) ? "Yes" : "No");
    }

    private boolean f(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((i & 1) == 1) {
                if (!Character.isUpperCase(str.charAt(i))) {
                    return false;
                }
            } else {
                if (!Character.isLowerCase(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
