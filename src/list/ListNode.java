package list;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public ListNode(int[] nodes) {
        if (nodes == null || nodes.length == 0)
            throw new IllegalArgumentException("Nodes arr can not be empty");
        this.val = nodes[0];
        ListNode cur = this;
        for (int i = 1; i < nodes.length; i++) {
            cur.next = new ListNode(nodes[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}