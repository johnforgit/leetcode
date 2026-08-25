class ListNode {
    val: number;
    next: ListNode | null;

    constructor(val = 0, next: ListNode | null = null) {
        this.val = val;
        this.next = next;
    }
}

function reverseList(head: ListNode | null): ListNode | null {
    let prev: ListNode | null = null;
    let current: ListNode | null = head;

    while (current !== null) {
        const next: ListNode | null = current.next;

        current.next = prev;

        prev = current;
        current = next;
    }

    return prev;
}

// Create: 1 -> 2 -> 3 -> null
const head = new ListNode(1,
    new ListNode(
        2,
        new ListNode(3)
    )
);

const reversed = reverseList(head);

// Print: 3 -> 2 -> 1
let current: ListNode | null = reversed;

while (current !== null) {
    console.log(current.val);
    current = current.next;
}