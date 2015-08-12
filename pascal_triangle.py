#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @return a list of lists of integers
    def dfs(self, lastRow, numRows):
        if not numRows:
            return
        part = []
        pre = 0
        for element in lastRow:
            part.append(element + pre)
            pre = element
        part.append(pre)
        self.ans.append(part)
        self.dfs(part, numRows - 1)

    def generate(self, numRows):
        if not numRows:
            return []
        if numRows == 1:
            return [[1]]
        self.ans = [[1]]
        self.dfs([1], numRows - 1)
        return self.ans
