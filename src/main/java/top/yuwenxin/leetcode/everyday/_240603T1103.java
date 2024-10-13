package top.yuwenxin.leetcode.everyday;

public class _240603T1103 {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];

        int count = 1;
        int cur = 0;
        while (candies > 0) {
            candies = candies - count;
            res[cur] += count;
            count++;
            cur++;
            if (cur == num_people) {
                cur = 0;
            }
            if (candies < 0) {
                res[cur] -= candies;
            }
        }
        return res;
    }
}
