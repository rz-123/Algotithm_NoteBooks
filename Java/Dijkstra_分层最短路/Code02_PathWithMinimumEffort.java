package Dijkstra_分层最短路;

// 最小体力消耗路径
// 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights
// 其中 heights[row][col] 表示格子 (row, col) 的高度
// 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1)
// （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动
// 你想要找到耗费 体力 最小的一条路径
// 一条路径耗费的体力值是路径上，相邻格子之间高度差绝对值的最大值
// 请你返回从左上角走到右下角的最小 体力消耗值
// 测试链接 ：https://leetcode.cn/problems/path-with-minimum-effort/

import java.util.PriorityQueue;

public class Code02_PathWithMinimumEffort {

    // 上下左右扩充 数组
    public static int[] move = new int[] {-1 , 0 , 1 , 0 , -1} ;

    public int minimumEffortPath(int[][] heights){
        int n = heights.length ;
        int m = heights[0].length ;
        int[][] distance = new int[n][m] ;
        for (int i = 0 ; i < n ; i++){
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE ; // 初始化最大值
            }
        }
        distance[0][0] = 0 ;
        boolean[][] visited = new boolean[n][m] ; // 是否进过堆
        // 堆内存放数组，(行，列，源点到当前格子的权重)
        PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a , int[] b) -> a[2] - b[2]) ;
        heap.add(new int[] {0,0,0}) ; // 先将源点加入

        // 只要堆内不为空，就继续
        while (!heap.isEmpty()) {
            int[] record = heap.poll() ; // 弹出
            int x = record[0] ; // 行
            int y = record[1] ; // 列
            int w = record[2] ; // 到该点的权重
            // 如果该节点进过堆，就不管
            if (visited[x][y]) {
                continue;
            }
            // 剪枝：如果弹出的坐标恰好是 右下角，就直接返回权重
            if (x == n - 1 && y == m - 1) {
                return w ;
            }
            // 否则 就讲 visited[x][y]设为true，由该点向四周扩散
            visited[x][y] = true ;
            for (int i = 0; i < 4; i++) {
                // 扩点
                int nx = x + move[i] ;
                int ny = y + move[i+1] ;
                // 如果扩充节点没越界
                // 并且 没进入过堆，
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    // max（该点的权重 跟 abs（当前格子的高度 减去 上一个格子的高度））
                    int nw = Math.max( w , Math.abs(heights[x][y] - heights[nx][ny])) ;
                    // 如果 选出的权重 < 该点的最短路
                    if (nw < distance[nx][ny]) {
                        // 那么就更新该点的最短路
                        distance[nx][ny] = nw ;
                        // 该点加入堆
                        heap.add(new int[] {nx , ny , nw}) ;
                    }
                }
            }
        }
        return -1 ;
    }
}
