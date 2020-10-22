package top.yuwenxin.leetcode.backtrace.tree;

import top.yuwenxin.stuct.tree.TreeNode;

public class BalanceTree {
    public boolean isBalanced(TreeNode root){
        if (root == null){
            return true;
        }
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = depth(root.left);
        if (left == -1) return -1;
        int right = depth(root.right);
        if (right == -1) return -1;
        return Math.abs((left - right)) > 1? -1: Math.max(left, right) + 1;
    }
}
