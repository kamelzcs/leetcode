package atcoder.abc184;

import common.InputReader;

import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int r1 = in.nextInt();
        int c1 = in.nextInt();
        int r2 = in.nextInt();
        int c2 = in.nextInt();

        if (r1 == r2 && c1 == c2) {
            out.println(0);
        } else {
            if (isValid(r1, c1, r2, c2)) {
                out.println(1);
            } else {
                for (int x = -4; x <= 4; x++) {
                    for (int y = -1; y <= 4; y++) {
                        int newR = r1 + x;
                        int newC = c1 + y;
                        if (isValid(r1, c1, newR, newC) && isValid(newR, newC, r2, c2)) {
                            out.println(2);
                            return;
                        }
                    }
                    out.println(3);
                }
            }
        }
    }

    private boolean isValid(int r1, int c1, int r2, int c2) {
        return r1 + c1 == r2 + c2 || r1 - c1 == r2 - c2 || (Math.abs(r1 - r2) + Math.abs(c1 - c2) <= 3);
    }
}
