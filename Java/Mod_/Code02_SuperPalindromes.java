package Mod_;

import javax.lang.model.element.NestingKind;

// 超级回文数
// 如果一个正整数是回文数并且它开平方也是回文数，那么它就是超级回文数
// 现在，给定两个正整数 L 和 R （以字符串形式表示），
// 返回包含在范围 [L, R] 中的超级回文数的数目。
// 1 <= len(L) <= 18
// 1 <= len(R) <= 18
// L 和 R 是表示 [1, 10^18) 范围的整数的字符串
public class Code02_SuperPalindromes {
    // 判断在[l-r]返回内有几个超级回文数
    public static int superpalindromesInRange(String left , String right){
        long l = Long.valueOf(left) ;
        long r = Long.valueOf(right) ; // 将字符串转换成long类型
        long limit = (long) Math.sqrt((double) r); // 右边界开根号，防止溢出

        long seed = 1;
        // num : 根号x，num^2 -> x
        long num = 0;
        int ans = 0;
        do {
            //  seed生成偶数长度回文数字
            // 123 -> 123321
            num = evenEnlarge(seed);
            if (check(num * num, l, r)) {
                ans++;
            }
            //  seed生成奇数长度回文数字
            // 123 -> 12321
            num = oddEnlarge(seed);
            if (check(num * num, l, r)) {  // 判断该回文数的平方在不在l-r范围上并且判断它的平方是不是回文
                ans++;
            }
            // 123 -> 124 -> 125
            seed++;
        } while (num < limit);
        return ans;
    }
    // 根据种子扩充到偶数长度的回文数字并返回
    public static long evenEnlarge(long seed) {
        long ans = seed;
        while (seed != 0) {
            ans = ans * 10 + seed % 10;
            seed /= 10;
        }
        return ans;
    }

    // 根据种子扩充到奇数长度的回文数字并返回
    public static long oddEnlarge(long seed) {
        long ans = seed;
        seed /= 10;
        while (seed != 0) {
            ans = ans * 10 + seed % 10;
            seed /= 10;
        }
        return ans;
    }
    // 判断ans是不是在l-r范围的回文数
    public static boolean check(long ans , long l , long r){
        return ans >= l && ans <= r && isPalindrome(ans) ;
    }
    // 判断该数是不是回文数
    public static boolean isPalindrome(long ans){
        long offset = 1 ; // 创建一个变量，
        while ( ans / offset >= 10 ){
            offset *= 10 ; // 把该变量的位数增加到跟ans一样长度
        }
        // 判断最左数跟最右边数是否相等
        while (ans!=0){
            if (ans / offset != ans % 10){
                return false ; // 如果不相等就肯定不是
            }
            ans = (ans % offset) / 10 ; // 最左跟最右消掉
            offset /= 100 ; // offset也减去两位,再回去循环
        }
        return true ;
    }
}
