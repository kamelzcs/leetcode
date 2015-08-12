void dfs(struct ListNode **current){
  if(!(*current) || !(*current)->next){
    return;
  }
  int current_val = (*current)->val, next_val = (*current)->next->val;
  dfs(&(*current)->next);
  if(current_val == next_val){
    (*current) = (*current)->next;
    if((*current) && (*current)->val == current_val){
      (*current) = (*current)->next;
    }
  }
}
struct ListNode *deleteDuplicates(struct ListNode *head) {
  dfs(&head);
  return head;
}


