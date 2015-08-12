#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @return a list of integers
    def grayCode(self, n):
        if not n:
            return [n]
        if n == 1:
            return [0, 1]
        part = self.grayCode(n - 1)
        new = []
        for each in part[::-1]:
            new.append((1 << (n - 1)) + each)
        return part + new
print Solution().grayCode(3)
