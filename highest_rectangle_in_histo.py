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
        return ans

print Solution().largestRectangleArea([2,1,5,6,2,3])
