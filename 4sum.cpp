/*
 * 4sum.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<algorithm>
#include<iostream>
#include<vector>

using namespace std;

class Solution {
public:
    int next(vector<int> &num, int start){
        int left = start + 1, right = num.size() - 1;
        while(left <= right){
            int middle = left + (right - left) / 2;
            if(num[middle] > num[start]){
                right = middle - 1;
            }
            else{
                left = middle + 1;
            }
        }
        return left;
    }
    vector<vector<int> > fourSum(vector<int> &num, int target) {
        vector<vector<int> > ans;
        sort(num.begin(), num.end());
        int len_num = num.size();
        for(int first = 0; first < len_num; first = next(num, first)){
            for(int second = first + 1; second < len_num; second = next(num, second)){
                int sum2 = num[first] + num[second];
                int third = second + 1, last = len_num - 1;
                while(third < last){
                    int temp = sum2 + num[third] + num[last];
                    if(temp > target){
                        last--;
                    }
                    else if(temp == target){
                        ans.push_back(vector<int> {num[first], num[second], num[third], num[last]});
                        third = next(num, third);
                    }
                    else{
                        third++;
                    }
                }
            }
        }
        return ans;
    }
};

int main(int argc, char *argv[])
{
    Solution *t = new Solution();
    vector<int> data = {1, 0, -1, 0, -2, 2};
    cout << (t->fourSum(data, 0).size());
    return 0;
}


