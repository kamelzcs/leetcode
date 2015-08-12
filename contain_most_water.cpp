/*
 * contain_most_water.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<vector>
#include<algorithm>
using namespace std;

class Solution {
public:
    int maxArea(vector<int> &height) {
        if(height.empty()){
            return 0;
        }
        int left = 0, right = height.size() - 1, ans = 0;
        while(left < right){
            ans = max(ans, (right - left + 1) * min(height[left], height[right]));
            if(height[left] > height[right]){
                right--;
            }
            else{
                left++;
            }
        }
        return ans;
    }
};

int main(int argc, char *argv[])
{
    
    return 0;
}

