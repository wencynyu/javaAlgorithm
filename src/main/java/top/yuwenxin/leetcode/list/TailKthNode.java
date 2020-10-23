package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class TailKthNode {
    public ListNode tailKthNode(ListNode head, int k){
        ListNode quick = head, slow = head;
        int i = 0;
        while (i < k && quick != null){
            quick = quick.next;
            i++;
        }

        if (i != k){
            throw new RuntimeException("param error: the length of List < k!!");
        }

        while (quick != null){
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }
}
