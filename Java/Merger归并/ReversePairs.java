package Merger归并;

// 翻转对
// 一个数组，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//你需要返回给定数组中的重要翻转对的数量。
// https://leetcode.cn/problems/reverse-pairs/
public class ReversePairs {

    // 跟小和问题差不多  merge里边的比较条件改了
    public static int MAXN = 50001;
    public static int[] help = new int[MAXN] ;

    // merge
    public static int merge(int[] arr , int l , int m , int r){
        // 统计个数
        int ans = 0 ;
        for (int i = l , j = m + 1 ; i <= m ; i++){
            while (j <= r && (long) arr[i] > (long) arr[j] * 2){
                j ++ ; // 右边往后移一位
            }
            // 根据每个i判断有几个j满足上边while条件
            ans += j - m - 1 ;
        }
        // 排序
        int a = l ;
        int x = l ;
        int y = m + 1 ;
        while (x <= m && y <= r){
            help[a++] = arr[x] <= arr[y] ? arr[x++] : arr[y++];
        }
        while (x <= m) {
            help[a++] = arr[x++];
        }
        while (y <= r) {
            help[a++] = arr[y++];
        }
        for (a = l; a <= r; a++) {
            arr[a] = help[a];
        }
        return ans;
    }

    // 递归
    public static int counts(int[] arr , int l , int r ) {
        // 判断最小单位
        if (l == r) {
            return 0 ;
        }
        int m = (l + r) >> 1 ;
        return counts(arr , l , m) + counts(arr , m+1 , r) + merge(arr , l , m , r) ;
    }

}
