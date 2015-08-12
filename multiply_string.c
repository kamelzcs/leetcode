/*
 * multiply_string.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<stdio.h>
#include<string.h>
#include<memory.h>

void reverse_string(char *str)
{
    /* skip null */
    if (str == 0)
    {
    	return;
    }

    /* skip empty string */
    if (*str == 0)
    {
    	return;
    }

    /* get range */
    char *start = str;
    char *end = start + strlen(str) - 1; /* -1 for \0 */
    char temp;

    /* reverse */
    while (end > start)
    {
    	/* swap */
    	temp = *start;
    	*start = *end;
    	*end = temp;

    	/* move */
    	++start;
    	--end;
    }
}

char* multiply(char* num1, char* num2) {
  int len_1 = strlen(num1), len_2 = strlen(num2);
  reverse_string(num1);
  reverse_string(num2);
  char *ans = malloc(sizeof(char) * (len_1 + len_2 + 1));
  memset(ans, 0, sizeof(char) * (len_1 + len_2 + 1));
  int i, j;
  int carry;
  for(i = 0; i < len_1; ++i){
    carry = 0;
    for(j = 0; j < len_2; ++j){
      int total = ans[i + j] + (num1[i] - '0') * (num2[j] - '0') + carry;
      ans[i + j] = total % 10;
      carry = total / 10;
    }
    if(carry){
      ans[i + len_2] = carry;
    }
  }

  for(i = 0; i < len_1 + len_2 - 1; ++i){
    ans[i] += '0';
  }

  if(carry){
    ans[len_1 + len_2 - 1] = carry + '0';
  }
  else{
    for(i = len_1 + len_2 - 2; i >= 1; --i){
      if(ans[i] != '0'){
        break;
      }
      ans[i] = 0;
    }
  }

  reverse_string(ans);
  return ans;
}

int main(){
  char a[] = "0", b[] = "99";
  printf("%s\n", multiply(a, b));
  return 0;
}


