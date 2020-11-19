package top.yuwenxin.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class JiOuSort {
    // 朴实无华的解法，申请两个列表保存奇数和偶数
    public int[] sortArrayByParityII(int[] A) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        for (int i :
                A) {
            if ((i & 1) == 0) {
                evens.add(i);
            }else {
                odds.add(i);
            }
        }
        int[] res = new int[A.length];
        for (int i = 0; i < res.length; i+=2) {
            res[i] = evens.get(i / 2);
            res[i + 1] = odds.get((i + 1) / 2);
        }
        return res;
    }

    // 朴实无华的双指针
    public int[] sortArrayByParityII2(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
