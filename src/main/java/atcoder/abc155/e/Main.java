package atcoder.abc155.e;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    void solve(Scanner sc, PrintWriter pw) {
        String N = sc.next();
        int sum = 0;
        int delta = 0;
        int started = -1;
        String part = "";
        for (int i = N.length() - 1; i >= 0 ; i--) {
            int digit = N.charAt(i) - '0';
            sum += digit;
            if (digit <= 4) {
                if (started >= 0) {
                    part = (String.format("%d%s", digit + 1, String.join("", Collections.nCopies(started - i, "0")))) + part;
                } else {
                    part = digit + part;
                }
                started = -1;
                continue;
            }
            if (digit == 5) {
                if (started >= 0) {
                    delta += 2 * digit - 9;
                } else {
                    if (i > 0 && N.charAt(i - 1) >= '5') {
                        started = i;
                        delta += 2 * digit - 11;
                    } else {
                        part = digit + part;
                    }
                }
            } else {
                if (started >= 0) {
                    delta += 2 * digit - 9;
                } else {
                    started = i;
                    delta += 2 * digit - 11;
                }
            }
        }
        pw.println(part);
        pw.println(cal(N, part));
        pw.println(sum - delta);
    }

    private int cal(String n, String part) {
        int carry = 0;
        int result = 0;
        for (int i = part.length() - 1; i >= 0; i--) {
            int p = part.charAt(i) - '0';
            int d = n.charAt(i) - '0';
            result += p;
            int currentDigit = p + carry - d;
            if (currentDigit < 0) {
                carry = -1;
                currentDigit += 10;
                result += currentDigit;
            } else {
                carry = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}
