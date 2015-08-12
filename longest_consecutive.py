#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param num, a list of integer
    # @return an integer
    def longestConsecutive(self, num):
        ans = 0
        if not num:
            return ans
        left, right, cache = {}, {}, set()
        for item in num:
            if item in cache:
                continue
            cache.add(item)
            small, big = item - 1, item + 1
            smallest, biggest = left.get(small, item), right.get(big, item)
            right[smallest] = biggest
            left[biggest] = smallest
            ans = max(ans, biggest - smallest + 1)
        return ans
