package 动态规划.三维动态规划;

// 一和零(多维费用背包)
// 给你一个二进制字符串数组 strs 和两个整数 m 和 n
// 请你找出并返回 strs 的最大子集的长度
// 该子集中 最多 有 m 个 0 和 n 个 1
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
// 测试链接 : https://leetcode.cn/problems/ones-and-zeroes/

public class Code01_OnesAndZeroes {

    public static int zeros = 0 , ones = 0 ;

    // 统计字符串中有几个1和几个0
    public static void zerosAndOnes(String str){
        zeros = 0 ;
        ones = 0 ;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0'){
                zeros++ ;
            } else {
                ones++ ;
            }
        }
    }

    // 主方法
    public static int findMaxForm(String[] str , int m , int n){

//        // 动态规划，带dp表
//        int[][][] dp = new int[str.length][m+1][n+1] ;
//        for (int i = 0; i < str.length; i++) {
//            for (int j = 0; j <= m; j++) {
//                for (int k = 0; k <= n; k++) {
//                    dp[i][j][k] = -1 ; // dp表初始化都为-1
//                }
//            }
//        }
//        return f2(str , 0 , m , n , dp) ; // 带dp表
        // return f1(str , 0 , m , n) ; // 暴力递归
        // return f3( str ,  m,  n) ; // 三维严格位置依赖
        return f4(str ,  m,  n) ;   // 三维压缩二维
    }


    // 递归
    // 从str[i...]自由选择，0不超过m个，1不超过n个,最多能多少个字符串
    public static int f1(String[] strs, int i, int m, int n){
        // 边界
        if (i == strs.length) {
            return 0 ;
        }
        // 情况1：不使用str[i]，所以从i+1的地方开始递归
        int p1 = f1(strs , i+1 , m , n) ;
        // 情况2：使用str[i]，
        // 先统计传进来的字符串有多少个0和1，在保证不超过m和n的情况下调用递归
        zerosAndOnes(strs[i]);
        int p2 = 0 ;
        if (zeros <= m && ones <= n){
            p2 = f1(strs , i+1 , m-zeros , n - ones) + 1;
        }
        return Math.max(p1,p2);
    }

    private static int f2(String[] strs, int i, int m, int n, int[][][] dp) {
        // 边界
        if (i == strs.length) {
            return 0 ;
        }
        if (dp[i][m][n] != -1) {
            return dp[i][m][n];
        }
        // 情况1：不使用str[i]，所以从i+1的地方开始递归
        int p1 = f2(strs , i+1 , m , n , dp) ;
        // 情况2：使用str[i]，
        // 先统计传进来的字符串有多少个0和1，在保证不超过m和n的情况下调用递归
        zerosAndOnes(strs[i]);
        int p2 = 0 ;
        if (zeros <= m && ones <= n){
            p2 = f2(strs , i+1 , m-zeros , n - ones , dp) + 1;
        }
        int ans = Math.max(p1,p2);
        dp[i][m][n] = ans ;
        return ans ;
    }

    // 严格路径依赖，三维
    public static int f3(String[] strs, int m, int n){
        int len = strs.length ;
        int[][][] dp = new int[len + 1] [m+1] [n+1] ;
        // 三维，从上层往下层填写
        for (int i = len - 1; i >= 0 ; i--) {
            zerosAndOnes(strs[i]);
            for (int j = 0 , p1 , p2; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    p1 = dp[i+1][j][k] ;
                    p2 = 0 ;
                    if (zeros <= j && ones <= k) {
                        p2 = 1 + dp[i + 1][j - zeros][k - ones];
                    }
                    dp[i][j][k] = Math.max(p1,p2) ;
                }
            }
        }
        return dp[0][m][n];
    }

    // 一张二维表滚动遍历
    public static int f4(String[] str , int m , int n){
        int[][] dp = new int[m + 1][n + 1];

        // 每个字符串遍历
        for(String s : str) {
            zerosAndOnes(s);
            // 因为是二维表滚动遍历，当计算下层表时，该表现在的数据就是上层表的数据，所以省略了dp[i+1][m][n]

            // 要保证不超过m和n  并且 还要保证 不少于现有0和1个数
            for (int z = m ; z >= zeros ; z--){
                for (int o = n ; o >= ones ; o--){
                    dp[z][o] = Math.max(dp[z][o] , 1 + dp[z-zeros][o-ones]) ;
                }
            }
        }
        return dp[m][n] ;
    }




}
