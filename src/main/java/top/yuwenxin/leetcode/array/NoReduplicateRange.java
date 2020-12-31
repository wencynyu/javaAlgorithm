package top.yuwenxin.leetcode.array;

import java.util.Arrays;

public class NoReduplicateRange {
    // 返回移除区间的最小个数
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;
        // 关键点在于排序算法的选择：先按a[1]排序，a[1]相等则按a[0]排序，使数组满足贪心策略
        Arrays.sort(intervals, (o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o1[1] - o2[1]);
        int last = intervals[0][0];
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 有重合部分则删减区间数+1
            if (last > intervals[i][0]) res++;
            // 否则将last置为新的last
            else last = intervals[i][1];
        }
        return res;
    }
}
