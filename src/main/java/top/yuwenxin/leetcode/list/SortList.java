package top.yuwenxin.leetcode.list;

import top.yuwenxin.stuct.list.ListNode;

public class SortList {

    /**
     * 数组的插入排序
     */
    public void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];  // 注意：要保存副本，供覆盖操作完成后替换至合适位置
            int j = i - 1;
            while (j > -1 && tmp < arr[j]) {
                arr[j + 1] = arr[j--];  // 这里推荐使用覆盖操作减少指令数
            }
            arr[j + 1] = tmp;
        }
    }

    /**
     * 链表的插入排序
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        // dummy节点保存头节点便于插入
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        // 从第二个节点开始插入
        while (curr != null) {
            // 前面节点已经是有序状态，只需要比较最后一个sorted的节点
            if (lastSorted.val <= curr.val) {
                // cur > lastSorted 说明该节点已经是有序的
                lastSorted = lastSorted.next;
            } else {
                // cur < lastSorted 说明该节点需要进行插入排序
                ListNode prev = dummyHead; // 从头节点开始比较

                // 找到第一个比cur节点大的节点作为插入的依据
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                // 执行插入过程
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }


    /**
     * 链表的归并排序
     * 可以参考数组的归并排序
     */
    public ListNode mergeSortList(ListNode head) {
        return mergeSortList(head, null);
    }

    // 拆分过程
    public ListNode mergeSortList(ListNode head, ListNode tail) {
        if (head == null) return null;

        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = mergeSortList(head, mid);
        ListNode list2 = mergeSortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    // 合并过程：普普通通的合并链表（作为单题出现）
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
