class Solution:
    # @param height, a list of integer
    # @return an integer
    def largestRectangleArea(self, height):
        ans = 0
        if not height:
            return ans
        height.append(-1)
        height.insert(0, -1)
        stk = [0]
        for index in range(1, len(height)):
            h = height[index]
            pre_index = stk[-1]
            pre_h = height[pre_index]
            if h >= pre_h:
                stk.append(index)
            else:
                while h < pre_h:
                    ans = max(ans, pre_h * (index - stk[-2] - 1))
                    stk.pop()
                    pre_h = height[stk[-1]]
                stk.append(index)
        height.pop(0)
        height.pop()
        return ans

    # @param matrix, a list of lists of 1 length string
    # @return an integer
    def maximalRectangle(self, matrix):
        ans = 0
        if not matrix:
            return ans
        matrix = [map(lambda x: ord(x) - ord('0'), row) for row in matrix]
        ans = self.largestRectangleArea(matrix[0])
        for row_index in range(1, len(matrix)):
            for col in range(len(matrix[row_index])):
                if matrix[row_index][col]:
                    matrix[row_index][col] += matrix[row_index - 1][col]
            ans = max(ans, self.largestRectangleArea(matrix[row_index]))
        return ans

print Solution().maximalRectangle([["1", "2"], ["3", "4"]])
