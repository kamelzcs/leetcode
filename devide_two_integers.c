/*
 * devide_two_integers.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<stdio.h>

#define MAX_INT 0x7fffffff
typedef long long LL;

LL div_bit(LL dividend, LL divisor){
  LL ans = 0;
  while((divisor << ans) <= dividend){
    ans++;
  }
  return ans - 1;
}

int divide(int divid, int divis) {
  if(divis == 0){
    return MAX_INT;
  }
  LL dividend = divid, divisor = divis;
  LL mul = ((dividend < 0) ^ (divisor < 0)) ? -1: 1;
  dividend = llabs(dividend);
  divisor = llabs(divisor);
  LL ans = 0;
  while(dividend >= divisor){
    LL bit = div_bit(dividend, divisor);
    ans += (1LL<<bit);
    dividend -= (divisor << bit);
  }
  ans *= mul;
  return ans > MAX_INT ? MAX_INT : (int)ans;
}

int main(){
  printf("%d\n", divide(-11, -1));
  printf("%d\n", divide(-2147483648, -1));
  return 0;
}


