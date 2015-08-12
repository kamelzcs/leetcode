#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param num, a list of integer
    # @return nothing (void), do not return anything, modify num in-place instead.
    def nextPermutation(self, num):
        ascending_index, current = -1, 1
        while current < len(num):
            if num[current] > num[current - 1]:
                ascending_index = current - 1
            current += 1

        if ascending_index == -1:
            num.sort()
        else:
            for index in range(len(num) - 1, 0, -1):
                if num[index] > num[ascending_index]:
                    num[ascending_index], num[index] = num[index], num[ascending_index]
                    break

            num[ascending_index + 1:] = reversed(num[ascending_index + 1:])

data = [1, 3, 2]
Solution().nextPermutation(data)
print data
