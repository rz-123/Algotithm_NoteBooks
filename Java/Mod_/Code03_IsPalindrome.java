package Mod_;

// 判断是不是回文数
public class Code03_IsPalindrome {

    public static boolean isPalindrome(long ans){
        long offset = 1 ; // 创建一个变量，
        while ( ans / offset >= 10 ){  // 当 <10时就说明到位了
            offset *= 10 ; // 把该变量的位数增加到跟ans一样长度
        }
        // 判断最左数跟最右边数是否相等，首尾判断
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
