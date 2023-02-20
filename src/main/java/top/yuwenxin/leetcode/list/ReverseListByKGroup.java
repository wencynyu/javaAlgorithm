package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class ReverseListByKGroup {
    public ListNode reverse(ListNode head, int k) {
        int len = getLen(head);

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, cur = head, next;
        for (int i = 0; i < len / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                next = cur.next;
                cur.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = cur;
            cur = prev.next;
        }
        return dummy.next;
    }

    private int getLen(ListNode head) {
        int res = 0;
        while (head != null){
            head = head.next;
            res++;
        }
        return res;
    }
}
