#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param triangle, a list of lists of integers
    # @return an integer
    def minimumTotal(self, triangle):
        self.data = [[] for i in range(2)]
        self.data[0].append(triangle[0][0])
        for index in range(1, len(triangle)):
            lastRow, currentRow = self.data[(index + 1) % 2], self.data[index % 2]
            del currentRow[:]
            currentRow.append(triangle[index][0] + lastRow[0])
            for j in range(1, len(triangle[index]) - 1):
                currentRow.append(min(lastRow[j - 1], lastRow[j]) + triangle[index][j])
            currentRow.append(triangle[index][-1] + lastRow[-1])
        ans = 10000000
        for item in self.data[(len(triangle) + 1) % 2]:
            ans = min(ans, item)
        return ans

print Solution().minimumTotal([[1], [2, 3]])
