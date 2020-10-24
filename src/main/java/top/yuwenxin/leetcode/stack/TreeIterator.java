package top.yuwenxin.leetcode.stack;

import top.yuwenxin.stuct.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 通过栈实现迭代遍历二叉树
 */
public class TreeIterator {
    // 中左右
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val); // 中
                stack.push(root);
                root = root.left; // 左
            }
            root = stack.pop();
            root = root.right; // 右
        }
        return res;
    }

    // 左中右
    public List<Integer> inOrderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left; // 左
            }
            root = stack.pop();
            res.add(root.val); // 中
            root = root.right; // 右
        }
        return res;
    }

    // 左右中
    public List<Integer> postOrderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) { // 先入后出，root最后出栈
                stack.push(root);
                root = root.left; // 左
            }

            root = stack.pop();

            // 右
            if (root.right == null || root.right == prev) { // prev记录访问过的右节点
                res.add(root.val); // 中
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
