/*
 * best_time_buby_sell.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */


int max(int a, int b){
  return a > b ? a : b;
}

int maxProfit(int prices[], int n){
  int ans = 0;
  if(!n){
    return ans;
  }
  int max_pre = 0;
  int maxDelta = -prices[0];
  int i;
  for(i = 1; i < n; ++i){
    max_pre = max(max_pre, maxDelta + prices[i]);
    maxDelta = max(maxDelta, max_pre - prices[i]);
  }
  return max_pre;
}


