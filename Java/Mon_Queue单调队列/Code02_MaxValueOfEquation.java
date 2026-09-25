package Mon_Queue单调队列;


// 满足不等式的最大值
// 给你一个数组 points 和一个整数 k
// 数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序
// 也就是说 points[i] = [xi, yi]
// 并且在 1 <= i < j <= points.length 的前提下，xi < xj 总成立
// 请你找出 yi + yj + |xi - xj| 的 最大值，
// 其中 |xi - xj| <= k 且 1 <= i < j <= points.length
// 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
// 测试链接 : https://leetcode.cn/problems/max-value-of-equation/

public class Code02_MaxValueOfEquation {

    public static int MAXN = 100001;

    public static int[][] deque = new int[MAXN][2] ;

    public static  int h , t ;

    // 首先判断 后x-前x <= k
    public static int findMaxValueOfEquation(int[] [] points , int k) {
        h = t = 0 ;
        int n = points.length ;
        int ans = Integer.MIN_VALUE ; // 最终返回的值
        for (int i = 0 , x , y ; i < n; i++) {
            x = points[i][0] ; // 当前节点的x
            y = points[i][1] ; // 当前节点的y
            // 如果 单调队列中的头部节点的x - 当前的x > k
            // 那么 就将头部节点弹出
            while (h < t && deque[h][0] + k < x ) {
                h++ ; // 头部节点弹出 最大值弹出
            }
            // 单调队列中 大->小，(y与x的差值的比较)
            while (h < t && deque[t-1][1] - deque[t-1][0] <= y - x){
                t--;  // 如果 尾部节点y减去尾部节点的x <= 当前节点的y-x,那么 尾部节点弹出
            }
            deque[t][0] = x;
            deque[t++][1] = y ;
            if ( h < t) {
                ans = Math.max(ans , x + y + deque[h][1] - deque[h][0]) ;
            }
        }
        return ans ;
    }
}
