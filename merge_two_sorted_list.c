/*
 * merge_two_sorted_list.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<memory.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode *mergeTwoLists(struct ListNode *l1, struct ListNode *l2) {
  struct ListNode *head = (struct ListNode *)malloc(sizeof(struct ListNode)), *tail = head;
  while(l1 || l2){
    if(!l1){
      tail->next = l2;
      break;
    }
    if(!l2){
      tail->next = l1;
      break;
    }
    if(l1->val < l2->val){
      tail->next = l1;
      l1 = l1->next;
    }
    else{
      tail->next = l2;
      l2 = l2->next;
    }
    tail = tail->next;
  }
  return head->next;
}


