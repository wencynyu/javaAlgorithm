package top.yuwenxin.leetcode.backtrace.tree;

import top.yuwenxin.stuct.tree.TreeNode;

public class CommonAncestor {
    public TreeNode getCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2){
        return solve(root, n1, n2);
    }

    private TreeNode solve(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null || root == n1 || root == n2){
            return root;
        }

        TreeNode left = solve(root.left, n1, n2);
        TreeNode right = solve(root.right, n1, n2);
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }

}
