package top.yuwenxin.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RebuildQueue {
    public int[][] rebuild(int[][] people){
        List<int[]> list = new LinkedList<>();
        Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
        for (int[] i : people) list.add(i[1], i); // index, Element
        return list.toArray(new int[list.size()][]);
    }
}
