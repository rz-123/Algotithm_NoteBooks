package BitMap位图;

// 不用算数运算符实现加减乘除
// https://leetcode.cn/problems/divide-two-integers/description/
public class Bit_Test03 {

    // 整数最小值
    public static int MIN = Integer.MIN_VALUE ;
    // 最小值 除法
    public static int divide(int a, int b) {

        if (a == MIN && b == MIN) {
            // a和b都是整数最小
            return 1;
        }
        if (a != MIN && b != MIN) {
            // a和b都不是整数最小，那么正常去除
            return div(a, b);
        }
        if (b == MIN) {
            // a不是整数最小，b是整数最小
            return 0;
        }
        // a是整数最小，b是-1，返回整数最大，因为题目里明确这么说了
        if (b == neg(1)) {
            return Integer.MAX_VALUE;
        }
        // a是整数最小，b不是整数最小，b也不是-1
        a = add(a, b > 0 ? b : neg(b)); // 让a=a+b，a变为变为不是整数最小，a + |b|
        int ans = div(a, b);  // 调除法 (a + |b| ) / b
        // 因为前边a加了b，并且进行了除法计算，
        // 如果b>0,那么上边的ans的结果是多了一个1，要减去一个1
        // 如果b<0,那么上边的ans的结果是少了一个1，要加上一个1
        int offset = b > 0 ? neg(1) : 1;
        return add(ans, offset);
    }

    // 俩数相加
    public static int add(int a , int b) {
        int ans = a ;
        while (b != 0){
            // a和b无进位相加
            ans = a ^ b ;
            // 全0为1，右移一位，表示进位信息
            b = (a & b) << 1 ;
            // 将无进位相加信息赋值给a，循环
            a = ans ;
        }
        return ans ;
    }

    public static int minus(int a, int b) {
        // 减法：a-b == a + b的相反数
        return add(a, neg(b));
    }
    // 求相反数
    public static int neg(int n) {
        return add(~n, 1);
    }

    // 龟速乘
//    public static long multiply(long a, long b, long mod) {
//        a = (a % mod + mod) % mod;
//        b = (b % mod + mod) % mod;
//        long ans = 0;
//        while (b != 0) {
//            if ((b & 1) != 0) {
//                ans = (ans + a) % mod;
//            }
//            a = (a + a) % mod;
//            b >>= 1;
//        }
//        return ans;
//    }
    public static int multiply(int a , int b){
        int ans = 0 ;
        while (b != 0) {
            // 并且b==1时
            if ((b & 1 ) != 0){
                ans = add(ans , a) ;
            }
            // a 向左移动一位，b无符号向右移动一位
            a <<= 1 ;
            b >>>= 1 ;
        }
        return ans ;
    }

    // 除法，x/y
    // 判断一个数a中有几个b*2的31次方，几个b*2的30次方，几个b*2的29次方。。。
    public static int div(int a , int b) {
        // 不管正负，全部转成整数，但是一定不能是整数最小值
        int x = a < 0 ? neg(a) : a ;
        int y = b < 0 ? neg(b) : b ;
        int ans = 0 ;
        for (int i = 30; i >= 0 ; i = minus(i , 1)) {
            // 如果x的左移30位，相当于y * 2的30次方(y << 30)，左移防溢出
            // 就说明y中包含x * 2的30次方，ans相应的位置变1
            // 然后y减去
            if ((x >> i) >= y) {
                ans |= (1 << i) ;
                x = minus(x , y << i);
            }
        }
        // 判断a、b俩的符号
        // 如果ab俩符号不一样，就返回ans的相反数
        return a < 0 ^ b < 0 ? neg(ans) : ans ;
    }
}
