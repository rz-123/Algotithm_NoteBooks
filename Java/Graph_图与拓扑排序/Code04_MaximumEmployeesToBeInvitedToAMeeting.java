package Graph_图与拓扑排序;

// 参加会议的最多员工数
// 一个公司准备组织一场会议，邀请名单上有 n 位员工
// 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工
// 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工
// 每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议
// 每位员工喜欢的员工 不会 是他自己。给你一个下标从 0 开始的整数数组 favorite
// 其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目
// 测试链接 : https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/

public class Code04_MaximumEmployeesToBeInvitedToAMeeting {

    // 找小环（两个节点）中的各个节点的最长链
    // 找大环（> 两个节点形成的环）中最大环的所在环的节点数量

    public static int maximumInvitations(int[] favorite){
        // favorite[a] = b 图 a -> b
        int n = favorite.length ;
        int[] indegree = new int[n] ;
        for (int i = 0 ; i < n ; i++) {
            indegree[favorite[i]]++ ;
        }

        // 队列
        int[] queue = new int[n] ;
        int l = 0 , r = 0 ;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i ;
            }
        }

        int[] deep = new int[n] ;
        while (l < r) {
            int cur = queue[l++] ;
            int next = favorite[cur] ;
            // 链的深度
            deep[next] = Math.max(deep[next] , deep[cur] + 1) ;
            if (--indegree[next] == 0) {
                queue[r++] = next ;
            }
        }

        // 运行到这，所有入度为0的节点都删除，目前还在的节点就是环上的节点

        int sumOfSmallRings = 0 ; // 小环上的总个数，中心点+延伸点
        int bigRings = 0 ; // 所有大环中最大环的中心的节点个数。

        for (int i = 0; i < n; i++) {
            // 只关心环上的点，环上的点的入度必定>0
            if (indegree[i] > 0) {
                int ringSize = 1 ;
                indegree[i] = 0 ; // 将该节点的入度设为0，
                for (int j = favorite[i] ; j != i ; j = favorite[j]) {
                    ringSize++ ;
                    indegree[j] = 0 ; // 沿途的节点的入度全部设为0
                }
                // 如果环上的节点为2，那么是小环，就找两个节点的最长链 + 加上自己两个节点
                if (ringSize == 2) {
                    sumOfSmallRings += 2 + deep[i] + deep[favorite[i]] ;
                } else {
                    // 否则就是大环，跟之前的大环选出最大，更新bigRings
                    bigRings = Math.max(bigRings , ringSize) ;
                }
            }
        }
        return Math.max(sumOfSmallRings,bigRings) ;
    }
}
