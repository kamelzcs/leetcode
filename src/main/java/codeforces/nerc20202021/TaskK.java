package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskK {
    class Data {
        int x;
        int y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    int[][] dxy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Map<Character, Integer> map = new HashMap<>();
    static {
        map.put('U', 0);
        map.put('R', 1);
        map.put('D', 2);
        map.put('L', 3);
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x = 0;
        int y = 0;
        String str = in.next();
        List<Data> candidates = getCandidates(str);
        boolean found = false;
        for (Data candidate : candidates) {
            if (candidate.x == 0 && candidate.y == 0) {
                continue;
            }
            Data result = search(str, candidate, 0, 0, 0);
            if (result.x == 0 && result.y == 0) {
                found = true;
                out.println(candidate.x + " " + candidate.y);
                break;
            }
        }
        if (!found) {
            out.println(0 + " " + 0);
        }
    }

    private Data search(String str, Data candidate, int x, int y, int index) {
        if (index >= str.length()) {
            return new Data(x, y);
        }
        int[] d = dxy[map.get(str.charAt(index))];
        int nextX = x + d[0];
        int nextY = y + d[1];
        if (nextX == candidate.x && nextY == candidate.y) {
            return search(str, candidate, x, y, index + 1);
        } else {
            return search(str, candidate, nextX, nextY, index + 1);
        }
    }

    private List<Data> getCandidates(String str) {
        int x = 0;
        int y = 0;
        List<Data> result = new ArrayList<>();
        for (char c : str.toCharArray()) {
            int id = map.get(c);
            x = x + dxy[id][0];
            y = y + dxy[id][1];
            result.add(new Data(x, y));
        }
        return result;
    }
}
