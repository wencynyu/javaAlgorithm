package top.yuwenxin.leetcode.backtrace.tree;

import top.yuwenxin.stuct.tree.TreeNode;

public class PathSum {
    public int sumNumbers(TreeNode root) {
//        int depth = getDepth(root);
//        return solve(root, depth - 1);
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int prev) {
        if (root == null) return 0;

        int cur = prev * 10 + root.val;

        if (root.left == null && root.right == null) return cur;
        return sumNumbers(root.left, cur) + sumNumbers(root.right, cur);
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;

        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 出现错误：
     * 左右路径的深度均会被认定为最大深度进行计算，拉闸
     */
//    private int solve(TreeNode root, int m) {
//        if (root == null){
//            return 0;
//        }
//        int left = solve(root.left, m - 1);
//        int right = solve(root.right, m - 1);
//        int cur = root.val;
//        // left + right + 当前节点 * 数深（需要计算当前分支的真实深度） * 叶子节点个数
//        return (int) (left + right + cur * Math.pow(10, m) * leafNum(root));
//    }

    private int leafNum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null){
            return 1;
        }
        int left = leafNum(root.left);
        int right = leafNum(root.right);

        return left + right;
    }
}
