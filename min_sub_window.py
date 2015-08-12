#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


from collections import Counter

class Solution:
    # @return a string
    def minWindow(self, S, T):
        counter = Counter(T)
        current_counter = Counter()
        total_num = len(counter.keys())
        len_s = len(S)
        current_num = 0
        left, right = 0, 0
        ans_left, ans_right, ans_len = 0, len_s, len_s + 1
        while right < len_s:
            item = S[right]
            if counter[item]:
                current_counter[item] += 1
                if current_counter[item] == counter[item]:
                    current_num += 1
                if current_num >= total_num:
                    del_item = S[left]
                    while True:
                        if counter[del_item]:
                            if current_counter[del_item] == counter[del_item]:
                                break
                            else:
                                if current_counter[del_item] == counter[del_item]:
                                    current_num -= 1
                                current_counter[del_item] -= 1
                        left += 1
                        del_item = S[left]
                    if(right - left + 1 < ans_len):
                        ans_len = right - left + 1
                        ans_left, ans_right = left, right
            right += 1
        return "" if ans_len > len_s else S[ans_left: ans_right + 1]

print Solution().minWindow("A", "AA")
print Solution().minWindow("A", "A")
print Solution().minWindow("ABAADCDBB", "DDB")
