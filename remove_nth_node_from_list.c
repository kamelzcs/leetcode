/*
 * remove_nth_node_from_list.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

struct ListNode {
  int val;
  struct ListNode *next;
};

struct ListNode *removeNthFromEnd(struct ListNode *head, int n) {
    struct ListNode **pre = &head, *ahead = head;
    while(n){
        ahead = ahead->next;
        n--;
    }
    while(ahead){
        pre = &(*pre)->next;
        ahead = ahead->next;
    }
    (*pre)= (*pre)->next;
    return head;
}


