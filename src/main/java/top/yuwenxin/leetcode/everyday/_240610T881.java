package top.yuwenxin.leetcode.everyday;

import java.util.Arrays;

public class _240610T881 {

    public int numRescueBoats(int[] people, int limit) {
        int res = 0;
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        while (i <= j) {
            if (people[i] + people[j] > limit) {
                j--;
                res++;
                continue;
            }

            i++;
            j--;
            res++;
        }
        return res;
    }
}
