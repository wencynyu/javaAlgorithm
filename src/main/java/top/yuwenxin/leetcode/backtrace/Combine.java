package top.yuwenxin.leetcode.backtrace;

import top.yuwenxin.utils.ArrayUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combine {
    /**
     * 求所有组合
     */
    Set<List<Integer>> set = new HashSet<>(); // 使用Set进行去重
    public List<List<Integer>> subSet(int[] nums, int k){
        backtrace(nums, 0, new ArrayList<>(), k);
        return new ArrayList<>(set);
    }

    private void backtrace(int[] nums, int pos, ArrayList<Integer> tmp, int k) {
        if (tmp.size() == k){
            set.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrace(nums, i + 1, tmp, k);
            tmp.remove(tmp.size() - 1);
        }
    }
}
