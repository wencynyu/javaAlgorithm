package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class MergeList {
    @SuppressWarnings("all")
    public ListNode merge(ListNode list1, ListNode list2){
        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
            }else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        node.next = l1 == null? l2 : l1;
        return dummy.next;
    }
}
