package Breadth_First_Search_BFS_宽度优先遍历;

// 到达角落需要移除障碍物的最小数目
// 给你一个下标从 0 开始的二维整数数组 grid ，数组大小为 m x n
// 每个单元格都是两个值之一：
// 0 表示一个 空 单元格，
// 1 表示一个可以移除的 障碍物
// 你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。
// 现在你需要从左上角 (0, 0) 移动到右下角 (m - 1, n - 1)
// 返回需要移除的障碍物的最小数目
// 测试链接 : https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/

import java.util.ArrayDeque;

public class Code03_MinimumObstacleRemovalToReachCorner {

    public static int minimumObstacles(int[][] grid){

        int[] move = {-1,0,1,0,-1} ; // 上下左右扩充

        int m = grid.length ;
        int n = grid[0].length ;
        int[][] distance = new int[m][n] ; // 创建distance数组，从源头到目标节点的距离
        // 设置最大值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE ;
            }
        }

        // 设置双端队列
        ArrayDeque<int[]> deque = new ArrayDeque<>() ;
        // 添加0,0节点
        deque.addFirst(new int[] {0,0});
        distance[0][0] = 0 ;
        // 如果队列不为空 就继续
        while(!deque.isEmpty()) {
            int[] ints = deque.pollFirst(); // 弹出节点
            int x = ints[0] ; // 提取坐标
            int y = ints[1] ;
            // 如果x和y是右下角的饿节点了，就直接返回distance数组相应位置的值
            if (x == m - 1 && y == n - 1) {
                return distance[x][y] ;
            }
            // 上下左右扩充，扩充的节点判断是否为1，及时替换
            for (int i = 0; i < 4; i++) {
                int nx  = x + move[i] , ny = y + move[i + 1] ;

                // 如果 扩充的节点没越界  并且
                // distance数组中的扩充节点的值 > distance数组中的当前节点的值 + 扩充节点的权重
                // 那么就更新distance数组中的扩充节点的值
                if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                    distance[nx][ny] > distance[x][y] + grid[nx][ny]) {
                    distance[nx][ny] = distance[x][y] + grid[nx][ny] ;

                // 如果 扩充节点的权重为0 从双端队列的头部加入
                if (grid[nx][ny] == 0) {
                    deque.addFirst(new int[]{nx , ny});
                }// 如果 扩充节点的权重为1 从双端队列的尾部加入
                else {
                    deque.addLast(new int[] {nx , ny});
                    }
                }
            }
        }
        return -1;
    }
}
