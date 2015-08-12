#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.

class Solution:
    # @param s, a string
    # @return an integer

    def longestValidParentheses(self, s):
        ans, current = 0, 0
        pre, pre_counter = 0, 0
        while current < len(s):
            if s[current] == '(':
                pre_counter += 1
            else:
                if not pre_counter:
                    pre, pre_counter = 0, 0
                else:
                    pre += 1
                    pre_counter -= 1
                    ans = max(ans, pre)
            current += 1
        return ans * 2

print Solution().longestValidParentheses(")(())")
