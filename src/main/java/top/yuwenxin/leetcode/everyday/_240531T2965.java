package top.yuwenxin.leetcode.everyday;

import java.util.HashSet;
import java.util.Set;

public class _240531T2965 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] res = new int[2];
        int n = grid.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                if (!set.contains(cur)) {
                    set.add(cur);
                } else {
                    res[0] = cur;
                }
            }
        }

        for (int i = 1; i <= n * n; i++) {
            if (!set.contains(i)) {
                res[1] = i;
            }
        }
        return res;
    }
}
