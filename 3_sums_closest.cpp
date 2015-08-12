/*
 * 3_sums_closest.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<vector>
#include<algorithm>
#include<iostream>
using namespace std;


class Solution {
public:
    vector<int> data;
    int NINF;
    bool nearer(int a, int b, int target){
        return abs(target - a) < abs(target - b);
    }
    int threeSumClosest(vector<int> &num, int target) {
        sort(num.begin(), num.end());
        NINF = -10000010;
        int ans = NINF;
        data = num;
        int len_num = num.size();
        for(int i = 0; i < len_num; ++i){
            int pivot = num[i];
            int target2 = target - pivot;
            int left = i + 1, right = len_num - 1;
            while(left < right){
                int temp = data[left] + data[right];
                if(nearer(pivot + temp, ans, target)){
                    ans = pivot + temp;
                }
                if(temp > target2){
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        return ans;
    }
};

int main(int argc, char *argv[])
{
    vector<int> data = {-4, -1, 1,2};
    Solution *t = new Solution();
    cout << t->threeSumClosest(data, 1);
    return 0;
}
