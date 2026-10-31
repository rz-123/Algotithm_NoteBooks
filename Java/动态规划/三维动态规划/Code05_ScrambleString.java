package 动态规划.三维动态规划;

// 扰乱字符串
// 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
// 步骤1 : 如果字符串的长度为 1 ，算法停止
// 步骤2 : 如果字符串的长度 > 1 ，执行下述步骤：
//        在一个随机下标处将字符串分割成两个非空的子字符串
//        已知字符串s，则可以将其分成两个子字符串x和y且满足s=x+y
//        可以决定是要 交换两个子字符串 还是要 保持这两个子字符串的顺序不变
//        即s可能是 s = x + y 或者 s = y + x
//        在x和y这两个子字符串上继续从步骤1开始递归执行此算法
// 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串
// 如果是，返回true ；否则，返回false
// 测试链接 : https://leetcode.cn/problems/scramble-string/

public class Code05_ScrambleString {

    public static boolean isScramble(String str1 , String str2){
        char[] s1 = str1.toCharArray() ; // 字符串 转成 字符数组
        char[] s2 = str2.toCharArray() ;
        int n = s1.length ;
        // return f1(s1 , s2 , 0 , n-1 , 0 , n-1 ) ; // 暴力递归
        return f2(s1 , s2 , 0 , 0 , n ) ;
    }

    // 传入两个字符数组以及各自的左右边界
    // 返回 是不是 扰乱串
    public static boolean f1(char[] s1 , char[] s2 , int l1 , int r1 , int l2 , int r2){
        // 如果俩数组里的个数是一个，那就直接判断
        if (l1 == r1) {
            return s1[l1] == s2[l2] ;
        }

        // 情况1 ：不错乱，俩数组的对应位置进行递归判断
        for (int i = l1 , j = l2 ; i < r1 ; i++ , j++){
            // s1[l1..i][i+1....r1]
            // s2[l2..j][j+1....r2]
            // 如果两边都是扰乱串，就直接返回true
            if (f1(s1 , s2 , l1 , i , l2 , j) && f1(s1 , s2 , i+1 , r1 , j+1 , r2)){
                return true ;
            }
        }

        // 情况2：错乱，一个数组前面的位置 与 另一个数组的 后面位置 递归 判断
        for (int i = l1 , j = r2 ; i < r1 ; i++ , j--) {
            // s1[l1..........i][i+1...r1]
            // s2[l2...j-1][j..........r2]
            if (f1(s1 , s2 , l1 , i , j , r2) && f1(s1 , s2 , i+1 , r1 , l2 , j-1)) {
                return true ;
            }
        }
        return false ;
    }

    // 由 四个可变参数  变为 三个，延伸的长度用len来代替
    public static boolean f2(char[] s1 , char[] s2 , int l1 , int l2 , int len){
        // 如果长度为1，直接判断是不是相等
        if (len == 1 ){
            return s1[l1] == s2[l2] ;
        }

        // 不交错情况
        for (int i = 1; i < len; i++) {
            // s1[l1 ... i] s1[l1+i ... len-i]
            // s2[l2 ... i] s1[l2+i ... len-i]
            // 最大延伸长度为len
            if (f2(s1 , s2 , l1 , l2 , i) && f2(s1 , s2 , l1+i , l2+i , len-i)) {
                return true ;
            }
        }

        // 交错情况
        // s1[l1 ... k] s1[i(l1+1) ... len - k (r2)]
        // s2[k ... l2+len-1(r2)]  s2[l2 ... len - k]
        for (int i = l1 + 1 , j = l2 + len - 1 , k = 1 ; k < len ; i++ , j-- , k++) {
            if (f2(s1 , s2 , l1 , j , k ) && f2(s1 , s2 , i, l2  , len - k )) {
                return true ;
            }
        }
        return false ;
    }

    // 记忆化检索，带dp表
    public static boolean isScramble3(String str1 , String  str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        // dp[l1][l2][len] : int 0 -> 没展开过
        // dp[l1][l2][len] : int -1 -> 展开过，返回的结果是false
        // dp[l1][l2][len] : int 1 -> 展开过，返回的结果是true
        int[][][] dp = new int[n][n][n + 1];
        return f3(s1, s2, 0, 0, n, dp);
    }
    public static boolean f3(char[] s1 , char[] s2 , int l1 , int l2 , int len , int[][][] dp){
        // 如果长度为1，直接判断是不是相等
        if (len == 1 ){
            return s1[l1] == s2[l2] ;
        }

        // 如果dp[l1][l2][len]不等于0 展开过，那么就从表中获取值没看看是不是1
        if (dp[l1][l2][len] != 0){
            return dp[l1][l2][len] == 1 ;
        }
        boolean ans = false ;
        // 不交错情况
        for (int i = 1; i < len; i++) {
            // s1[l1 ... i] s1[l1+i ... len-i]
            // s2[l2 ... i] s1[l2+i ... len-i]
            // 最大延伸长度为len
            if (f3(s1 , s2 , l1 , l2 , i , dp) && f3(s1 , s2 , l1+i , l2+i , len-i , dp)) {
                ans = true ;
                break ;
            }
        }

        // 交错情况
        if (!ans){
        // s1[l1 ... k] s1[i(l1+1) ... len - k (r2)]
        // s2[k ... l2+len-1(r2)]  s2[l2 ... len - k]
        for (int i = l1 + 1 , j = l2 + len - 1 , k = 1 ; k < len ; i++ , j-- , k++) {
            if (f3(s1 , s2 , l1 , j , k , dp) && f3(s1 , s2 , i, l2  , len - k , dp)) {
                ans = true ;
                break;
                }
            }
        }
        dp[l1][l2][len] = ans ? 1 : 0 ;
        return ans ;
    }

    public static boolean isScramble4(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        boolean[][][] dp = new boolean[n][n][n + 1];
        // 填写len=1层，所有的格子
        for (int l1 = 0; l1 < n; l1++) {
            for (int l2 = 0; l2 < n; l2++) {
                dp[l1][l2][1] = s1[l1] == s2[l2];
            }
        }
        for (int len = 2; len <= n; len++) {
            // 注意如下的边界条件 : l1 <= n - len l2 <= n - len
            for (int l1 = 0; l1 <= n - len; l1++) {
                for (int l2 = 0; l2 <= n - len; l2++) {
                    for (int k = 1; k < len; k++) {
                        if (dp[l1][l2][k] && dp[l1 + k][l2 + k][len - k]) {
                            dp[l1][l2][len] = true;
                            break;
                        }
                    }
                    if (!dp[l1][l2][len]) {
                        for (int i = l1 + 1, j = l2 + len - 1, k = 1; k < len; i++, j--, k++) {
                            if (dp[l1][j][k] && dp[i][l2][len - k]) {
                                dp[l1][l2][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

}
