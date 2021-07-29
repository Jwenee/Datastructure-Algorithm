/**
 * question: 206. 反转链表
 *
 * describe: 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1:
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 */
package list;

public class LeetCodeSolution206 {

//    method one
//    public ListNode reverseList(ListNode head) {
//        ListNode pre = null;
//        ListNode cur = head;
//        ListNode next;
//        while (cur != null) {
//            next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
//    }
//  method two
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode rec = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rec;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode ret = (new LeetCodeSolution206()).reverseList(head);
        System.out.println(ret);
    }
}
