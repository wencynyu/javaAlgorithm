package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class ReorderList {
    public void reorder(ListNode head){
        if (head == null) return;
        ListNode cur = head, next = cur.next;
        ListNode tPrev, tail;
        while (next != null && next.next != null){
            // 找到倒数第二个节点，如果没有则已经旋转完毕
            tPrev = cur;
            while (tPrev.next.next != null){
                tPrev = tPrev.next;
            }
            tail = tPrev.next;

            // 根据cur，next，tPrev，tail四个指针进行旋转
            cur.next = tail;
            tail.next = next;
            tPrev.next = null;
            cur = cur.next.next;
            next = cur.next;
        }
    }
}
