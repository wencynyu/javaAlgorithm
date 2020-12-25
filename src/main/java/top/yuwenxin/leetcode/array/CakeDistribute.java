package top.yuwenxin.leetcode.array;

import java.util.Arrays;

public class CakeDistribute {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] >= g[res]){
                res++;
                if (res == g.length) break;
            }
        }
//        for (int i = 0; i < g.length; i++) {
//            for (int j = 0; j < s.length; j++) {
//                if (g[i] <= s[j]){
//                    res++;
//                    s[j] = 0;
//                    break;
//                }
//            }
//        }
        return res;
    }
}
