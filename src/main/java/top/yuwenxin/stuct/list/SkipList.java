package top.yuwenxin.stuct.list;

import java.util.Random;

/**
 * 空间换时间的优化链表，保留二分搜索记录节点
 * 查询 & 插入 复杂度均为log n
 */
public class SkipList {

    private static final int MAX_DEPTH = 32; // avoid too deep skip tree, according to redis implement
    private static final int THRESHOLD = Integer.MAX_VALUE >> 2; // P = 0.25

    private SkipListNode head;
    private int depth;

    public SkipList() {
        this.head = new SkipListNode();
    }



    private int genCurInsRandomDepth() {
        int res = 1;
        Random r = new Random();
        while (r.nextInt() < THRESHOLD) {
            res++;
        }
        return Math.max(res, MAX_DEPTH);
    }

    static class SkipListNode {
        private int value;
        private SkipListNode[] forward;
    }
}
