/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

// impure recursive
function reverseList(
  head: ListNode | null,
  parent: ListNode | null = null,
): ListNode | null {
  if (!head) return parent;

  const next = head.next;
  head.next = parent;

  return reverseList(next, head);
};

// pure recursive
function reverseList(head: ListNode | null): ListNode | null {
  if (!head) return head;

  function reverse(curr: ListNode, parent: ListNode | null): ListNode {
    const next = curr.next;

    curr = new ListNode(curr.val, parent);

    if (!next) return curr;
    return reverse(next, curr);
  }

  return reverse(head, null);
};

// impure iterative
function reverseList(head: ListNode | null): ListNode | null {
  let prev = null;

  while (head) {
    let next = head.next;
    head.next = prev;
    prev = head;
    head = next;
  }

  return prev;
}

// pure iterative
function reverseList(head: ListNode | null): ListNode | null {
  let prev: ListNode | null = null;

  while (head) {
    prev = new ListNode(head.val, prev);
    head = head.next;
  }

  return prev;
}