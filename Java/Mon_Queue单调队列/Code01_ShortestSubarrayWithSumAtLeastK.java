package Mon_Queue单调队列;

// 和至少为K的最短子数组
// 给定一个数组arr，其中的值有可能正、负、0
// 给定一个正数k
// 返回累加和>=k的所有子数组中，最短的子数组长度
// 测试链接 : https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/

public class Code01_ShortestSubarrayWithSumAtLeastK {

    public static int MAXN = 100001;

    public static long[] sum = new long[MAXN] ; // 前缀和数组，sum[i]就是前i个数的前缀和

    public static int[] deque = new int[MAXN] ;

    public static int h , t ;

    public static int shortestSubarray(int[] arr , int K) {
        for (int i = 0 ; i < arr.length ; i++) {
            sum[i+1] = sum[i] + arr[i]; // 计算前缀和
        }

        h = t = 0 ;
        int ans = Integer.MAX_VALUE ;
        for (int i = 0; i <= arr.length ; i++) {
            // 如果当前的前缀和 减去 头前缀和（最小前缀和） >=k
            // 那么计算当前的前缀和与头前缀和的长度，并且单调队列的头前缀和移到后一位
            while ( h!= t && sum[i] - sum[deque[h]] >= K){
                ans = Math.min(ans , i - deque[h++]) ;
            }
            // 单调队列：从小到大
            while (h != t && sum[deque[t-1]] >= sum[i]){
                t-- ;
            }
            deque[t++] = i ; // 尾部加
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }
}
