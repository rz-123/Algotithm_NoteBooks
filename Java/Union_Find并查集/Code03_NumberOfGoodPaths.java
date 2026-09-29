package Union_Find并查集;


// 好路径的数目
// 给你一棵 n 个节点的树（连通无向无环的图）
// 节点编号从0到n-1，且恰好有n-1条边
// 给你一个长度为 n 下标从 0 开始的整数数组 vals
// 分别表示每个节点的值。同时给你一个二维整数数组 edges
// 其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边
// 好路径需要满足以下条件：开始和结束节点的值相同、 路径中所有值都小于等于开始的值
// 请你返回不同好路径的数目
// 注意，一条路径和它反向的路径算作 同一 路径
// 比方说， 0 -> 1 与 1 -> 0 视为同一条路径。单个节点也视为一条合法路径
// 测试链接 : https://leetcode.cn/problems/number-of-good-paths/

import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Comparator;

public class Code03_NumberOfGoodPaths {


    // 主流程
    public static int numberOfGoodPaths(int[] vals , int [][] edges){
        int n = vals.length ;
        build(n);
        int ans = n ;
        // 根据节点 从小到大排序，
        Arrays.sort(edges , Comparator.comparingInt(e -> Math.max(vals[e[0]], vals[e[1]])));
        for (int[] edge : edges) {
            ans += union(edge[0] , edge[1] , vals) ; // 路径相加
        }
        return ans ;
    }

    public static int MAX = 30001 ;
    public static int[] father = new int[MAX] ;
    public static int[] maxcnt = new int[MAX] ;

    public static void build( int n ){
        for (int i = 0; i < n; i++) {
            father[i] = i ;
            maxcnt[i] = 1 ;  // 最大值的数量
        }
    }

    // 查看i的代表节点
    public static int find(int i){
        if (i != father[i]) {
            father[i] = find(father[i]) ;
        }
        return father[i];
    }

    // 合并集合，谁的最大值大，谁做代表节点
    public static int union(int x, int y , int[] vals){
        int fx = find(x) ;
        int fy = find(y) ;
        int path = 0;
        // 谁大 谁做代表节点
        if (vals[fx] > vals[fy]){
            father[fy] = fx ;
        } else if (vals[fx] < vals[fy]){
            father[fx] = fy ;
        } else {
            // 两个集合相等时，计算路径条数
             path = maxcnt[fx] * maxcnt[fy];
            father[fy] = fx ;
            maxcnt[fx] += maxcnt[fy];
        }
        return path ;
    }




}
