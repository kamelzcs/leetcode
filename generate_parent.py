#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param an integer
    # @return a list of string
    def generateParenthesis(self, n):
        return reduce(lambda x, y: x + y, map(lambda i: ["(" + first + ")" + second for first in self.generateParenthesis(i) for second in self.generateParenthesis(n - 1 - i)], range(n)), []) or [""]

print Solution().generateParenthesis(3)
