#! /usr/bin/env python


class Interval:
 def __init__(self, s=0, e=0):
     self.start = s
     self.end = e


class Solution:
    # @param intervals, a list of Interval
    # @return a list of Interval
    def merge(self, intervals):
        ans = []
        if not intervals:
            return ans

        intervals.sort(key=lambda interval: interval.start)
        left, right = intervals[0].start, intervals[0].start - 1
        for item in intervals:
            if item.start > right:
                ans.append(Interval(left, right))
                left, right = item.start, item.end
            else:
                right = max(right, item.end)
        ans.append(Interval(left, right))
        return ans[1:]
