package codejam.q22020.serucity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Data {
    int id;
    int minutes;
    int counts;
    Data(int id, int minutes, int counts) {
        this.id = id;
        this.minutes = minutes;
        this.counts = counts;
    }
}
public class Solution {
    int D, C;
    int MAX = 1_000_000;
    // key is id, value is counts
    PriorityQueue<SimpleEntry<Integer, Integer>> countsQueue;

    // key is id, value is distance
    PriorityQueue<SimpleEntry<Integer, Integer>> minutesQueue;

    Map<Integer, List<Integer>> neighbors;
    Map<Integer, Integer> distance;
    Map<SimpleEntry<Integer, Integer>, Integer> lengths;
    List<Data> lists;
    List<SimpleEntry<Integer, Integer>> edges;
    void solve(Scanner sc, PrintWriter pw) {
        C = sc.nextInt();
        D = sc.nextInt();
        countsQueue = new PriorityQueue<>(C, Comparator.comparingInt(SimpleEntry::getValue));
        minutesQueue = new PriorityQueue<>(C, Comparator.comparingInt(SimpleEntry::getValue));
        neighbors = new HashMap<>();
        distance = new HashMap<>();
        lengths = new HashMap<>();
        lists = new ArrayList<>();
        edges = new ArrayList<>();
        for (int i = 2; i <= C; i++) {
            int val = sc.nextInt();

            // counter
            if (val < 0) {
                countsQueue.add(new SimpleEntry<>(i, -val));
            } else {
                minutesQueue.add(new SimpleEntry<>(i, val));
            }
        }

        for (int i = 0; i < D; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            neighbors.computeIfAbsent(from, v -> new ArrayList<>()).add(to);
            neighbors.computeIfAbsent(to, v -> new ArrayList<>()).add(from);
            edges.add(new SimpleEntry<>(from, to));
        }

        lists.add(new Data(1, 0, 0));
        while (!countsQueue.isEmpty()) {
            SimpleEntry<Integer, Integer> top = countsQueue.poll();
            int id = top.getKey();
            int count = top.getValue();
            while (count > lists.size()) {
                SimpleEntry<Integer, Integer> minutesTop = minutesQueue.poll();
                lists.add(new Data(minutesTop.getKey(), minutesTop.getValue(), lists.size()));
            }
            Data last = lists.get(lists.size() - 1);
            if (count > last.counts) {
                lists.add(new Data(id, last.minutes + 1, count));
            } else {
                lists.add(new Data(id, last.minutes, count));
            }
        }

        while (!minutesQueue.isEmpty()) {
            SimpleEntry<Integer, Integer> minutesTop = minutesQueue.poll();
            lists.add(new Data(minutesTop.getKey(), minutesTop.getValue(), lists.size()));
        }

        distance.put(1, 0);
        for (int i = 1; i < lists.size(); i++) {
            Data current = lists.get(i);
            for (int neighbor : neighbors.get(current.id)) {
                if (!distance.containsKey(neighbor)) {
                    continue;
                }
                if (distance.get(neighbor) == current.minutes) {
                    continue;
                }

                int delta = current.minutes - distance.get(neighbor);
                assert delta > 0;
                lengths.put(new SimpleEntry<>(neighbor, current.id), delta);
                lengths.put(new SimpleEntry<>(current.id, neighbor), delta);
                distance.put(current.id, current.minutes);
                break;
            }
        }

        List<String> result = new ArrayList<>();
        for (SimpleEntry<Integer, Integer> edge : edges) {
            result.add(String.valueOf(lengths.getOrDefault(edge, MAX)));
        }

        pw.println(String.join(" ", result));
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
