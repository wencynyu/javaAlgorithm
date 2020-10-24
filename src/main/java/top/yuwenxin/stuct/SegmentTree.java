package top.yuwenxin.stuct;

public class SegmentTree {
    static class TreeNode {
        int val;
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end) {
            left = null;
            right = null;
            this.start = start;
            this.end = end;
        }
    }

    TreeNode root;

    /**
     * 根据传入的数组构造线段树
     */
    public SegmentTree(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    // 后序遍历构建线段树操作
    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;

        TreeNode curr = new TreeNode(start, end);
        if (start == end) {
            curr.val = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            curr.left = buildTree(nums, start, mid);
            curr.right = buildTree(nums, mid + 1, end);
            curr.val = curr.left.val + curr.right.val;
        }
        return curr;
    }

    /**
     * 更新数组，调用更新线段树的方法
     */
    public void update(int i, int val) {
        updateTree(root, i, val);
    }

    // 后序遍历更新线段树操作
    public void updateTree(TreeNode node, int i, int val) {
        if (node.start == node.end) {
            node.val = val;
        } else {
            int mid = node.start + (node.end - node.start) / 2;
            if (i <= mid) {
                updateTree(node.left, i, val);
            } else{
                updateTree(node.right, i, val);
            }
            node.val = node.left.val + node.right.val;
        }
    }

    /**
     * 区间查询，调用查询树方法
     */
    public int sumRange(int i, int j) {
        return queryTree(root, i, j);
    }

    // 后序遍历查询线段树操作
    public int queryTree(TreeNode node, int i, int j) {
        if (node.start == i && node.end == j) return node.val;
        else {
            int mid = node.start + (node.end - node.start) / 2;
            if (j <= mid) {
                return queryTree(node.left, i, j);
            } else if (i >= (mid + 1)) {
                return queryTree(node.right, i, j);
            } else {
                return queryTree(node.left, i, mid) + queryTree(node.right, mid + 1, j);
            }
        }
    }
}
