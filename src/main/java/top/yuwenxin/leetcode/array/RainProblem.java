package top.yuwenxin.leetcode.array;

public class RainProblem {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     */
    public int trap(int[] height) {
        int i = 0, j = i + 1;
        int len = height.length;
        int res = 0;
        int tmp = 0; // tmp用来保存蓄水池中间凸起的柱子长度，当找到蓄水池时需要减去
        while (i < len && j < len){
            if (height[j] >= height[i]){
                res += Math.min(height[i], height[j]) * (j - i - 1); // 蓄水池最大蓄水量
                res -= tmp; // 减去蓄水池中的柱子占比
                i = j;
                tmp = 0;
            }else{
                tmp += height[j]; // 累计柱子
            }
            j++;
        }

        tmp = 0;
        i = len - 1;
        j = i - 1;
        while (i >= 0 && j >= 0){
            if (height[j] > height[i]){ // 一个重点，这里不能是大于等于，会在2,0,0,2用例报错，因为没有最高点
                res += Math.min(height[i], height[j]) * (i - j - 1);
                res -= tmp;
                i = j;
                tmp = 0;
            }else{
                tmp += height[j];
            }
            j--;
        }

        return res;
    }
}
