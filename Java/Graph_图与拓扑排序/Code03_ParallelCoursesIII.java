package Graph_图与拓扑排序;

// 并行课程 III
// 给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n
// 同时给你一个二维整数数组 relations ，
// 其中 relations[j] = [prevCoursej, nextCoursej]
// 表示课程 prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）
// 同时给你一个下标从 0 开始的整数数组 time
// 其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
// 请你根据以下规则算出完成所有课程所需要的 最少 月份数：
// 如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
// 你可以 同时 上 任意门课程 。请你返回完成所有课程所需要的 最少 月份数。
// 注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）
// 测试链接 : https://leetcode.cn/problems/parallel-courses-iii/

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;

public class Code03_ParallelCoursesIII {

    public static int minimumTime(int n , int[][] relations , int[] time){

        // 邻接表建图
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>() ;
        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>()) ;
        }

        int[] indegree = new int[n+1] ; // 入度表，0不用

        for (int[] edge : relations) {
            graph.get(edge[0]).add(edge[1]) ;
            indegree[edge[1]]++ ;
        }

        int[] queue = new int[n] ;
        int l = 0 , r = 0 ;
        for (int i = 1 ; i <= n ; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i ;
            }
        }

        int[] cost = new int[n+1] ; // 统计表
        int ans = 0 ;
        while (l<r) {
            int cur = queue[l++] ;
            cost[cur] += time[cur - 1] ; // 加该点的月份，因为time[0]代表1，所以time[cur-1] 为 cur位置
            ans = Math.max(ans , cost[cur]) ;
            for (int next : graph.get(cur)) {
                cost[next] = Math.max(cost[next] , cost[cur]) ; // 选出 最大的月份
                if (--indegree[next] == 0) {
                    queue[r++] = next ;
                }
            }
        }
        return ans ;
    }
}
