#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    # @return a list of tree node
    def dfs(self, n):
        if n in self.cache:
            return self.cache[n]
        ans = 0
        for index in range(n):
            left, right = index, n - 1 - index
            ans += self.dfs(left) * self.dfs(right)
        self.cache[n] = ans
        return ans

    def numTrees(self, n):
        if not n:
            return 0
        self.cache = {}
        self.cache[0] = 1
        return self.dfs(n)

print Solution().numTrees(3)
