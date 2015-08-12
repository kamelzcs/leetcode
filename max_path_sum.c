/*
 * max_path_sum.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

int ans;

int max(int a, int b){
  return a > b ? a : b;
}

int dfs(struct TreeNode *root){
  if(!root){
    return 0;
  }
  int l = max(dfs(root->left), 0), r = max(0, dfs(root->right));
  ans = max(ans, l + r + root->val);
  return max(l, r) + root->val;
}
int maxPathSum(struct TreeNode *root) {
  ans = -10000;
  if(!root){
    return 0;
  }
  int l = max(dfs(root->left), 0), r = max(0, dfs(root->right));
  return max(ans, l + r + root->val);
}
