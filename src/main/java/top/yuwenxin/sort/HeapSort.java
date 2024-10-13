package top.yuwenxin.sort;

import top.yuwenxin.utils.ArrayUtil;
import top.yuwenxin.utils.SortUtil;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = SortUtil.arr;
        HeapSort sort = new HeapSort();
        sort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void heapSort(int[] arr){
        for (int i = arr.length - 1; i > 0; i--) {
            maxHeap(arr, i);

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    private void maxHeap(int[] arr, int n) {
        int child;
        // 从第n个节点开始维护堆
        for (int i = (n - 1) / 2; i >= 0; i--) {
            // 找到n的父节点i下标
            child = 2 * i + 1;
            // 仅针对第n个节点，如果child != n说明当前的第n个节点是左节点，此时会定位到非n父节点
            // 如果恰好此时非n父节点 < 实际的n节点，则需要吧child还原为n节点
            if (child != n && arr[child] < arr[child + 1]) {
                child++;
            }
            // 交换父节点与左右子节点中的最大值
            if (arr[i] < arr[child]) {
                ArrayUtil.swap(arr, i, child);
            }
        }
        
    }
}
