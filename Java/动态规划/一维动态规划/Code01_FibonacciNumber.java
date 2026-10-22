package 动态规划.一维动态规划;

// 斐波那契数
// 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列
// 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
// 也就是：F(0) = 0，F(1) = 1
// F(n) = F(n - 1) + F(n - 2)，其中 n > 1
// 给定 n ，请计算 F(n)
// 测试链接 : https://leetcode.cn/problems/fibonacci-number/
// 注意：最优解来自矩阵快速幂，时间复杂度可以做到O(log n)

import java.util.Arrays;

public class Code01_FibonacciNumber {
    public static int fib(int n){
        return f1(n) ;
    }

    // 最普通的递归写法
    public static int f1( int i ){
        if (i == 0) {
            return 0 ;
        }
        if (i == 1) {
            return 1 ;
        }
        return f1(i - 1) + f1(i - 2) ;
    }

    // 优化2：根据上面的写法 会有重复计算，所以下边的重复计算部分添加一个dp数组记录
    public static int fib2(int n) {
        // 存值
        int[] dp = new int[ n + 1 ] ;
        Arrays.fill(dp , -1); // 数组中全部初始化为-1
        return f2( n , dp) ;
    }
    public static int f2(int i , int[] dp){
        if (i == 0) {
            return 0 ;
        }
        if (i == 1) {
            return 1;
        }
        // 如果 dp[i]不等于-1，那就说明之前有值，直接返回该值
        if (dp[i] != -1){
            return dp[i] ;
        }
        // 加dp数组递归
        int ans = f2( i - 1 , dp) + f2( i - 2 , dp) ;
        dp[i] = ans ; // 每个值存入dp中
        return ans ;
    }

    // 优化3：把所有递归改成dp数组承接
    public static int f3(int n) {
        int[] dp = new int[ n + 1] ;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        dp[1] = 1 ; // 第一位等于1,第0位初始化等于0
        for (int i = 2 ; i <= n ; i++) {
            dp[i] = dp[i-1] + dp[i-2] ;
        }
        return dp[n] ;
    }

    // 优化4：已知F(n) = F(n-1) + F(n-2),所以讲F(n-1) 和 F(n-2)设为两个变量，交替使用
    public static int f4(int n){
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // 两个变量 n-1和n-2
        int lastlast = 0 , last = 1 ;
        for (int i = 2 , cur; i <= n ; i++) {
            cur = lastlast + last ; // 第二位上的斐波拉契数
            // 两个变量分别向后移一位
            lastlast = last ;
            last = cur ;
        }
        return last ;
    }
}
