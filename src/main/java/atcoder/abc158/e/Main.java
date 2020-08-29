package atcoder.abc158.e;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    int N, P;
    int[] data;
    int[] powerOfTen;
    int[] invPowerOfTen;

    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        P = sc.nextInt();
        data = new int[N];
        powerOfTen = new int[N + 1];
        invPowerOfTen = new int[N + 1];
        String str = sc.next();
        for (int i = 0; i < str.length(); i++) {
            data[i] = str.charAt(i) - '0';
        }
        powerOfTen[0] = 1;
        for (int i = 1; i <= N; i++) {
            powerOfTen[i] = (powerOfTen[i - 1] * 10) % P;
        }
        invPowerOfTen[0] = 1;
        for (int i = 1; i <= N; i++) {
            invPowerOfTen[i] = power(powerOfTen[i], P - 2);
        }
        pw.println(solve(0, N));
    }

    private int power(int v, int p) {
        if (p == 0) {
            return 1;
        }
        int part = power(v, p / 2);
        int candidate = (part * part) % P;
        int multiply = (p % 2) == 0 ? 1 : v;
        return (multiply * candidate) % P;
    }

    private long solve(int left, int right) {
        if (left + 1 == right) {
            return data[left] % P == 0 ? 1 : 0;
        }


        int mid = left + (right - left) / 2;
        long partial = solve(left, mid) + solve(mid, right);
        int leftStart = mid - 1;
        int currentLeft = 0;
        Map<Integer, Integer> occurs = new HashMap<>();
        while (leftStart >= left) {
            int len = mid - leftStart;
            currentLeft = (((data[leftStart] * powerOfTen[len - 1]) % P) + currentLeft) % P;
            occurs.put(currentLeft, occurs.getOrDefault(currentLeft, 0) + 1);
            leftStart--;
        }

        long result = 0;
        int rightStart = mid;
        int currentRight = 0;
        while (rightStart < right) {
            currentRight = (data[rightStart] + currentRight * 10) % P;
            int lenRight = rightStart - mid + 1;
            int matchLeft;
            if (P == 2 || P == 5) {
                if (currentRight == 0) {
                    result += (mid - left);
                }
            } else {
                 matchLeft = (P - ((invPowerOfTen[lenRight] * currentRight) % P)) % P;
                result += occurs.getOrDefault(matchLeft, 0);
            }
            rightStart++;
        }
//        System.out.println(String.format("left:%d right:%d partial:%d result:%d", left, right, partial, result));
        return result + partial;
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
