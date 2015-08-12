/*
 * remove_duplicates_sorted_list.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

struct ListNode {
    int val;
    struct ListNode *next;
};
void dfs(int pre, struct ListNode **current){
  if(!(*current)){
    return;
  }
  int value = (*current)->val;
  if(value == pre){
    (*current) = (*current)->next;
    dfs(pre, current);
  }
  else{
    dfs(value, &((*current)->next));
  }
}
struct ListNode *deleteDuplicates(struct ListNode *head) {
  dfs(-10000, &head);
  return head;
}


