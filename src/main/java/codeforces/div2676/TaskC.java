package codeforces.div2676;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String str = in.next();
        out.println(3);
        out.println("L 2");
        out.println("R 2");
        out.println(String.format("R %d", 2 * str.length() - 1));
    }
}
