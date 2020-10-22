package top.yuwenxin.sort;

import top.yuwenxin.utils.ArrayUtil;
import top.yuwenxin.utils.SortUtil;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.arr;
        BubbleSort sort = new BubbleSort();
        sort.bubbleSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    ArrayUtil.swap(arr, i, j);
                }
            }
        }
    }

    public void bubbleSort2(int[] arr){
        boolean swap = true;
        for (int i = 0; i < arr.length - 1 && swap; i++) {
            swap = false;
            // 如果没有在第二重循环中进行swap交换，则说明已经排序完成可以跳出循环
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    ArrayUtil.swap(arr, i, j);
                    swap = true;
                }
            }
        }
    }
}
