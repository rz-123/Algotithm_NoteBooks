package Bidirectional_BFS_双向广搜;

// 牛牛的背包问题 & 世界冰球锦标赛
// 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
// 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
// 牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。
// 输入描述：
// 输入包括两行
// 第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量
// 第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积
// 输出描述：
// 输出一个正整数, 表示牛牛一共有多少种零食放法。
// 测试链接 : https://www.luogu.com.cn/problem/P4799


import java.io.*;
import java.util.Arrays;

public class Code02_SnacksWaysBuyTickets {

    public static int MAXN = 40;

    public static int MAXM = 1 << 20; // 2的20次方

    public static long[] arr = new long[MAXN];

    // 左半边
    public static long[] lsum = new long[MAXM];
    // 右半边
    public static long[] rsum = new long[MAXM];

    public static int n;

    public static long w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval ; // 比赛的数量
            in.nextToken() ;
            w = (long)in.nval ; // 资金
            for (int i = 0; i < n; i++) {
                in.nextToken() ;
                arr[i] = (long)in.nval ; // 每场 比赛需要的资金
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long compute(){
        int lsize = f(0 , n>>1 , 0 , w , lsum , 0) ;
        int rsize = f(n >> 1 , n , 0 , w , rsum , 0) ;
        // 两个数组排序
        Arrays.sort(lsum , 0 , lsize);
        Arrays.sort(rsum , 0 , rsize);
        long ans = 0 ;
        // 左半边的指针i从右往左走，右半边指针j从左往右走。
        for (int i = lsize - 1 , j = 0 ; i >= 0 ; i--) {
            // 当右半边的指针j没越界  并且 左右两边指针位置上的值 小于 总资金
            // 那么 右半边指针j往右走j++
            while (j < rsize && lsum[i] + rsum[j] <= w) {
                j++ ;
            }
            ans += j ; // 记录j的位置 与之前 ans相加 最终返回ans，就是全部方案
        }
        return ans ;
    }

    // f 递归函数
    // arr[i..e-1]的范围展开 到e就停止，ans数组的累加和到了什么位置
    // w：门票资金，ans : 门票资金累加和数组，s：从0开始累加
    public static int f(int i, int e, long s, long w, long[] ans, int j){
        if (s > w) {
            return j ;
        }
        if (i == e) {
            ans[j++] = s ;
        } else {
            // 不要i位置的数
            j = f(i+1 , e , s , w , ans , j) ;
            // 要i位置的数
            j = f(i+1 , e , s + arr[i] , w , ans , j) ;
        }
        return j ;
    }
}
