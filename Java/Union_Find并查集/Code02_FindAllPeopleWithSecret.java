package Union_Find并查集;

// 找出知晓秘密的所有专家
// 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号
// 另外给你一个下标从 0 开始的二维整数数组 meetings
// 其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和专家 yi 在时间 timei 要开一场会
// 一个专家可以同时参加 多场会议 。最后，给你一个整数 firstPerson
// 专家 0 有一个 秘密 ，最初，他在时间 0 将这个秘密分享给了专家 firstPerson
// 接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播
// 更正式的表达是，每次会议，如果专家 xi 在时间 timei 时知晓这个秘密
// 那么他将会与专家 yi 分享这个秘密，反之亦然。秘密共享是 瞬时发生 的
// 也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享
// 在所有会议都结束之后，返回所有知晓这个秘密的专家列表
// 你可以按 任何顺序 返回答案
// 链接测试 : https://leetcode.cn/problems/find-all-people-with-secret/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code02_FindAllPeopleWithSecret {

    public static int MAX = 100001 ;
    public static int[] father = new int[MAX] ;
    // 打标签数组，表示 集合的属性
    public static boolean[] secret = new boolean[MAX] ;

    public static void build(int n , int first){
        for (int i = 0; i < n; i++) {
            father[i] = i ;
            secret[i] = false ;
        }
        father[first] = 0 ;  // 0号知道秘密
        secret[0] = true ; // 将0号集合的属性变为true
    }

    public static int find(int i){
        if (i != father[i]) {
            father[i] = find(father[i]) ;
        }
        return father[i];
    }

    public static void union(int x, int y){
        int fx = find(x) ;
        int fy = find(y) ;
        if (fx != fy) {
            father[fx] = fy ;
            secret[fy] |= secret[fx];  // 合并集合后，标签 或 进去，双方有一个True就都为True
        }
    }

    public static List<Integer> findAllPeople(int n , int[][] meetings , int first){
        build(n , first); // 初始化

        // 按照 时间进行排序
        // {0 : 专家   1 : 专家编号   2 : 时刻}
        Arrays.sort(meetings , (int[] a , int[] b) -> a[2] - b[2]);
        int m = meetings.length ;
        for (int l = 0 , r; l < m ;) {
            r = l ;
            // 找同一个时刻的会议
            while (r+1 < m && meetings[l][2] == meetings[r+1][2]) {
                r++ ;
            }
            // 同一个时刻的进行合并
            for (int i = l ; i <= r ; i++){
                union(meetings[i][0] , meetings[i][1]);
            }
            // 标签不为True 每个元素分解成单独的一个集合
            for (int i = l , a , b ; i <= r  ; i++){
                a = meetings[i][0] ;
                b = meetings[i][1] ;
                if (!secret[find(a)]) {
                    father[a] = a ;
                }
                if (!secret[find(b)]) {
                    father[b] = b ;
                }
            }
            l = r + 1 ;
        }
        List<Integer> ans = new ArrayList<>() ;
        for (int i = 0 ; i < n ; i++){
            if (secret[find(i)]){
                ans.add(i) ;
            }
        }
        return ans ;
    }
}
