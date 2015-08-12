/*
 * long_substring_without_repeating.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<algorithm>
#include<string>
#include<set>
using namespace std;
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        set<char> existed;
        int left, right;
        left = right = 0;
        int ans = 0;
        int len_s = s.size();
        while(right < len_s){
            char current = s[right];
            if(existed.find(current) != existed.end()){
                do{
                    existed.erase(s[left]);
                }while(s[left++] != current);
            }
            existed.insert(current);
            ans = max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
};
int main(int argc, char *argv[])
{
    return 0;
}
