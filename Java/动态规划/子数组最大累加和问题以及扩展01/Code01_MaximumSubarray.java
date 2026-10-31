package 动态规划.子数组最大累加和问题以及扩展01;

// 子数组最大累加和
// 给你一个整数数组 nums
// 返回非空子数组的最大累加和
// 测试链接 : https://leetcode.cn/problems/maximum-subarray/

public class Code01_MaximumSubarray {

    // 动态规划
    public static int maxSubArray1(int[] nums){
        int n = nums.length ;
        // 以i位置的数做结尾，往左能延伸的最大累加和
        int[] dp = new int[n] ;
        dp[0] = nums[0] ; // 位置0的最大累加和就是位置0的数本身
        int ans = nums[0] ;
        for (int i = 1; i < n; i++) {
            // i位置上的数 与 以i位置上的数结尾往左扩最大的累加和 + i位置上的数   选最大
            dp[i] = Math.max(nums[i] , dp[i-1] + nums[i]) ;
            //
            ans = Math.max(ans , dp[i]) ;
        }
        return ans ;
    }


    // 空间压缩,用pre变量滚动
    public static int maxSubArray2(int[] nums){
        int n = nums.length ;
        int ans = nums[0] ;
        for (int i = 1 , pre = nums[0]; i < n; i++) {
            pre = Math.max(nums[i] , pre + nums[i]) ;
            ans = Math.max(ans,pre) ;
        }
        return ans ;
    }



    // 返回最大累加和的左右边界
    public static int left;

    public static int right;

    public static int sum;

    public static void extra(int[] nums){

        for (int l = 0 , r = 0 , pre = Integer.MIN_VALUE , sum = Integer.MIN_VALUE ; r < nums.length ; r++) {
            // 吸收左边的累加和 可以更大
            if (pre >= 0) {
                pre += nums[r] ;
            } else { // 吸收左边的累加和 变小，不吸收，更改左边界
                pre = nums[r] ;
                l = r ;
            }
            //
            if ( pre > sum ) {
                sum = pre ;
                left = l ;
                right = r ;
            }
        }
    }
}
