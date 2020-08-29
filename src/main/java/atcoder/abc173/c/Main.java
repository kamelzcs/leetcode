package atcoder.abc173.c;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int H = sc.nextInt();
        int W = sc.nextInt();
        int K = sc.nextInt();
        String[] data = new String[H];
        for (int i = 0; i < H; i++) {
            data[i] = sc.next();
        }
        int result = 0;
        for (int row = 0; row < (1 << H) - 1; row++) {
            for (int col =0; col < (1<<W) -1; col++) {
                int totalBlack = 0;
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        if (((1<<i) & row) > 0) {
                            continue;
                        }
                        if (((1<<j) & col) > 0) {
                            continue;
                        }
                        if (data[i].charAt(j) == '#') {
                            totalBlack++;
                        }
                    }
                }
                if (totalBlack == K) {
                    result++;
                }
            }
        }
        pw.println(result);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        new Main().solve(sc, pw);
        pw.flush();
        pw.close();
        sc.close();
    }
}
