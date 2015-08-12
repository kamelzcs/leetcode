#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if(prices.empty()){
            return 0;
        }
        int ans = 0;
        reverse(prices.begin(), prices.end());
        int max_v = prices[0];
        for(int p : prices){
            ans = max(ans, max_v - p);
            max_v = max(max_v, p);
        }
        return ans;
    }
};
