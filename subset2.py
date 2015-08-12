#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @param num, a list of integer
    # @return a list of lists of integer
    def dfs(self, S):
        if not S:
            return [[]]
        S.sort()
        first = S[0]
        remain = self.subsetsWithDup(S[1:])
        ans = []
        for index, item in enumerate(remain):

        for each in remain:
            ans.append(each)
            ans.append([first] + each)
        ans = [list(x) for x in set(tuple(x) for x in ans)]
        return ans

    def subsetsWithDup(self, S):
        S.sort()
        return self.dfs(S)

print Solution().subsetsWithDup([1,2,2])
