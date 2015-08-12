/*
 * strStr.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<stdio.h>

int strStr2(char *haystack, char *needle) {
  int index = 0;
  char *current = haystack;
  if(!(*haystack) && !(*needle)){
    return 0;
  }
  while(*current){
    char *first = current, *second = needle;
    while((*first) && (*second) && (*first) == (*second)){
      first++;
      second++;
    }
    if(!(*second)){
      return index;
    }
    if(!(*first)){
      return -1;
    }
    index++;
    current++;
  }
  return -1;
}

int find(int index, char *haystack, char *needle){
  if(!(*haystack)){
    if(!(*needle)){
      return 0;
    }
    return -1;
  }
  char *first = haystack, *second = needle;
  while(*second){
    if(!(*first)){
      return -1;
    }
    if((*first) != (*second)){
      return find(index + 1, haystack + 1, needle);
    }
    first++;
    second++;
  }
  return index;
}

int strStr(char *haystack, char *needle) {
  return find(0, haystack, needle);
}

int main(){
  printf("%d\n", strStr("", ""));
  return 0;
}
