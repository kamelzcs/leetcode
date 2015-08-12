#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


from collections import Counter


class Solution:
    # @param S, a string
    # @param L, a list of string
    # @return a list of integer
    def findSubstring(self, S, L):
        ans = []
        total_num, full_counter, word_len, s_len = len(L), Counter(L), len(L[0]), len(S)
        for start in range(word_len):
            left, right = start, start
            current_num = 0
            current_counter = Counter()
            while right < s_len:
                next_word = S[right: right + word_len]
                if next_word not in full_counter:
                    current_num = 0
                    current_counter.clear()
                    left = right + word_len
                else:
                    current_counter[next_word] += 1
                    current_num += 1
                    if current_counter[next_word] > full_counter[next_word]:
                        pop_word = S[left: left + word_len]
                        while pop_word != next_word:
                            current_counter[pop_word] -= 1
                            current_num -= 1
                            left += word_len
                            pop_word = S[left: left + word_len]
                        current_counter[pop_word] -= 1
                        current_num -= 1
                        left += word_len
                    else:
                        if(current_num == total_num):
                            ans.append(left)
                            pop_word = S[left: left + word_len]
                            current_counter[pop_word] -= 1
                            current_num -= 1
                            left += word_len
                right += word_len
        return ans

print Solution().findSubstring("abababab", ["a", "b", "a"])
