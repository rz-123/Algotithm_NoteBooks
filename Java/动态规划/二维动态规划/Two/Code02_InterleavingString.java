package 动态规划.二维动态规划.Two;

// 编辑距离
// 给你两个单词 word1 和 word2
// 请返回将 word1 转换成 word2 所使用的最少代价
// 你可以对一个单词进行如下三种操作：
// 插入一个字符，代价a
// 删除一个字符，代价b
// 替换一个字符，代价c
// 测试链接 : https://leetcode.cn/problems/edit-distance/

public class Code02_InterleavingString {

    // 动态规划
    public static int editDistance1(String str1, String str2, int a, int b, int c){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        // dp[i][j] :
        // s1[前缀长度为i]想变成s2[前缀长度为j]，至少付出多少代价
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            // 当s1[i]s2[0]时 所以s1有几个值删几个值
            // 也就是dp表的第一列已经填好
            dp[i][0] = i * b;
        }
        for (int j = 1; j <= m; j++) {
            // 当s1[0]s2[j],所以 s2的值有几个，在s1中就添加几个
            dp[0][j] = j * a; // dp表第一行
        }

        // dp表，从上到下 ， 从左往右填充
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m ; j++) {
                int p1 = Integer.MAX_VALUE ;
                int p2 = Integer.MAX_VALUE ;
                // 第一种情况：s1和s2最后一位相同，
                if (s1[i-1] == s2[j-1]){
                    p1 = dp[i-1] [j-1] ; // 左上格子
                }
                // 第二种情况：最后一位不相等
                if (s1[i-1] != s2[j-1]) {
                    p2 = dp[i-1][j-1] + c ; // 加上替换最后一位的代价
                }
                // 第三种情况：s1不要最后一位，s2要最后一位
                int p3 = dp[i][j-1] + a ; // 因为s2不要最后一位，所以要加一个插入代价
                // 第四种情况：s1要最后一位，s2不要最后一位
                int p4 = dp[i-1][j] + b ; // 因为s1不要最后一位，所以要加一个删除代价
                // 因为要求最小代价，所以四种情况选最小，放入dp表中
                dp[i][j] = Math.min(Math.min(p1,p2) , Math.min(p3,p4)) ;
            }
        }
        return dp[n][m];
    }

    // 优化2：
    public static int editDistance2(String str1, String str2, int a, int b, int c){
        char[] s1 = str1.toCharArray() ;
        char[] s2 = str2.toCharArray() ;
        int n = str1.length() , m = str2.length() ;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i * b;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j * a;
        }

        //
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 最后一位相等
                if (s1[i-1] == s2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j] + b , dp[i][j-1] + a) , dp[i-1][j-1] + c) ;
                }
            }
        }
        return dp[n][m] ;
    }

    // 优化3：空间压缩
    public static int editDistance3(String str1, String str2, int a, int b, int c){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[] dp = new int[m + 1];
        for (int j = 1 ; j<= m ; j++) {
            dp[j] = j * a ;
        }

        // 从上往下填充
        for (int i = 1,leftUp,backUp ; i <= n ; i++) {
            leftUp = (i-1) * b ; // 左上的格子
            dp[0] = i * b ; // 第一个格子
            // 从左往右 填充
            for (int j = 1; j <= m; j++) {
                backUp = dp[j] ; // 临时变量存储左边的格子
                if (s1[i - 1] == s2[j-1]){
                    dp[j] = leftUp ;
                } else {
                    dp[j] = Math.min(Math.min(dp[j] + b, dp[j - 1] + a), leftUp + c);
                }
                leftUp = backUp ; // 上边的执行完后，backUp表示的左边格子就变为左上方的格子了
            }
        }
        return dp[m];
    }

}
