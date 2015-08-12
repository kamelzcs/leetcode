class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<pair<int, int> > data;
        for(int i = 0; i < nums.size(); ++i){
            data.push_back(make_pair(nums[i], i + 1));
        }
        sort(data.begin(), data.end());
        int left = 0, right = data.size() - 1;
        while(left < right){
            long long tmp = data[left].first + data[right].first;
            if(tmp > target){
                right--;
            }
            else if(tmp < target){
                left++;
            }
            else{
                vector<int> ans{data[left].second, data[right].second};
                sort(ans.begin(), ans.end());
                return ans;
            }
        }
    }
};
