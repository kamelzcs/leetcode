package common;

import java.util.Map;
import java.util.Optional;

public class Template {
    static final char left = '{';
    static final char right = '}';
    public static String replace(final String input, final Map<String, String> config) {
        String result = "";
        int index = 0;
        final int currentCount = 0;
        while (index < input.length()) {
            char current = input.charAt(index);
            if (current == left || current == right) {
                if (current == right) {
                    throw new IllegalArgumentException("More } on index" + index);
                } else {
                    final int match =  findMatch(input, currentCount + 1, index + 1);
                    if (match == -1) {
                        throw new IllegalArgumentException("More { on index" + index);
                    } else {
                        final String inner = input.substring(index + 1, match);
                        final String translate = replace(inner, config);
                        int finalIndex = index;
                        result += Optional.ofNullable(config.get(translate)).orElseThrow(()
                                -> new IllegalArgumentException(String.format("could not find key of %s from %d to %d", translate, finalIndex, match)));
                        index = match + 1;
                    }
                }

            } else {
                result += current;
                index++;
            }
        }
        return result;
    }

    private static int findMatch(String input, int count, int index) {
        int currentCount = count;
        int currentIndex = index;
        while (currentIndex < input.length()) {
            final char currentChar = input.charAt(currentIndex);
            if (left == currentChar) {
                currentCount++;
            } else if (right == currentChar) {
                currentCount--;
                if (currentCount < 0) {
                    return -1;
                }
                if (currentCount == count - 1) {
                    return currentIndex;
                }
            }
            currentIndex++;
        }
        return -1;
    }
}
