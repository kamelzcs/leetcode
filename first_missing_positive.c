/*
 * first_missing_positive.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<stdio.h>

void rotate(int A[], int n, int start){
  if(start <= 0 || start > n){
    return;
  }
  if(A[start - 1] == start){
    return;
  }
  int nxt = A[start - 1];
  A[start - 1] = start;
  rotate(A, n, nxt);
}

int firstMissingPositive(int A[], int n) {
  int i;
  for(i = 0; i < n; ++i){
    rotate(A, n, A[i]);
  }
  for(i = 0; i < n; ++i){
    if(A[i] != i + 1){
      return i + 1;
    }
  }
  return n + 1;
}

int main(){
  int data[] = {3, 4, -1, 1};
  printf("%d\n", firstMissingPositive(data, 4));
  return 0;
}


