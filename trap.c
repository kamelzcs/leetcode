/*
 * trap.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

int max(int a, int b){
  return a > b ? a : b;
}

int trap(int A[], int n) {
  if(!A){
    return 0;
  }
  int left = 0, right = n - 1;
  int left_max = A[0], right_max = A[n - 1];
  int ans = 0;
  while(left <= right){
    left_max = max(left_max, A[left]);
    right_max = max(right_max, A[right]);
    if(left_max < right_max){
      ans += left_max - A[left];
      left++;
    }
    else{
      ans += right_max - A[right];
      right--;
    }
  }
  return ans;
}

int main(){
  return 0;
}


