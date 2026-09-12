package Mod_;

// 神奇数字
// https://leetcode.cn/problems/nth-magical-number/
public class Code01_NthMagicalNumber {

    public static int nthMagicalNumber(int n, int a, int b){
        long lcm = lcm(a,b) ; // ab最小公倍数
        long ans = 0 ;  // 记录位置

        for (long l = 0 , r = (long) n * Math.min(a,b) , m = 0 ; l <= r ;){
            m = (l + r) / 2 ;  // 中点位置

            if ( m / a + m / b - m / lcm >= n){
                ans = m ;  // 标记位置
                r = m - 1 ; // 往中点左边找
            } else {
                l = m + 1 ; // 否则往右边找
            }
        }
        return (int) (ans % 1000000007) ;
    }

    // 最大公约数
    public static long gcd(long a , long b){
        return b == 0 ? a : gcd( b , a%b) ;
    }
    // 最小公倍数
    public static long lcm(long a , long b){
        return (long)a / gcd(a, b) * b ;
    }
}
