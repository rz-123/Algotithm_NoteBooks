package Recursion递归;

// N皇后问题
// https://leetcode.cn/problems/n-queens-ii/
public class NQueens {

    // 经典递归
    public static int totalNQueens1(int n){
        if (n<1){
            return 0 ;
        }
        return f1(0,new int[n] , n) ;
    }

    // i代表行数 ，n是几个皇后
    public static int f1(int i , int[] path , int n){
        if (i == n) {
            return 1 ;
        }
        int ans = 0 ;
        for (int j = 0; j < n; j++) {
            // 判断是不是对角线
            if (check(path , i , j)){
                // 如果不是在之前皇后的对角线上 或者不是同一列，就将j赋值给path[i]
                // 标志着 第i行的皇后在第j列
                path[i] = j ;
                ans += f1(i+1 , path , n) ;
            }
        }
        return ans ;
    }
    // 行 i ; 列 j
    public static boolean check(int[] path , int i , int j){
        // k 表示 之前的行，path[k] 表示 之前的列
        for (int k = 0; k < i; k++) {
            // 判断当前列是否在之前列上，后者判断当前数据是否在之前的皇后的对角线上
            // 判断是不是在对角线上的公式：| 当前行 - 之前行 | == | 当前列 - 之前列 |
            if (j == path[k] || Math.abs(i - k) == Math.abs(j-path[k])){
                return false ;
            }
        }
        return true ;
    }

    // 位运算,n个皇后就是n位数
    public static int totalNQueens( int n ){

        if (n < 1){
            return 1 ;
        }
        // 有几个皇后就右移几位，然后减1，变为0-n位上全是1，
        // n = 5
        // 1 << 5 = 0...100000 - 1
        // limit  = 0...011111;
        int limit = (1 << n) - 1 ;
        return f2(limit , 0 , 0 , 0 );  // 传入之前列跟对角线 现在都是0
    }
    // col：之前列的影响
    public static int f2(int limit , int col , int left , int right){
        // 如果 之前列col 全为1 等于 limit 就说明全部放完了
        if (col == limit){
            return 1 ;
        }
        // 总限制，列跟对角线
        int ban = col | left | right ; // 就是把所有的1都或到一起
        // 将ban取反，就是所有0都变为1，所以有1的地方是可以加入的
        // 又因为 取反是将32位的数全部取反，所以前边的数也会变为1，
        // 因此跟limit与一下，只要后边那几位
        // 例如：ban : 000...111000
        //     ~ban : 111...000111
        //     limit & ~ ban : 0111111 & 111...000111
        //     candidate: 000111
        int candidate = limit & (~ban) ;
        int place = 0 ; // 放置皇后的位置的尝试
        int ans = 0 ; // 一共有多少中有效的方法

        while (candidate != 0){
            place = candidate & (-candidate) ; // 取candidate最右侧的1赋值给place，
            candidate ^= place ; // 在candidate消掉最右侧1
            // 当前最右侧的1 或上 之前的列 ，
            // (之前的左对角线 或上 当前最右侧的1) 右移一位 作为现在的左对角线
            // (之前的右对角线 或上 当前最右侧的1) 左移一位 作为现在的右对角线
            ans += f2(limit , col | place , (left | place) >> 1 , (right | place) << 1) ;
        }
        return ans ;
    }
}
