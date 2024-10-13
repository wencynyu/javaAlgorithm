package top.yuwenxin.leetcode.everyday;

import java.util.BitSet;

public class _240602T575 {
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        BitSet bitSet = new BitSet(n);
        int eatCount = 0;
        for (int type : candyType) {
            if (eatCount > n / 2) {
                break;
            }
            if (bitSet.get(type)) {
                continue;
            }
            bitSet.set(type);
            eatCount++;
        }

        return eatCount;
    }
}
