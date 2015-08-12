#include <algorithm>
#include <vector>
using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if(prices.empty()){
            return 0;
        }
        int ans = 0, max_delta = -prices[0];
        for(int i = 1; i < prices.si(); ++i){
            ans = max(ans, max_delta + prices[i]);
            max_delta = max(max_delta, ans - prices[i]);
        }
        return ans;
    }
};
