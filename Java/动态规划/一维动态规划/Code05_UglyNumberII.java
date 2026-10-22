package 动态规划.一维动态规划;

// 丑数 II
// 给你一个整数 n ，请你找出并返回第 n 个 丑数
// 丑数 就是只包含质因数 2、3 或 5 的正整数
// 测试链接 : https://leetcode.cn/problems/ugly-number-ii/

public class Code05_UglyNumberII {

    // 时间复杂度 O(n)
    public static int nthUglyNumber(int n){

        // 设置dp数组
        int[] dp = new int[n+1] ;
        dp[1] = 1 ;
        // i2 = 1 , i3 = 1 , i5 = 1 三个指针，此时停在下标1位置
        for (int i = 2 , i2 = 1 , i3 = 1 , i5 = 1 , a , b , c , cur ; i <= n ; i++) {
            a = dp[i2] * 2 ;
            b = dp[i3] * 3 ;
            c = dp[i5] * 5 ;
            // 分别乘上2/3/5，得到的三个数选出最小，就是丑数
            cur = Math.min(Math.min(a,b),c) ;
            // 选出的丑数 如果 是 a ， 那么i2指针向后移动一位，下边那俩也一样，
            if (cur == a){
                i2++;
            }
            if (cur == b) {
                i3++;
            }
            if (cur == c) {
                i5++;
            }
            // 将得到的丑数存在dp数组中
            dp[i] = cur ;
        }
        return dp[n] ;
    }

}
