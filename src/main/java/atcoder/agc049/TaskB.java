package atcoder.agc049;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String x = in.next();
        String y = in.next();
        int numOneX = count(x);
        int numOneY = count(y);

        int delta = numOneX - numOneY;
        if (delta < 0 || ((delta & 1) == 1)) {
            out.println(-1);
            return;
        }

        long result = delta / 2;
        int one = 0;
        int posY = 0;
        int lastOneIndex = -1;
        for (int posX = 0; posX < n; posX++) {
            if (x.charAt(posX) == '0') {
                continue;
            }
            while (posY < n && y.charAt(posY) == '0') {
                posY++;
            }
            if (posX < posY) {
                if ((one & 1) == 1) {
                    result += posX - lastOneIndex - 1;
                }
                one++;
            } else {
                if ((one & 1) == 0) {
                    one = 0;
                    result += posX - posY;
                    posY++;
                } else {
                    result += posX - lastOneIndex - 1;
                    one++;
                }
            }
//            System.out.println(String.format("posX[%d] posY[%d] one[%d] lastOneIndex[%d] result[%d]", posX, posY, one, lastOneIndex, result));
            lastOneIndex = posX;
        }
        while (posY < n && y.charAt(posY) == '0') {
            posY++;
        }
        out.println(posY < n ? -1 : result);
    }

    private int count(String x) {
        return (int)x.chars().mapToObj(c -> (char) c).filter(c-> c == '1').count();
    }
}
