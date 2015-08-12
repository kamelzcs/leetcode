#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.

from collections import deque


class Solution:
    # @param A, a list of integers
    # @return an integer
    def trap(self, A):
        ans = 0
        if not A:
            return ans
        decrease_que = deque([(0, A[0])])
        for index in range(1, len(A)):
            lst_index, lst_height = decrease_que[-1]
            pre_height = lst_height
            if lst_height < A[index]:
                while True:
                    if lst_height >= A[index]:
                        ans += (A[index] - pre_height) * (index - lst_index - 1)
                        break
                    else:
                        ans += (lst_height - pre_height) * (index - lst_index - 1)
                    decrease_que.pop()
                    if not decrease_que:
                        break
                    pre_height = lst_height
                    lst_index, lst_height = decrease_que[-1]

            decrease_que.append((index, A[index]))
        return ans

print Solution().trap([0,1,0,2,1,0,1,3,2,1,2,1])
print Solution().trap([1,1])
print Solution().trap([4, 2, 3])
print Solution().trap([4,2,0,3,2,5])
