#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @return a tuple, (index1, index2)
    def twoSum(self, num, target):
        num = list(enumerate(num))
        num = sorted(num, key=lambda x: x[1])
        left, right = 0, len(num) - 1
        while left < right:
            while left < right and num[left][1] + num[right][1] > target:
                right -= 1
            if num[left][1] + num[right][1] == target:
                return tuple(sorted((num[left][0] + 1, num[right][0] + 1)))
            left += 1

print Solution().twoSum([50, 25, 75], 100)
