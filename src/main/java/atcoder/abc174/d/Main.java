package atcoder.abc174.d;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int[] data = new int[N];
        int totalZero = 0;
        String str = sc.next();
        for (int i = 0; i < str.length(); i++) {
            data[i] = str.charAt(i) == 'W' ? 1 : 0;
            if (data[i] == 0) {
                totalZero++;
            }
        }
        int result = 0;
        for (int i = 0; i < totalZero; i++) {
            if (data[i] == 1) {
                result++;
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
