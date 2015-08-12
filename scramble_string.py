#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.


class Solution:
    # @return a boolean
    def isScramble(self, s1, s2):
        if s1 == s2:
            return True
        for len1 in range(1, len(s1)):
            if sorted(s1[:len1]) == sorted(s2[:len1]):
                if self.isScramble(s1[:len1], s2[:len1]) and self.isScramble(s1[len1:], s2[len1:]):
                    return True
            elif sorted(s1[:len1]) == sorted(s2[len(s1) - len1:]):
                if self.isScramble(s1[:len1], s2[len(s1) - len1:]) and self.isScramble(s1[len1:], s2[:len(s1) - len1]):
                    return True
        return False
