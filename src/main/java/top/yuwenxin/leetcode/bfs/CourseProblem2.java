package top.yuwenxin.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("all")
public class CourseProblem2 {
    // 拓扑排序的简单应用，主要使用到了队列的bfs实现
    public int[] canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 1) return new int[0];

        int[] degreeTable = new int[numCourses];
        for (int[] prerequisite :
                prerequisites) {
            degreeTable[prerequisite[0]]++; // 每找到一个依赖项就在入度表中+1
        }

        // 入度为0的课程可以直接进行学习，也就是没有依赖项的课程
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degreeTable.length; i++) {
            if (degreeTable[i] == 0) queue.offer(i);
        }

        int count = 0; // 完成的课程数
        int[] res = new int[numCourses];
        while (!queue.isEmpty()){
            int cur = queue.poll();
            res[count++] = cur;
            for (int[] prerequisite :
                    prerequisites) {
                if (prerequisite[1] == cur){
                    degreeTable[prerequisite[0]]--;
                    if (degreeTable[prerequisite[0]] == 0){
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        return count == numCourses? res : new int[0];
    }
}
