package 动态规划.一维动态规划;

// 最低票价
// 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
// 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出
// 每一项是一个从 1 到 365 的整数
// 火车票有 三种不同的销售方式
// 一张 为期1天 的通行证售价为 costs[0] 美元
// 一张 为期7天 的通行证售价为 costs[1] 美元
// 一张 为期30天 的通行证售价为 costs[2] 美元
// 通行证允许数天无限制的旅行
// 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证
// 那么我们可以连着旅行 7 天(第2~8天)
// 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费
// 测试链接 : https://leetcode.cn/problems/minimum-cost-for-tickets/

import java.util.Arrays;

public class Code02_MinimumCostForTickets {

    public static int[] durations = {1,7,30} ; // 不同的销售方式数组

    // 暴力递归
    public static int mincostTickets1(int[] days, int[] costs) {
        return f1(days, costs, 0);
    }
    // days为给定的天数，costs数组表示三种不同的价格，i为从0开始的下标
    public static int f1(int[] days , int[] costs , int i){
        // 如果 i == 天数数组的长度就说明到头了
        if ( i == days.length) {
            return 0 ;
        }
        // i : 第days[i]天有旅行
        int ans = Integer.MAX_VALUE ;
        // 三种方案 挨个试，返回最低消费
        for (int k = 0 , j = i ; k < 3 ; k++) {
            // 首先保证j在有效范围内，并且 当前的天数 + 方案中的天数 大于 j所在位置的天数，j就往后移一位
            while ( j < days.length && days[i] + durations[k] > days[j]) {
                j++ ; // j 向后移
            }
            // 加上该方案的价格 调递归，选最小
            ans = Math.min(ans , costs[k] + f1(days , costs , j)) ;
        }
        return ans ;
    }

    // 优化1：改为记忆搜索，就是将每一步计算的值存入dp数组中，下次用到直接取
    public static int mincostTickets2(int[] days , int[] costs) {
        int [] dp = new int[days.length] ;
        Arrays.fill(dp , Integer.MAX_VALUE); // 初始化 最大值
        return f2(days,costs,dp,0);
    }
    // 从顶到底的动态规划(从左往右)
    public static int f2(int[] days , int[] costs , int[] dp , int i){
        if (i == days.length) {
            return 0 ;
        }
        // 之前有值 直接返回
        if (dp[i] != Integer.MAX_VALUE) {
            return dp[i] ;
        }
        int ans = Integer.MAX_VALUE ;
        for (int k = 0 , j = i ; k < 3 ; k++){
            while ( j < days.length && days[i] + durations[k] > days[j]) {
                j++ ;
            }
            ans = Math.min(ans , costs[k] + f2(days , costs , dp , j)) ;
        }
        dp[i] = ans ; // 存储 值
        return ans ;
    }

    // 优化2：将所有的递归过程用dp数组替代
    public static int mincostTickets3(int[] days , int[] costs) {
        int n = days.length ;
        int[] dp = new int[days.length + 1] ;
        Arrays.fill(dp,0,n+1 , Integer.MAX_VALUE);
        dp[n] = 0 ;
        // 从右往左推，从底到顶
        for (int i = n-1 ; i >= 0 ; i--) {
            for (int k = 0 , j = i ; k < 3 ; k++) {
                while (j < days.length && days[i] + durations[k] > days[j]) {
                    j++ ;
                }
                dp[i] = Math.min(dp[i] , costs[k] + dp[j]) ;
            }
        }
        return dp[0];
    }






}
