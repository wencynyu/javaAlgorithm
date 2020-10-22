package top.yuwenxin.leetcode.backtrace;

import top.yuwenxin.utils.ArrayUtil;

import java.util.*;

public class Permute {
    /**
     * 求全排列
     * 在许多场景可以用来进行暴力解，例如笔试题中的全排列/n为整数的xx数
     */
    List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums){
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        backtrace(numsList, 0);
        return list;
    }

    private void backtrace(List<Integer> nums, int pos) {
        if (pos == nums.size()){
            list.add(new ArrayList<>(nums));
            return;
        }

        for (int i = pos; i < nums.size(); i++) {
            Collections.swap(nums, i, pos);
            backtrace(nums, pos + 1);
            Collections.swap(nums, i, pos);
        }
    }
}
