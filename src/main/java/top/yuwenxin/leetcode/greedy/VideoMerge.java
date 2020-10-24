package top.yuwenxin.leetcode.greedy;

public class VideoMerge {

    /**
     * 找到贪心的策略是问题的关键
     */
    public int merge(int[][] clips, int T) {
        // 记录从当前时间能访问到的最大时间
        int[] maxN = new int[T];
        for (int[] c: clips) {
            if (c[0] < T){
                maxN[c[0]] = Math.max(maxN[c[0]], c[1]);
            }
        }

        int end = 0, prev = 0;
        int res = 0;
        for (int i = 0; i < T; i++) {
            end = Math.max(end, maxN[i]);
            // 如果当前时刻与上一次时间的结尾出现断层，则说明无法合并video
            if (i == end){
                return -1;
            }
            // 如果当前时刻与上一次时间的开头相等，则可以直接进行合并，并且将prev置为上一次时间的结尾end时刻
            if (i == prev){
                res++;
                prev = end;
            }
        }

        return res;
    }
}
