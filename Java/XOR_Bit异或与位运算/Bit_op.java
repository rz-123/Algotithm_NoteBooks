package XOR_Bit异或与位运算;


///题目1 判断一个整数是不是2的幂
/// 题目2 判断一个整数是不是3的幂
/// 题目3 返回大于等于n的最小的2的幂
/// 题目4 区间[0, right]内所有数字 & 的结果
///
/// 题目5 反转一个二进制的状态，不是0变1、1变0，是逆序。超自然版
/// 题目6 返回一个数二进制中有几个1。
public class Bit_op {

    // 判断一个整数是不是2的幂
    // 也就是提取最右侧的1
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & -n);
    }
    // 判断一个整数是不是3的幂
    public static boolean isPowerOfThree( int n ){
        return n > 0 && 1162261467 % n == 0 ;
    }
    // 返回大于等于n的最小的2的幂
    public static final int near2power(int n){
        if(n <= 0){
            return 1;  // 如果n小于等于0  那么n的最小的2次幂都1 2的0次幂
        }
        n -- ;
        // 以下五行代码就是把n-- 的右边部分全部或成1
        n = n | n >>> 1 ;
        n |= n >>> 2 ;
        n |= n >>> 4 ;
        n |= n >>> 8 ;
        n |= n >>> 16 ;
        return n + 1; // 最后加个1
    }

    // 区间[left, right]内所有数字 & 的结果
    public static int rangBitwiseAnd(int left , int right){
        while (left < right){
            // left < right,那么right最右侧的1留不下，就减去，
            // 此时right来到right-最右侧1的位置，在这个范围里在找最右侧1
            // 一直到- ，直到减到right <= left 就返回该right
            right -= right & (-right) ;
        }
        return right ;
    }

    // 反转一个二进制的状态，不是0变1、1变0，是逆序。超自然版
    // 00111011 反转 11011100
    public static int reverBits(int n){
        // a == 00001010   5 == 00000101  0x是十六进制  一位一位的换
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        // c == 00001100    3 == 00000011   两位两位的换
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        // f0 == 11110000        0f == 00001111  四位四位的换
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        // ff00 == 1111 1111 0000 0000    00ff == 0000 0000 1111 1111
        // 八位八位的换
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        // 最后整体换过来
        n = (n >>> 16) | (n << 16);
        return n;
    }

    // 返回一个数二进制中有几个1。
    // 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
    public static int hamming(int x , int y) {
        return hemmingDistance(x ^ y ) ;
    }
    public static int hemmingDistance(int n){
        // 先一位看做一个整体，然后两位看做一个整体，然后三位看做一个整体。。。
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }
}
