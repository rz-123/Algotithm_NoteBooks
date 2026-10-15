package Bidirectional_BFS_双向广搜;

// 最接近目标值的子序列和
// 给你一个整数数组 nums 和一个目标值 goal
// 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal
// 也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal)
// 返回 abs(sum - goal) 可能的 最小值
// 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
// 数据量描述:
// 1 <= nums.length <= 40
// -10^7 <= nums[i] <= 10^7
// -10^9 <= goal <= 10^9
// 测试链接 : https://leetcode.cn/problems/closest-subsequence-sum/

import java.util.Arrays;

public class Code03_ClosestSubsequenceSum {

    public static int MAXN = 1 << 20 ;
    public static int[] lsum = new int[MAXN] ; // 左半边累加和数组
    public static int[] rsum = new int[MAXN] ; // 右半边累加和数组
    public static int fill ; // 数组中的位置，f函数需要返回在数组中填到的位置

    public static int minAbsDifference(int[] nums , int goal){
        int n = nums.length ;
        long min = 0 , max = 0 ;
        // 第一个 剪枝，如果数组的和 < 传进来的目标值，那么就找到了|sum-goal|的最小值
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                max += nums[i]; // 正数累加和
            } else {
                min += nums[i]; // 负数累加和
            }
        }
        // 如果正数累加和比goal小，那么最小值就是 max - goal 的 绝对值
        if (max < goal){
            return (int)Math.abs(max - goal) ;
        }
        if (min > goal) {
            return (int) Math.abs(min - goal) ;
        }

        Arrays.sort(nums); // 排序
        fill = 0 ;
        f(nums , 0 , n>>1 , 0 , lsum) ; // 左半边递归 [0...n/2]的位置
        int lsize = fill ; // 递归完 记录fill的位置
        fill = 0 ; // fill 全局游标， 上边传完了 fill重置，防止污染下边
        f(nums , n >> 1 , n , 0 , rsum); // 右半边递归 [n/2...n]
        int rsize = fill ;

        // 累加和数组排序
        Arrays.sort(lsum , 0 , lsize) ;
        Arrays.sort(rsum , 0 , rsize) ;

        int ans = Math.abs(goal) ;

        // i指针在左半边，从左往右
        // j指针在右半边，从右往左
        for (int i = 0 , j = rsize - 1 ; i < lsize ; i++) {
            // 如果 j 没越界
            // 并且
            // （目标值 减去 i位置上的数 减去 j位置的前一个数）的绝对值 <= （目标值 减去 i位置上的数 减去 j位置的数）的绝对值
            // j 就 往左移动
            while (j>0 && Math.abs(goal - lsum[i] - rsum[j-1]) <= Math.abs(goal - lsum[i] - rsum[j])){
                j-- ;
            }
            // 直到找到j位置时大于j-1位置的，就是计算j位置与i位置的距离目标值的最小绝对差值
            ans = Math.min(ans  , Math.abs(goal - lsum[i] - rsum[j])) ;
        }
        return ans ;
    }

    // 递归 [i..e]的累加和
    // 剪枝，因为上边排序后，相同的数字都在一起 所以可以分组展开
    public static void f(int[] nums , int i , int e , int s , int[] sum){
        // 如果i == e 那么将s累加和赋值给sum数组中的fill位置，fill向后移一位
        if (i == e) {
            sum[fill++] = s ;
        } else {
            // 有多少个跟i位置上的数相同
            int j =  i + 1 ;
            while (j < e && nums[j] == nums[i]){
                j++ ; // 如果后一位跟前一位相同 j++
            }
            // 分组递归 ， 0个i位置上的数向下递归，1个i位置上的数递归，2个...
            // nums[ 1 1 1 1 1 2....
            //       i         j
            for (int k = 0 ; k <= j - i ; k ++){
                f(nums , j , e , s + k * nums[i] , sum) ;
            }
        }
    }
}
