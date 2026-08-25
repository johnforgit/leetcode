/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */


/**
 * 
 * @param {*} head 
 */
var reverselist = function(head) {
     let prev=null;
     let curr=head;
     while(curr !== null) {
          const nextnode = curr.next;
          curr.next = prev;
          prev=curr;
          curr=nextnode;
     }
     return prev;
}