#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param root, a tree node
    # @return a list of integers
    def inorderTraversal(self, root):
        ans, stack = [], []
        if not root:
            return ans
        current = root
        while stack or current:
            if current:
                stack.append(current)
                current = current.left
            else:
                top = stack.pop()
                ans.append(top.val)
                current = top.right
        return ans
