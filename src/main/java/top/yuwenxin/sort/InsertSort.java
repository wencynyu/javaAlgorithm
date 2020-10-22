package top.yuwenxin.sort;

import top.yuwenxin.utils.ArrayUtil;
import top.yuwenxin.utils.SortUtil;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.arr;
        InsertSort sort = new InsertSort();
        sort.insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];  // 注意：要保存副本，供覆盖操作完成后替换至合适位置
            int j = i - 1;
            while (j > -1 && tmp < arr[j]) {
                arr[j + 1] = arr[j--];  // 这里推荐使用覆盖操作减少指令数
            }
            arr[j + 1] = tmp;
        }
    }
}
