package common;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TemplateTest {

    @Test
    public void replace() {
        Assert.assertEquals("aabccabc", Template.replace("a{A}c{A}", ImmutableMap.of("A", "abc")));
        Assert.assertEquals("aabccbcd", Template.replace("a{A}c{ABC}", ImmutableMap.of("A", "abc", "ABC", "bcd")));
        Assert.assertEquals("aaca", Template.replace("a{A}c{{ABC}}", ImmutableMap.of("A", "a", "ABC", "A")));
    }

    @Test
    public void representation() {
        for (int i = 2; i <= 10; i++) {
            for (int j = i; j <= 10; j++) {
                System.out.println(String.format("%d %d %s", i, j, func(i, j)));
            }
        }
    }

    private List<Integer> func(int a, int b) {
        int len = b - a + 1;
        boolean[] canDo = new boolean[b * (b + 1) / 2 + 1];
        for (int mask = 0; mask < (1<<len); mask++) {
            int sum = 0;
            for (int j = 0; j < len; j++) {
                if ((mask & (1<<j)) > 0) {
                    sum += (j + a);
                } else {
                    sum -= (j + a);
                }
            }
            if (sum >= 0) {
                canDo[sum] = true;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < canDo.length; i++) {
            if (canDo[i]) {
                result.add(i);
            }
        }
        return result;
    }
    private List<Integer> func(int n) {
        boolean[] canDo = new boolean[n * (n + 1) / 2 + 1];
        for (int mask = 0; mask < (1<<n); mask++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((mask & (1<<j)) > 0) {
                    sum += (1 + j);
                } else {
                    sum -= (1 + j);
                }
            }
            if (sum >= 0) {
                canDo[sum] = true;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < canDo.length; i++) {
            if (canDo[i]) {
                result.add(i);
            }
        }
        return result;
    }
}