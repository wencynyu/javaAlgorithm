package top.yuwenxin.leetcode.backtrace;

import top.yuwenxin.utils.ArrayUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubSet {
    public static void main(String[] args) {
        SubSet subSet = new SubSet();
        List<List<Integer>> res = subSet.subSet(new int[]{1, 2, 3, 4});
        System.out.println(res.size());
        res.forEach(System.out::println);
    }
    /**
     * 求所有子集（子序列）
     * 在许多子序列题目中可以进行暴力解
     * 例如LCS（最长连续/公共子序列），LIS（最长递增子序列）
     */
    Set<List<Integer>> set = new HashSet<>(); // 使用Set进行去重
    public List<List<Integer>> subSet(int[] nums){
        backtrace(nums, 0, new ArrayList<>());
        return new ArrayList<>(set);
    }

    private void backtrace(int[] nums, int pos, ArrayList<Integer> tmp) {
        for (int i = pos; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrace(nums, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }

        set.add(new ArrayList<>(tmp));
    }
}
