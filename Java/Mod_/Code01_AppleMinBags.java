package Mod_;

// 买橘子问题
// https://www.nowcoder.com/practice/73e0552b78474a9086781e47f4e01d73
public class Code01_AppleMinBags {

    // 暴力递归
    public static int bags1(int a){
        int ans = f(a) ;
        return ans == Integer.MAX_VALUE ? -1 : ans ; // 最小值为无效返回-1，否则返回ans
    }
    public static int f(int a) {
        if (a <= 0){
            return a == 0 ? 0 : Integer.MAX_VALUE ;  // 最小值 无效
        }
        // 使用8规格的袋子，剩余橘子需要几个袋子
        int p8 = f(a-8) ;
        int p6 = f(a-6) ; // 6规格的袋子剩余橘子要几个袋子
        p8 += p8 != Integer.MAX_VALUE ? 1 : 0 ;
        p6 += p6 != Integer.MAX_VALUE ? 1 : 0 ;
        return Math.min(p8,p6) ;
    }

    // 规律解法
    public static int bags2(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 18) {
            if (apple == 0) {
                return 0;
            }
            if (apple == 6 || apple == 8) {
                return 1;
            }
            if (apple == 12 || apple == 14 || apple == 16) {
                return 2;
            }
            return -1;
        }
        return (apple - 18) / 8 + 3;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ":" + bags1(i));
        }
    }
}
