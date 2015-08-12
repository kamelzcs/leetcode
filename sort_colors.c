/*
 * sort_colors.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */


void swap(int *a, int *b){
  int temp = *a;
  *a = *b;
  *b = temp;
}

void sortColors(int A[], int n) {
  int zero_index = 0, two_index = n - 1;
  int index = 0;
  while(index <= two_index){
    if(A[index] == 0){
      swap(A + index, A + zero_index);
      zero_index++;
      index++;
    }
    else if(A[index] == 1){
      index++;
    }
    else{
      swap(A + index, A + two_index);
      two_index--;
    }
  }
}


