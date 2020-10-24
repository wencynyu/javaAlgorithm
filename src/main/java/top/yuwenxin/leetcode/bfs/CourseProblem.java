package top.yuwenxin.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 */
public class CourseProblem {
    
    // 拓扑排序的简单应用，主要使用到了队列的bfs实现
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        while (!queue.isEmpty()){
            int cur = queue.poll();
            count++;
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
        return count == numCourses;
    }
}
