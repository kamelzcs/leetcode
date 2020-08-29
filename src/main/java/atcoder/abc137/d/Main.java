package atcoder.abc137.d;

import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    void solve(Scanner sc, PrintWriter pw) {
        int N = sc.nextInt();
        int M = sc.nextInt();
        SimpleEntry<Integer, Integer>[] data = new SimpleEntry[N];
        for (int i = 0; i < N; i++) {
            data[i] = new SimpleEntry<>(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(data, (a, b) -> {
            if (a.getKey() != b.getKey()) {
                return Integer.compare(b.getKey(), a.getKey());
            } else {
                return Integer.compare(b.getValue(), a.getValue());
            }
            });
        PriorityQueue<SimpleEntry<Integer, Integer>> pq = new PriorityQueue<>(N, Comparator.comparingInt(SimpleEntry::getValue));
        int result = 0;
        for (int i = 0; i < N; i++) {
            int days = data[i].getKey();
            int reward = data[i].getValue();
            if (pq.size() + days <= M) {
                pq.add(data[i]);
                result += reward;
            } else {
                if (!pq.isEmpty()) {
                    SimpleEntry<Integer, Integer> top = pq.peek();
                    if (reward > top.getValue()) {
                        result -= top.getValue();
                        pq.poll();
                        pq.add(data[i]);
                        result += reward;
                    }
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
