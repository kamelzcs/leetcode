package tc.srm800;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PoisonedSwamp {
    static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public String cross(String[] swamp) {
        int rows = swamp.length;
        int cols = swamp[0].length();
        int[][] cost = new int[rows][cols];
        int INF = Integer.MAX_VALUE / 10;
        for (int[] d : cost) {
            Arrays.fill(d, INF);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        cost[0][0] = 0;
        pq.add(new Node(0, 0, 0));
        while (!pq.isEmpty()) {
            Node top = pq.poll();
            if (top.x == rows - 1 && top.y == cols - 1) {
                return "possible";
            }
        }

        return "possible";
    }
}
