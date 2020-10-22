package top.yuwenxin.leetcode.dp;

import top.yuwenxin.leetcode.backtrace.SubSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class SequenceProblem {

    /**
     * LCS最长公共子串
     */
    // 暴力解
    public int lcs(String str1, String str2) {
        String shorter = str1.length() <= str2.length() ? str1 : str2;
        String longer = str1.length() > str2.length() ? str1 : str2;

        int res = 0;
        for (int i = 0; i < shorter.length() - res; i++) {
            for (int j = i + res; j < shorter.length(); j++) {
                if (longer.contains(shorter.substring(i, j))) {
                    res = j - i;
                }
            }
        }
        return res;
    }

    // dp优化
    public int lcs2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int res = 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(dp[i][j], res);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }

    /**
     * LCS最长公共子序列
     */
    // 暴力解
    public int lcs11(int[] nums1, int[] nums2) {
        SubSet subSet = new SubSet();
        Set<List<Integer>> subSet1 = new HashSet<>(subSet.subSet(nums1));
        Set<List<Integer>> subSet2 = new HashSet<>(subSet.subSet(nums2));
        int res = 0;
        for (List<Integer> list :
                subSet1) {
            if (subSet2.contains(list)) {
                res = Math.max(res, list.size());
            }
        }
        return res;
    }

    // dp优化
    public int lcs12(int[] nums1, int[] nums2) {
        // todo:lcs12
        return 0;
    }

    /**
     * LCS最长连续子序列
     */
    // 暴力解
    public int lcs21(int[] nums1, int[] nums2) {
        SubSet subSet = new SubSet();
        Set<List<Integer>> subSet1 = new HashSet<>(subSet.subSet(nums1));
        Set<List<Integer>> subSet2 = new HashSet<>(subSet.subSet(nums2));
        int res = 0;
        for (List<Integer> list :
                subSet1) {
            if (subSet2.contains(list) && isContinuous(list)) {
                res = Math.max(res, list.size());
            }
        }
        return res;
    }

    private boolean isContinuous(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) + 1 != list.get(i + 1)) return false;
        }
        return true;
    }

    // dp优化
    public int lcs22(int[] nums1, int[] nums2) {
        // todo:lcs22
        return 0;
    }

    /**
     * LIS最长递增子序列
     */
    // 暴力解
    public int lis(int[] nums1, int[] nums2) {
        SubSet subSet = new SubSet();
        Set<List<Integer>> subSet1 = new HashSet<>(subSet.subSet(nums1));
        Set<List<Integer>> subSet2 = new HashSet<>(subSet.subSet(nums2));
        int res = 0;
        for (List<Integer> list :
                subSet1) {
            if (subSet2.contains(list) && isIncreament(list)) {
                res = Math.max(res, list.size());
            }
        }
        return res;
    }

    private boolean isIncreament(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) return false;
        }
        return true;
    }

    // dp优化
    public int lis2(int[] nums1, int[] nums2) {
        // todo:lis2
        return 0;
    }
}
