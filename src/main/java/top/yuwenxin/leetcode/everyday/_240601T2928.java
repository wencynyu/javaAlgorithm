package top.yuwenxin.leetcode.everyday;

public class _240601T2928 {

    public int distributeCandies(int n, int limit) {
        int res = 0;
        for (int child1 = 0; child1 <= limit; child1++) {
            // Need to get min(n-child1, limit), it means the number of candies the 2nd child can get
            for (int child2 = 0; child2 <= Math.min((n - child1), limit); child2++) {
                int child3 = n - child1 - child2;
                if (child3 <= limit) {
                    res++;
                }
            }
        }
        return res;
    }
}
