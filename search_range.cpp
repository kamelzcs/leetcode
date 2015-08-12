/*
 * search_range.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<vector>
using namespace std;

class Solution {
public:
    vector<int> searchRange(int A[], int n, int target) {
        vector<int> ans;
        int left = 0, right = n - 1;
        while(left <= right){
            int middle = left + (right - left) / 2;
            if(A[middle] >= target){
                right = middle - 1;
            }
            else{
                left = middle + 1;
            }
        }
        if(A[left] != target){
            return vector<int>{-1, -1};
        }
        ans.push_back(left);
        right = n - 1;
        while(left <= right){
            int middle = left + (right - left) / 2;
            if(A[middle] > target){
                right = middle - 1;
            }
            else{
                left = middle + 1;
            }
        }
        ans.push_back(right);
        return ans;
    }
};


