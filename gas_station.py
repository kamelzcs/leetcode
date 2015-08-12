#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.

import operator

class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        if not gas:
            return -1
        delta = map(operator.sub, gas, cost)
        pre, ans_sum, ans_index = delta[0], delta[0], 0
        for index in range(1, len(delta)):
            pre = pre + delta[index]
            if pre < ans_sum:
                ans_sum = pre
                ans_index = index
        return (ans_index + 1) % len(gas) if pre >= 0 else -1

print Solution().canCompleteCircuit([4, 2], [5, 1])
