package top.yuwenxin.stuct.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    public TreeNode root;

    /**
     * 数组转树，-1为空
     * @param nums
     * @return
     */
    public static TreeNode buildTree(int[] nums){
        // 调用先序构造方法
        return buildTreeByPre(nums);
    }

    /**
     * 先序构造
     */
    static int pos = 0;
    private static TreeNode buildTreeByPre(int[] nums) {
        TreeNode root;
        if (pos >= nums.length || nums[pos] == -1){
            root = null;
        }else {
            root = new TreeNode(nums[pos]);
            root.left = buildTree(nums);
            root.right = buildTree(nums);
        }
        pos++;
        return root;
    }


    /**
     * 先序遍历
     * @param root
     * @return
     */
    public static List<Integer> preOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private static void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private static void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        preOrder(root.left, res);
        res.add(root.val);
        preOrder(root.right, res);
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private static void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        preOrder(root.left, res);
        preOrder(root.right, res);
        res.add(root.val);
    }
}
