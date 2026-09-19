package Sliding_Window滑动窗口;

import javax.swing.plaf.metal.MetalTheme;

// 累加和大于等于target的最短子数组长度
// 给定一个含有 n 个正整数的数组和一个正整数 target
// 找到累加和 >= target 的长度最小的子数组并返回其长度
// 如果不存在符合条件的子数组返回0
// 测试链接 : https://leetcode.cn/problems/minimum-size-subarray-sum/
public class Code01_MinimumSizeSubarraySum {


    public static int minSubArrayLen(int target , int[] nums) {
        int ans = Integer.MAX_VALUE ; // 最终返回的长度
        // sum 累加和
        for (int l = 0 , r = 0 , sum = 0; r < nums.length; r++) {
            sum += nums[r] ; // 累加和 右边界
            // 如果 累加和减去左边界l 还大于 target
            // 因为要找最短子串，那么说明此时的左边界的数值不需要了
            // sum-nums[l]，l++,累加和减去左边界的值，l向后走一位
            while (sum - nums[l] >= target){
                sum = sum - nums[l] ;
                l++ ;
                // sum -= nums[l++] ;
            }
            // 如果满足条件，更新ans，此时的长度与之前的ans比较选出最小的值
            if (sum >= target) {
                ans = Math.min(ans , r - l + 1) ;
            }
        }
        return ans ;
    }
}
