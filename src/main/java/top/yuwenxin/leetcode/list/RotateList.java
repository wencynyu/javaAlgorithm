package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class RotateList {
    /**
     * 每个节点往右移动k位，k超过链表长度则回到开头进行循环移动
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k){
        if (head == null) return null;
        int len = getLen(head);
        int move = k % len;
        if (move == 0) return head;

        // 获取倒数第move+1个节点
        int i = 0;
        ListNode prev = head;
        while (i < len - move - 1){
            prev = prev.next;
            i++;
        }

        // 保存结果
        ListNode res = prev.next;
        ListNode tail = prev;
        while (tail.next != null){
            tail = tail.next;
        }

        // 旋转
        prev.next = null;
        tail.next = head;

        return res;
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
