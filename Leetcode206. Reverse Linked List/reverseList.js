class ListNode {
    constructor(val = 0, next = null) {
        this.val = val;
        this.next = next;
    }
}

function reverseList(head) {
    let prev = null;
    let current = head;

    while (current !== null) {
        const next = current.next;

        current.next = prev;

        prev = current;
        current = next;
    }

    return prev;
}

// Create: 1 -> 2 -> 3 -> null
const head = new ListNode(
    1,
    new ListNode(
        2,
        new ListNode(3)
    )
);

const reversed = reverseList(head);

// Print: 3 -> 2 -> 1
let current = reversed;

while (current !== null) {
    console.log(current.val);
    current = current.next;
}