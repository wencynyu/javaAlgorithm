package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class ReverseList {
    public ListNode reverse(ListNode head){
        ListNode prev = null, cur = head, next;

        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
