package atcoder.arc110;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskD {
    long MOD = 1_000_000_007L;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += in.nextLong();
        }

        out.println(combine(m + n, n + sum));
    }

    <T> List<T> filter(Collection<T> t, Predicate<T> p) {
        return t.stream().filter(p).collect(Collectors.toList());
    }

    private long combine(long a, long b) {
        long result = 1;
        for (long i = a - b + 1; i <= a; i++) {
            result = (result * i) % MOD;
        }

        long dividen = 1;
        for (long i =2 ; i <= b; i++) {
            dividen = (dividen * i) % MOD;
        }
        return (result * inverse(dividen)) % MOD;
    }

    private long inverse(long dividen) {
        return power(dividen, MOD - 2);
    }

    private long power(long v, long m) {
        if (m == 0) {
            return 1;
        }

        long part = power(v, m / 2);
        long mul = (m % 2 == 1) ? v : 1;
        long result = ((part * part) % MOD * mul) % MOD;
        return result;
    }
}
