package top.yuwenxin.leetcode.array;

import java.util.*;

public class MaxCombine {
    Set<String> set = new HashSet<>();
    public String largestNumber(int[] nums) {
        backtrace(nums, 0);
        List<String> list = new ArrayList<>(set);
        list.sort((s1, s2) -> {
            return s2.compareTo(s1);
        });
        return list.get(0);
    }

    private void backtrace(int[] nums, int pos) {
        if (pos == nums.length){
            StringBuilder tmp = new StringBuilder();
            for (int i :
                    nums) {
                tmp.append(i);
            }
            set.add(tmp.toString());
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            swap(nums, i, pos);
            backtrace(nums, pos + 1);
            swap(nums, i, pos);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
