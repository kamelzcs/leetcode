/*
 * search_insert_position.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

int searchInsert(int A[], int n, int target) {
  int left = 0, right = n - 1;
  while(left <= right){
    int middle = left + (right - left) / 2;
    if(A[middle] < target){
      left = middle + 1;
    }
    else{
      right = middle - 1;
    }
  }
  return left;
}


