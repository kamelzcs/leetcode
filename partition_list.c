/*
 * partition_list.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode *partition(struct ListNode *head, int x) {
  struct ListNode *small = (struct ListNode *)malloc(sizeof(struct ListNode));
  struct ListNode *big = (struct ListNode *)malloc(sizeof(struct ListNode));
  small->next = 0;
  big->next = 0;
  struct ListNode *small_tail = small, *big_tail = big;
  while(head){
    int data = head->val;
    if(data < x){
      small_tail->next = head;
      small_tail = small_tail->next;
    }
    else{
      big_tail->next = head;
      big_tail = big_tail->next;
    }
    head = head->next;
  }
  big_tail->next = 0;
  small_tail->next = big->next;
  return small->next;
}
