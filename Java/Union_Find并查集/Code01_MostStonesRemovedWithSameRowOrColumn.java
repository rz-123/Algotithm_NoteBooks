package Union_Find并查集;

// 移除最多的同行或同列石头
// n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头
// 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头
// 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置
// 返回 可以移除的石子 的最大数量。
// 测试链接 : https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column/

import class106.HashFunction;

import java.util.HashMap;

public class Code01_MostStonesRemovedWithSameRowOrColumn {

    // 创建两个哈希map，存储列跟行第一次遇到的石头编号
    public static HashMap<Integer , Integer> rowFirst = new HashMap<>() ;
    public static HashMap<Integer , Integer> colFirst = new HashMap<>() ;

    public static int MAX = 1001 ;

    public static int[] father = new int[MAX] ;

    public static int sets ;

    // 初始化
    public static void build( int n ){
        rowFirst.clear(); // 哈希Map清空
        colFirst.clear();
        for (int i = 0; i < n; i++) {
            father[i] = i ;
        }
        sets = n ; // 最初的集合数
    }

    public static int find( int i ){
        if (i != father[i]) {
            father[i] = find(father[i]) ;
        }
        return father[i];
    }

    // 合并
    public static void union( int x , int y ){
     int fx = find(x) ;
     int fy = find(y) ;
     if (fx != fy) {
         father[fx] = fy ;
         sets -- ;
        }
    }

    public static int removeStones(int[][] stones){
        int n = stones.length ;
        build(n);
        for (int i = 0; i < n; i++) {
            // 具体位置
            int row = stones[i][0] ; // 行
            int col = stones[i][1] ; // 列
            // 如果 行map中之前没有，那就加入
            if (!rowFirst.containsKey(row)){
                rowFirst.put(row , i) ;
            } else {
                union(i , rowFirst.get(row)); // 如果之前有，那就合并。
            }
            if (!colFirst.containsKey(col)){
                colFirst.put(col , i) ;
            } else {
                union(i , colFirst.get(col));
            }
        }
        return n - sets ; // 所有石头数 减去 合并之后的集合数 就是最多需要减少的石头数。
    }
}

