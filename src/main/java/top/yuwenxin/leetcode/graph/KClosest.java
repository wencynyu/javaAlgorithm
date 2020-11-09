package top.yuwenxin.leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosest {
    public int[][] kClosest(int[][] points, int K) {
        if (points.length < K) return points;
        PriorityQueue<int[]> queue = new PriorityQueue<>((arr1, arr2) -> {
            int dist1 = arr1[0] * arr1[0] + arr1[1] * arr1[1];
            int dist2 = arr2[0] * arr2[0] + arr2[1] * arr2[1];
            return dist1 - dist2;
        });

        for (int[] ints :
                points) {
            queue.offer(ints);
        }

        for (int[] ints :
                queue) {
            System.out.println(Arrays.toString(ints));
        }

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] tmp = queue.poll();
            res[i][0] = tmp[0];
            res[i][1] = tmp[1];
        }
        return res;
    }
}
