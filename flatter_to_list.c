/*
 * flatter_to_list.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */


struct TreeNode *dfs(struct *root){
  struct TreeNode *left = root->left, *right = root->right;
  if(!left && !right){
    return root;
  }
  root->left = root->right = 0;
  struct TreeNode *right_most = root;
  if(left){
    root->right = left;
    right_most = dfs(left);
  }
  if(right){
    right_most->right = right;
    right_most = dfs(right);
  }
  return right_most;
}

void flatten(struct TreeNode *root) {
  if(!root){
    return;
  }
  dfs(root);
}

