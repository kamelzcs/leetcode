#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param root, a tree node
    # @param sum, an integer
    # @return a list of lists of integers
    def dfs(self, root, sum, current):
        if not root:
            return

        if not root.left and not root.right:
            if root.val == sum:
                self.ans.append(current + [sum])
            return
        if root.left:
            self.dfs(root.left, sum - root.val, current + [root.val])

        if root.right:
            self.dfs(root.right, sum - root.val, current + [root.val])

    def pathSum(self, root, sum):
        current, self.ans = [], []
        self.dfs(root, sum, current)
        return self.ans
