/*
 * search_rotated.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

int search(int A[], int n, int target) {
  int left = 0, right = n - 1;
  while(left <= right){
    int middle = left + ((right - left) >> 1);
    if(A[middle] == target){
      return middle;
    }
    int same = !((A[left] <= A[(left + middle) / 2]) ^ (A[middle] >= A[(left + middle) / 2]));
    if(same){
      if((target >= A[left] && target <= A[middle]) || (target >= A[middle] && target <= A[left])){
        right = middle - 1;
      }
      else{
        left = middle + 1;
      }
    }
    else{
      if((target >= A[right] && target <= A[middle]) || (target >= A[middle] && target <= A[right])){
        left = middle + 1;
      }
      else{
        right = middle - 1;
      }
    }
  }
  return -1;
}
