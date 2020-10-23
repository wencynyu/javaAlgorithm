package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

import java.util.HashSet;
import java.util.Set;

public class CircleList {
    public boolean isCircle(ListNode head){
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
