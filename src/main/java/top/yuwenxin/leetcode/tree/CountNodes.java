package top.yuwenxin.leetcode.tree;

import top.yuwenxin.stuct.tree.TreeNode;

public class CountNodes {
    int res = 0;
    public int countNodes(TreeNode root) {
//        dfs(root);
//        return res;
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        res++;
        dfs(root.left);
        dfs(root.right);
    }
}
