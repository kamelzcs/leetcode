package atcoder.aising2020.c;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int[] result = new int[N + 1];
        for (int i = 1; i * i <= N; i++) {
            for (int j = 1; j * j <= N; j++) {
                for (int l = 1; l * l <= N; l++) {
                    int total = i * i + j * j + l * l + i* j + j* l + i * l;
                    if (total <=N) {
                        result[total]++;
                    }
                }
            }
        }
        for (int i =1; i <=N; i++) {
            pw.println(result[i]);
        }
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
