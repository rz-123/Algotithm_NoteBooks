package 动态规划.三维动态规划;

// 盈利计划(多维费用背包)
// 集团里有 n 名员工，他们可以完成各种各样的工作创造利润
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与
// 如果成员参与了其中一项工作，就不能参与另一项工作
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划
// 并且工作的成员总数最多为 n
// 有多少种计划可以选择？因为答案很大，答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/profitable-schemes/

import static class069.Code02_ProfitableSchemes.f2;

public class Code02_ProfitableSchemes {

    public static int MOD = 1000000007;

    // n : 员工的额度，不能超
    // minProfit : 利润的额度，不能少
    // group[i] : i号项目需要几个人
    // profit[i] : i号项目产生的利润
    // 返回能做到员工不能超过n，利润不能少于p的计划有多少个
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//        int m = group.length;
//        int[][][] dp = new int[m][n + 1][minProfit + 1];
//        for (int a = 0; a < m; a++) {
//            for (int b = 0; b <= n; b++) {
//                for (int c = 0; c <= minProfit; c++) {
//                    dp[a][b][c] = -1;
//                }
//            }
//        }
//        return f2(group, profit, 0, n, minProfit, dp); // 记忆化搜索
        // return f1(group, profit, 0, n, minProfit); // 暴力递归
        return f3(n,  minProfit, group, profit) ;
    }

    // i种工作，员工额度还剩r人，利润还有s才能达标
    public static int f1(int[] g, int[] p, int i, int r, int s){
        // 边界
        // 人员额度耗尽
        if (r<=0){
            // 如果达到利润，就返回一种方案
            return s<=0?1:0; //
        }
        // 工作耗尽
        if (i == g.length) {
            return s<=0?1:0;
        }

        // 情况1：要当前工作
        int p1 = f1(g,p,i+1,r,s) ;

        // 情况2：不要当前工作
        int p2 = 0 ;
        if (g[i] <= r) {
            p2 = f1(g,p,i+1,r-g[i],s-p[i]) ;
        }
        return p1+p2 ;
    }

    public static int f2(int[] g, int[] p, int i, int r, int s, int[][][] dp) {
        // 边界
        // 人员额度好紧
        if (r<=0){
            // 如果达到利润，就返回一种方案
            return s==0?1:0; //
        }
        // 工作耗尽
        if (i == g.length) {
            return s==0?1:0;
        }
        if (dp[i][r][s] != -1) {
            return dp[i][r][s] ;
        }
        // 情况1：要当前工作
        int p1 = f2(g,p,i+1,r,s,dp) ;

        // 情况2：不要当前工作
        int p2 = 0 ;
        if (g[i] <= r) {
            p2 = f2(g,p,i+1,r-g[i],Math.max(0,s-p[i]) , dp) ;
        }
        int ans = (p1+p2) % MOD ;
        dp[i][r][s] = ans ;
        return ans ;
    }

    // 三维压缩成二维
    public static int f3(int n, int minProfit, int[] group, int[] profit){
        // 二维数组
        int[][] dp = new int[n+1][minProfit+1] ;
        // 当利润为0时，方案数始终是1个
        for (int r = 0; r <= n; r++) {
            dp[r][0] = 1 ;
        }
        int m = group.length ;
        // 从右往左，从下往上推
        for (int i = m-1; i >= 0 ; i--) {
            for (int r = n; r >= 0 ; r--) {
                for (int s = minProfit; s >= 0 ; s--) {
                    int p1 = dp[r][s] ;
                    int p2 = group[i] <= r ? dp[r-group[i]][Math.max(0,s-profit[i])] : 0;
                    dp[r][s] = (p1+p2)%MOD ;
                }
            }
        }
        return dp[n][minProfit] ;
    }


}
