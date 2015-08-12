#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param ratings, a list of integer
    # @return an integer
    def calSum(self, x):
        return x * (x + 1) / 2

    def dfs(self, start):
        ans = 0
        while True:
            if start == len(self.ratings) - 1:
                ans += 1
                return ans
            index = start + 1
            while index < len(self.ratings):
                if self.ratings[index] < self.ratings[index - 1]:
                    break
                if self.ratings[index] == self.ratings[index - 1]:
                    ans += self.calSum(index - start)
                    break
                index += 1
            if index < len(self.ratings) and self.ratings[index] == self.ratings[index - 1]:
                start = index
                continue
            leftNum = index - 1 - start
            while index < len(self.ratings):
                if self.ratings[index] >= self.ratings[index - 1]:
                    break
                index += 1
            rightNum = index - leftNum - 1 - start
            ans += self.calSum(leftNum) + self.calSum(rightNum)
            ans += max(leftNum, rightNum)
            start = index - 1

    def candy(self, ratings):
        if not ratings:
            return 0
        self.ratings = ratings
        return self.dfs(0)

print Solution().candy([1,1,2,3,4,4,5])
print Solution().candy([4,2,3,4,1])
