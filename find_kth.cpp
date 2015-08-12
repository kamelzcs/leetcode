#include <vector>
#include <iostream>
using namespace std;

class Solution {
public:
    int partition(vector<int> &nums, int left, int right){
        int pivot = nums[left];
        int last = left;
        for(int i = left + 1; i <= right; ++i){
            if(nums[i] > pivot){
                last++;
                swap(nums[last], nums[i]);
            }
        }
        swap(nums[left], nums[last]);
        return last;
    }
    int findKthLargest(vector<int>& nums, int k) {
        int left = 0, right = nums.size() - 1;
        while(1){
            int pos = partition(nums, left, right);
            if(pos == k - 1){
                return nums[pos];
            }
            else if(pos > k - 1){
                right = pos - 1;
            }
            else{
                left = pos + 1;
            }
        }
    }
};

int main(){
    Solution *t = new Solution();
    vector<int> data({3, 2, 3, 1, 2, 4, 5, 5, 6});
    cout<<t->findKthLargest(data, 4);
    return 0;
}
