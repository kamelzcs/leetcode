#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @return a list of lists of integers
    def dfs(self, rowIndex):
        lastRow, currentRow = self.data[(rowIndex + 1) % 2], self.data[rowIndex % 2]
        if not rowIndex:
            return lastRow
        del currentRow[::]
        pre = 0
        for element in lastRow:
            currentRow.append(element + pre)
            pre = element
        currentRow.append(pre)
        return self.dfs(rowIndex - 1)

    def getRow(self, rowIndex):
        rowIndex += 1
        self.data = [[] for index in range(2)]
        self.data[rowIndex % 2].append(1)
        return self.dfs(rowIndex - 1)
