//Given an encoded string, return its decoded string.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the 
//square brackets is being repeated exactly k times. Note that k is guaranteed to 
//be a positive integer. 
//
// You may assume that the input string is always valid; No extra white spaces, 
//square brackets are well-formed, etc. 
//
// Furthermore, you may assume that the original data does not contain any digit
//s and that digits are only for those repeat numbers, k. For example, there won't
// be input like 3a or 2[4]. 
//
// Examples: 
//
// 
//s = "3[a]2[bc]", return "aaabcbc".
//s = "3[a2[c]]", return "accaccacc".
//s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
// 
//
// 
// Related Topics Stack Depth-first Search


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DecodeStringV2 {
    public static void main(String[] args) {
        Solution solution = new DecodeStringV2().new Solution();
//        System.out.println(solution.decodeString("3[a]2[bc]"));
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    enum TokenName {
        NUMBER, STRING, LEFTBRACKET, RIGHTBRACKET
    }

    class Token {
        TokenName name;
        Object value;
        int start;
        int length;

        Token(TokenName name, Object value, int start, int length) {
            this.name = name;
            this.value = value;
            this.start = start;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    ", start=" + start +
                    ", length=" + length +
                    '}';
        }
    }

    class TokenParser {
        TokenName name;
        String regex;
        Function<String, Object> converter;

        public TokenParser(TokenName name, String regex, Function<String, Object> converter) {
            this.name = name;
            this.regex = regex;
            this.converter = converter;
        }
    }

    class Tokenizer {
        Map<TokenName, Function<String, Object>> parserMap;
        String regex;

        Tokenizer(List<TokenParser> tokenParsers) {
            this.parserMap = tokenParsers.stream().collect(Collectors.toMap(tokenParser -> tokenParser.name, tokenParser -> tokenParser.converter));
            regex = tokenParsers.stream().map(tokenParser -> String.format("(?<%s>%s)", tokenParser.name.name(), tokenParser.regex)).collect(Collectors.joining("|"));
        }

        List<Token> tokenize(String input) {
            List<Token> result = new ArrayList<>();
            Matcher matcher = Pattern.compile(this.regex).matcher(input);
            Matcher spaceConsumer = Pattern.compile("\\s+").matcher(input);
            int pos = 0;
            while (pos < input.length()) {
                // consume space
                if(spaceConsumer.find(pos)) {
                    pos = spaceConsumer.end();
                }

                if (pos >= input.length()) {
                    break;
                }

                if (!matcher.find(pos)) {
                    throw new IllegalArgumentException(String.format("Invalid input[%s] at pos[%d]", input, pos));
                }

                List<TokenName> names = parserMap.entrySet().stream().filter(kv -> matcher.group(kv.getKey().name()) != null).map(kv -> kv.getKey()).collect(Collectors.toList());
                if (names.size() != 1) {
                    throw new IllegalArgumentException(String.format("Invalid input[%s] at pos[%d] with captured groups[%s]", input, pos, names));
                }
                TokenName name = names.get(0);
                String part = matcher.group(name.name());
                result.add(new Token(name, parserMap.get(name).apply(part), pos, part.length()));
                pos += part.length();
            }
            return result;
        }
    }

    class Solution {
        String s;
        Tokenizer tokenizer;
        int index;
        List<Token> tokens;

        public String decodeString(String s) {
            this.s = s;
            tokenizer = new Tokenizer(Arrays.asList(new TokenParser(TokenName.NUMBER, "\\d+", Integer::valueOf),
                    new TokenParser(TokenName.LEFTBRACKET, "\\[", String::valueOf),
                    new TokenParser(TokenName.RIGHTBRACKET, "\\]", String::valueOf),
                    new TokenParser(TokenName.STRING, "[a-zA-Z]+", String::valueOf))
            );
            index = 0;
            tokens = this.tokenizer.tokenize(this.s);
            System.out.println(tokens);
            return decode();
        }

        private String decode() {
            StringBuilder stringBuilder = new StringBuilder();
            while (index < tokens.size()) {
                Optional<String> parsed = parseEncoded();
//                System.out.println(String.format("%d %s", index, parsed));
                if (!parsed.isPresent()) {
                    break;
                }
                stringBuilder.append(parsed.get());
            }
            return stringBuilder.toString();
        }

        private Optional<String> parseEncoded() {
            Token currentToken = tokens.get(index);
            if (currentToken.name == TokenName.STRING) {
                index++;
                return Optional.of((String) currentToken.value);
            } else if (currentToken.name == TokenName.NUMBER) {
                int count = (int) currentToken.value;
                index++;
                assert tokens.get(index).name == TokenName.LEFTBRACKET;

                index++;
                String decoded = decode();

                assert tokens.get(index).name == TokenName.RIGHTBRACKET;

                index++;
                return Optional.of(occurs(decoded, count));
            } else {
                return Optional.empty();
            }
        }

        private String occurs(String decoded, int count) {
            return String.join("", Collections.nCopies(count, decoded));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}