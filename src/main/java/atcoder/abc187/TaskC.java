package atcoder.abc187;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Set<String> contains = new HashSet<>();
        String result = "";
        for (int i = 0; i < n; i++) {
            String str = in.next();
            if (!result.isEmpty()) {
                continue;
            }
            if (str.startsWith("!")) {
                String target = str.substring(1);
                if (contains.contains(target)) {
                    result = target;
                }
            } else {
                String target = "!" + str;
                if (contains.contains(target)) {
                    result = str;
                }
            }
            contains.add(str);
        }
        out.println(result.isEmpty() ? "satisfiable" : result);

    }
}
