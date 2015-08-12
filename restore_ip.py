#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param s, a string
    # @return a list of strings
    def dfs(self, start, remain):
        if start >= len(self.s):
            return []
        if remain == 1:
            if len(self.s) - start > 3:
                return []
            if len(self.s) - start > 1 and not self.s[start]:
                return []
            temp = 0
            temp_str = ""
            for index in range(start, len(self.s)):
                temp = temp * 10 + self.s[index]
                temp_str += str(self.s[index])
            if temp > 255:
                return []
            return [temp_str]
        current = 0
        ans = []
        current_str = ""
        for index in range(start, min(start + 3, len(self.s))):
            current = current * 10 + self.s[index]
            current_str += str(self.s[index])
            if current > 255:
                break
            part = self.dfs(index + 1, remain - 1)
            for each in part:
                if each:
                    ans.append(current_str + "." + each)
            if not current:
                break
        return ans
    def restoreIpAddresses(self, s):
        if not s:
            return []
        self.s = map(int, s)
        return self.dfs(0, 4)


print Solution().restoreIpAddresses("25525511135")
print Solution().restoreIpAddresses("010010")
