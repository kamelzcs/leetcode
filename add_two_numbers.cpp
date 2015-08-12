/*
 * add_two_numbers.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<algorithm>
#include<iostream>
using namespace std;

struct ListNode {
  int val;
  ListNode *next;
  ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
        ListNode *head = new ListNode(-1);
        int carry = 0;
        ListNode *tail = head;
        while(l1 || l2 || carry){
            int temp = carry;
            if(l1){
                temp += l1->val;
                l1 = l1->next;
            }
            if(l2){
                temp += l2->val;
                l2 = l2->next;
            }
            ListNode *nxt = new ListNode(temp % 10);
            tail->next = nxt;
            tail = nxt;
            carry = temp / 10;
        }
        return head->next;
    }
};


