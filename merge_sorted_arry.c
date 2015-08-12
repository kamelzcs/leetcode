/*
 * merge_sorted_arry.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */


void merge(int A[], int m, int B[], int n) {
  int last_pos = m + n - 1, last_a = m - 1, last_b = n - 1;
  while(1){
    if(last_b < 0){
      break;
    }
    if(last_a < 0){
      while(last_b >= 0){
        A[last_pos--] = B[last_b--];
      }
      break;
    }
    if(A[last_a] >= B[last_b]){
      A[last_pos--] = A[last_a--];
    }
    else{
      A[last_pos--] = B[last_b--];
    }
  }
}

