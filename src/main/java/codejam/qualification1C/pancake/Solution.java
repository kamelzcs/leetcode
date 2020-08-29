package codejam.qualification1C.pancake;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


class Pair {
    long dividend;
    long divisor;
    Pair(long dividend, long divisor) {
        long gcd = gcd(dividend, divisor);
        this.dividend = dividend / gcd;
        this.divisor = divisor / gcd;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair that = (Pair) obj;
        return this.dividend == that.dividend && this.divisor == that.divisor;
    }

    @Override
    public int hashCode() {
        return (int)(this.divisor * 19880101 + this.dividend);
    }

    public static long mod(long x, Pair candidate) {
        return (x * candidate.divisor) % candidate.dividend;
    }

    public static int compare(long size, Pair candidate) {
        return Long.compare(size * candidate.divisor, candidate.dividend);
    }

    public static long divide(long size, Pair candidate) {
        return size * candidate.divisor / candidate.dividend;
    }

    long gcd(long x, long y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
public class Solution {
    final static String PROBLEM_NAME = "test";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2020/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input.txt";
    final static String OUTPUT_FILE_NAME = "output.txt";

    int N;
    int D;
    List<Long> data;
    Set<Pair> slice;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        D = sc.nextInt();
        data = new ArrayList<>();
        slice = new HashSet<>();
        for (int i = 0; i < N; i++) {
            data.add(sc.nextLong());
            for (int splits = 1; splits <= D; splits++) {
                slice.add(new Pair(data.get(i), splits));
            }
        }
        data.sort(Long::compareTo);

        long result = D - 1;
        for (Pair candidate : slice) {
            result = Math.min(result, cuts(candidate));
        }
        pw.println(result);
    }

    private long cuts(Pair candidate) {
        long result = 0;
        long pieces = 0;
        long makeupPieces = 0;
        for (long size : data) {
            if (Pair.compare(size , candidate) < 0) {
                continue;
            }
            long newPieces = Pair.divide(size , candidate);
            long mod = Pair.mod(size , candidate);
            if (mod == 0) {
                if (newPieces + pieces > D) {
                    result += D - pieces;
                    return result;
                } else if (newPieces + pieces == D) {
                    result += D - pieces;
                    result--;
                    return result;
                }
                pieces += newPieces;
                result += newPieces - 1;
            } else {
                makeupPieces += newPieces;
            }
        }
        if (makeupPieces + pieces >= D) {
            result += D - pieces;
            return result;
        } else {
            return Long.MAX_VALUE / 4;
        }
    }

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
//        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
//                + OUTPUT_FILE_NAME));
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
