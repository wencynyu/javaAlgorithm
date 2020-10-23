package top.yuwenxin.stuct.graph;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 双向连通图的实现
 *
 * @param <E>
 */
public class AdjMatrixGraph<E> implements IGraph<E> {
    int[][] adjMatrix;
    E[] vertex;
    int numOfVertex;
    int maxNumOfVertex;
    boolean[] visited;

    final int INF = 0x3f3f3f3f;

    public AdjMatrixGraph(int n, Class<E> type) {
        this.maxNumOfVertex = n;
        this.adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (i == j) {
                    this.adjMatrix[i][j] = 0;
                } else {
                    this.adjMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        this.vertex = (E[]) Array.newInstance(type, n);
    }

    @Override
    public int getNumOfVertex() {
        return this.numOfVertex;
    }

    @Override
    public boolean insertVex(E v) {
        if (this.numOfVertex >= this.maxNumOfVertex) {
            return false;
        }

        this.vertex[this.numOfVertex + 1] = v;
        return true;
    }

    @Override
    public boolean deleteVex(E v) {
        int index = indexOfVex(v);
        if (index == -1) {
            System.out.println("there is no v:" + v.toString());
            return false;
        }

        // 修改vertex节点数组
        // 从删除节点的index开始，到现有的节点总数-1，后一个覆盖前一个依次往前覆盖
        // 最后一个活跃节点置为空
        for (int i = index; i < numOfVertex - 1; i++) {
            vertex[i] = vertex[i + 1];
        }
        vertex[numOfVertex - 1] = null;

        for (int col = index; col < numOfVertex - 1; col++) {
            for (int row = 0; row < numOfVertex; row++) {
                adjMatrix[col][row] = adjMatrix[col + 1][row];
            }
        }

        for (int row = index; row < numOfVertex - 1; row++) {
            for (int col = 0; col < numOfVertex; col++) {
                adjMatrix[col][row] = adjMatrix[col][row + 1];
            }
        }
        numOfVertex--;
        return true;
    }

    @Override
    public int indexOfVex(E v) {
        for (int i = 0; i < vertex.length; i++) {
            if (v.equals(vertex[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E getVexByIndex(int index) {
        if (index < 0 || index >= numOfVertex) {
            return null;
        }
        return vertex[index];
    }

    @Override
    public boolean insertEdge(int v1, int v2, int weight) {
        if (v1 < 0 || v2 < 0 || v1 > numOfVertex || v2 > numOfVertex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        adjMatrix[v1][v2] = weight;
        adjMatrix[v2][v1] = weight;
        return true;
    }

    @Override
    public boolean deleteEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 > numOfVertex || v2 > numOfVertex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        adjMatrix[v1][v2] = Integer.MAX_VALUE;
        adjMatrix[v2][v1] = Integer.MAX_VALUE;
        return true;
    }

    @Override
    public int getEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 > numOfVertex || v2 > numOfVertex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return adjMatrix[v1][v2];
    }

    @Override
    /**
     * 从第k个节点开始进行深度优先搜索
     */
    public String depthFirstSearch(int index) {
        if (getVexByIndex(index) == null) {
            throw new RuntimeException("vertex is not in graph");
        }

        this.visited = new boolean[numOfVertex];
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(index);
        visited[index] = true;

        while (!stack.isEmpty()) {
            int v = stack.pop();
            sb.append(vertex[v].toString()).append(",");
            for (int i = 0; i < numOfVertex; i++) {
                if ((adjMatrix[v][i] != 0) && (adjMatrix[v][i] != Integer.MAX_VALUE) && !visited[index]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String breadFirstSearch(int index) {
        if (getVexByIndex(index) == null) {
            throw new RuntimeException("vertex is not in graph");
        }

        this.visited = new boolean[numOfVertex];
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            sb.append(vertex[v].toString()).append(",");
            for (int i = 0; i < numOfVertex; i++) {
                if ((adjMatrix[v][i] != 0) && (adjMatrix[v][i] != Integer.MAX_VALUE) && !visited[index]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

        return sb.toString();
    }

    @Override
    /**
     * 第v个节点开始，到达各节点的最短路径
     * 核心思想是：贪心 + 广度优先搜索
     */
    public int[] dijkstra(int v) {
        boolean[] isVisited = new boolean[adjMatrix.length];
        int[] res = new int[adjMatrix.length];
        Arrays.fill(res, INF); // 将所有节点置为不可达

        // 自身访问自身距离置为0
        isVisited[v] = true;
        res[v] = 0;

        int unVisitNode = adjMatrix.length;
        int index = v;
        while (unVisitNode > 0) {
            int min = INF;
            // 找到对于v节点来说最短的访问路径和访问到的节点，并添加为可访问节点
            for (int i = 0; i < res.length; i++) {
                if (min > res[i] && !isVisited[i]) {
                    index = i;
                    min = res[i];
                }
            }

            for (int i = 0; i < adjMatrix.length; i++) {
                // 根据找到的最短访问路径和节点对所有res进行更新
                res[i] = Math.min(res[i], res[i] + adjMatrix[index][i]);
            }
            unVisitNode--;
            // 添加的可访问节点设置为已访问
            isVisited[index] = true;
        }

        return res;
    }

    /**
     * k次中转限制的dijkstra算法
     * @param src 源
     * @param dst 目的
     * @param K 限制
     * @return
     */
    public int lowestCostFromA2B(int src, int dst, int K) {
        Map<Integer, Integer> best = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>();
        pq.offer(new int[]{0, 0, src}); // cost, time, dst

        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0], time = info[1], place = info[2];
            if (time > K + 1 || cost > best.getOrDefault(time * 1000 + place, INF))
                continue;
            if (place == dst)
                return cost;

            for (int nei = 0; nei < adjMatrix.length; ++nei) {
                if (adjMatrix[place][nei] > 0) {
                    int newCost = cost + adjMatrix[place][nei];
                    if (newCost < best.getOrDefault((time + 1) * 1000 + nei, INF)) {
                        pq.offer(new int[]{newCost, time + 1, nei});
                        best.put((time + 1) * 1000 + nei, newCost);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 弗洛伊德算法，用来求多源的最短路径（所有节点的最短路径）
     * 核心思想：动态规划
     * 时间复杂度n^3
     * 空间复杂度n^2：保存结果
     * @return
     */
    public int[][] floyd() {
        int[][] res = new int[adjMatrix.length][adjMatrix[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if (i == j) {
                    res[i][j] = 0;
                } else if (adjMatrix[i][j] == 0) {
                    res[i][j] = INF;
                } else {
                    res[i][j] = adjMatrix[i][j];
                }
            }
        }

        for (int k = 0; k < res.length; k++) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[0].length; j++) {
                    res[i][j] = Math.min(res[i][j], res[i][k] + res[k][i]);
                }
            }
        }

        return res;
    }

}