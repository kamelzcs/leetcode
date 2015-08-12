#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    def intToRoman(self, num):
        table = {1000: "M",
                900: "CM",
                500: "D",
                400: "CD",
                100: "C",
                90: "XC",
                50: "L",
                40: "XL",
                10: "X",
                9: "IX",
                5: "V",
                4: "IV",
                1: "I"}
        ans = ""
        for key in sorted(table.keys(), reverse=True):
            if num / int(key):
                ans += table[key] * (num / int(key))
                num %= int(key)
        return ans

print Solution().intToRoman(32)
