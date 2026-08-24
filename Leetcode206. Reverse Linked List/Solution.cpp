/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
     struct ListNode {
          int val;
          ListNode *next;
          ListNode() : val(0), next(nullptr) {}
          ListNode(int x) : val(x), next(nullptr) {}
          ListNode(int x, ListNode *next) : val(x), next(next) {}
     };
    ListNode* reverseList(ListNode* head) {
        if(head == NULL) {
            return NULL;
        }
        ListNode *curr = head, *prev=NULL, *nextnode;
        while(curr != NULL) {
            nextnode = curr->next;
            curr->next = prev;
            prev = curr;
            curr = nextnode;
        }

        return prev;
    }
};