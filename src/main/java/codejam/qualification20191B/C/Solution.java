package codejam.qualification20191B.C;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    final static String PROBLEM_NAME = "fight";
    final static String WORK_DIR = "/home/kamel/Code/java/LeetCode/data/gcj2019/q1b/" + PROBLEM_NAME + "/";
    final static String INPUT_FILE_NAME = "input";
    final static String OUTPUT_FILE_NAME = "output";

    int N, K;
    int highBitN;
    int[] C;
    int[] D;
    int[][] rmqC;
    int[][] rmqD;
    void solve(Scanner sc, PrintWriter pw) {
        N = sc.nextInt();
        N += 2;
        highBitN = highBit(N);
        K = sc.nextInt();
        C = new int[N];
        C[0] = Integer.MAX_VALUE;
        C[N - 1] = Integer.MAX_VALUE;
        for (int i = 0; i < N - 2; i++) {
            C[i + 1] = sc.nextInt();
        }

        D = new int[N];
        D[0] = Integer.MAX_VALUE;
        D[N - 1] = Integer.MAX_VALUE;
        for (int i = 0; i < N - 2; i++) {
            D[i + 1] = sc.nextInt();
        }

        rmqC = new int[C.length + 1][highBitN + 1];
        buildRMQ(rmqC, C);

        rmqD = new int[D.length + 1][highBitN + 1];
        buildRMQ(rmqD, D);

        pw.println(calculate());
    }

    private long calculate() {
        long result = 0;
        for (int i = 1; i < N - 1; i++) {
            int rightD = rightBigger(rmqD, D, i, D[i]);
            int leftD = leftSmaller(rmqD, D, i - 1, D[i]);

            int rightC = rightBigger(rmqC, C, i, D[i] - K - 1);
            int rightC2 = rightSmaller(rmqC, C, rightC, D[i] + K + 1);

            int leftC = leftBigger(rmqC, C, i, D[i] - K - 1);
            int leftC2 = leftSmaller(rmqC, C, leftC, D[i] + K + 1);

            int rightMost = Math.min(rightD, rightC2);
            int leftMost = Math.max(leftD, leftC2);

            result += (long)(rightMost - rightC) * (leftC - leftMost);
            System.out.println(String.format("%d %d %d %d %d %d %d", leftD, rightD, leftC, leftC2, rightC, rightC2, result));
        }
        return result;
    }

    private int leftBigger(int[][] rmqD, int[] d, int index, int value) {
        if (index < 0) {
            return index;
        }

        int left = 0;
        int right = index;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (d[query(rmqD, d, mid, index)] <= value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private int rightSmaller(int[][] rmqD, int[] d, int index, int value) {
        if (index >= N) {
            return index;
        }

        int left = index;
        int right = N;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (d[query(rmqD, d, index, mid)] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int leftSmaller(int[][] rmqD, int[] d, int index, int value) {
        if (index < 0) {
            return index;
        }

        int left = 0;
        int right = index;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (d[query(rmqD, d, mid, index)] < value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private int rightBigger(int[][] rmqD, int[] d, int index, int value) {
        if (index >= d.length) {
            return d.length;
        }

        int left = index;
        int right = d.length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (d[query(rmqD, d, index, mid)] <= value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int query(int[][] rmqD, int[] d, int index, int m) {
        if (m == index) {
            return index;
        }
        int mid = m + 1;
        int high = highBit(mid - index);
        int l = rmqD[index][high];
        int r = rmqD[mid - (1<<high)][high];
        if (d[l] > d[r]) {
            return l;
        } else {
            return r;
        }
    }

    private void buildRMQ(int[][] rmq, int[] data) {
        for (int i = 0; i < data.length; i++) {
            rmq[i][0] = i;
        }
        for (int i = 1; i <= highBitN; i++) {
            for (int j = 0; j + (1<<i) <= N; j++) {
                if (data[rmq[j][i - 1]] >= data[rmq[j + (1<< (i - 1))][i - 1]]) {
                    rmq[j][i] = rmq[j][i - 1];
                } else {
                    rmq[j][i] = rmq[j + (1<< (i - 1))][i - 1];
                }
            }
        }
    }

    private int highBit(int n) {
        if (n == 1) {
            return 0;
        }
        int result = 0;
        while (n > 1) {
            n >>= 1;
            result++;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + INPUT_FILE_NAME));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR
                + OUTPUT_FILE_NAME));
//        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        PrintWriter pw = new PrintWriter(System.out);
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Solution().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
