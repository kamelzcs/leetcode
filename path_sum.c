/*
 * path_sum.c
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */


bool hasPathSum(struct TreeNode *root, int sum) {
  if(!root){
    if(!sum){
      return true;
    }
    return false;
  }
  return hasPathSum(root->left, sum - root->val) || hasPathSum(root->right, sum - root->val);
}
