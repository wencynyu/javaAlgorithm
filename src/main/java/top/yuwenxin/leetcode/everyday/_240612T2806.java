package top.yuwenxin.leetcode.everyday;

public class _240612T2806 {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        return (int) (100 - Math.round(purchaseAmount / 10.0) * 10);
    }
}
