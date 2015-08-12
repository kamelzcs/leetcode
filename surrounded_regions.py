#! /usr/bin/env python
# -*- coding: utf-8 -*-
# vim:fenc=utf-8
#
# Copyright © 2015 zhao <zhao@kamel-Desktop>
#
# Distributed under terms of the MIT license.

import operator
from collections import deque


class Solution:
    # @param board, a 2D array
    # Capture all regions by modifying the input board in-place.
    # Do not return any value.
    def bfs(self, (x, y), origin, replace):
        if x < 0 or x >= self.m:
            return
        if y < 0 or y >= self.n:
            return
        if self.board[x][y] != origin:
            return
        self.board[x][y] = replace
        q = deque([(x, y)])
        while q:
            now_x, now_y = q.pop()
            for _ in self.dirs:
                next_x, next_y = tuple(map(operator.add, (now_x, now_y), _))
                if next_x < 0 or next_x >= self.m:
                    continue
                if next_y < 0 or next_y >= self.n:
                    continue
                if self.board[next_x][next_y] != origin:
                    continue
                self.board[next_x][next_y] = replace
                q.append((next_x, next_y))

    def solve(self, board):
        if not board:
            return
        self.replace = '@'
        self.board = board
        self.m, self.n = len(board), len(board[0])
        self.dirs = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        for col in range(self.n):
            self.bfs((0, col), 'O', self.replace)
            self.bfs((self.m - 1, col), 'O', self.replace)
        for row in range(self.m):
            self.bfs((row, 0), 'O', self.replace)
            self.bfs((row, self.n - 1), 'O', self.replace)

        for row in range(self.m):
            for col in range(self.n):
                if board[row][col] == 'O':
                    board[row][col] = 'X'

        for row in range(self.m):
            for col in range(self.n):
                if board[row][col] == self.replace:
                    board[row][col] = 'O'
#data = [['X', 'X', 'X', 'X'], ['X', 'O', 'O', 'X'], ['X', 'X', 'O', 'X'], ['X', 'O', 'X', 'X']]
data = [['O'] * 4 for i in range(4)]
Solution().solve(data)
print data
