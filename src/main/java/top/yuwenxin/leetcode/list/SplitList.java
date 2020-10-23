package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class SplitList {
    public ListNode split(ListNode head, int x) {
        ListNode large = new ListNode(-1);
        ListNode small = new ListNode(-2);
        ListNode smallPtr = small;
        ListNode largePtr = large;
        ListNode cur = head;

        while (cur != null){
            if (cur.val < x){
                smallPtr.next = cur;
                smallPtr = smallPtr.next;
            }else {
                largePtr.next = cur;
                largePtr = largePtr.next;
            }
            cur = cur.next;
        }
        largePtr.next = null;
        smallPtr.next = large.next;
        return small.next;
    }
}
