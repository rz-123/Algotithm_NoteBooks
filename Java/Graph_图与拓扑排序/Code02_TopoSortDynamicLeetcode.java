package Graph_图与拓扑排序;


// 拓扑结构模版题 邻接表建图（动态方式）
// 邻接表建图（动态方式）
// 课程表II
// 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1
// 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi]
// 表示在选修课程 ai 前 必须 先选修 bi
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1]
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序
// 你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组
//* https://leetcode.cn/problems/course-schedule-ii/

import java.util.ArrayList;
import java.util.Arrays;

public class Code02_TopoSortDynamicLeetcode {

    // 邻接表建图
    public static int[] findOrder(int numCourses , int[][] prerequisites){
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>() ;
        // 建表
        for (int i = 0 ; i < numCourses ; i++) {
            graph.add(new ArrayList<>()) ;
        }
        // 入度表
        int[] indegree = new int[numCourses] ;
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++ ; // 该点的入度加1
        }

        // 队列
        int[] queue = new int[numCourses] ;
        int l = 0 ;
        int r = 0 ;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i ;
            }
        }
        int cnt = 0 ;
        while ( l < r ){
            int cur = queue[l++] ;
            cnt++ ;

            for (int next :graph.get(cur)) {
                // 如果该点的入度-- 为 0 ，就加入队列
                if (--indegree[next] == 0){
                    queue[r++] = next ; //
                }
            }
        }
     // 如果 cnt 恰好 等于 课程总数，那么就直接返回队列，
     // 否则拓扑排序存在环，不可能完成所有课程，返回空数组
        return cnt == numCourses ? queue : new int[0] ;
    }
}
