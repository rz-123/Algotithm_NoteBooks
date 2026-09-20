package Two_Pointers双指针;

// 接雨水
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water/

public class Code03_TrappingRainWater {

    // 1、用两个辅助数组 时间复杂度O(n)，额外空间复杂度O(n)
    public static int trap(int[] nums){
        int n = nums.length ;
        int[] lmax = new int[n] ; // 从左往右最大值
        int[] rmax = new int[n] ; // 从右往左的最大值
        // 从左往右数组
        lmax[0] = nums[0] ; // 最左边是nums0位置上的数
        for (int i = 1; i < n; i++) {
            // 当前位置跟lmax中的前一个位置比较 哪个数大，更新该位置
            lmax[i] = Math.max(lmax[i-1] , nums[i]) ;
        }
        // 从右往左 数组
        rmax[n-1] = nums[n-1] ; // 最右边的位置
        for (int i = n-2 ; i >= 0 ; i--) {
            // 当前位置跟rmax中的后一个位置比较 哪个数大，更新该位置
            rmax[i] = Math.max(rmax[i+1] , nums[i]) ;
        }

        int ans = 0 ;

        // nums中的数遍历，比较左边的最大值跟右边的最大值哪个小 用哪个减去该位置上的数
        // 第一个跟最后一个存不出水
        for (int i = 1; i < n-1; i++) {
            ans += Math.max(0 , Math.min(lmax[i-1] , rmax[i+1] ) - nums[i]) ;
        }
        return ans ;
    }


    // 双指针，最优解 时间复杂度O(n)，额外空间复杂度O(1)
    public static int trap2(int[] nums){
        // 设置左右指针
        int l = 1 , r = nums.length-2 ;
        // 左右最大值,目前最左跟最右是最大值
        int lmax = nums[0] , rmax = nums[nums.length-1] ;
        int ans = 0 ;
        while (l <= r) {
            // 如果左边最大值小于等于右边左边最大值，
            // 那么就用左边最大值减去左指针，得到左指针的水量，
            // 左指针移到下一位，左边最大值更新
            if (lmax <= rmax) {
                // 为什么要跟0比较，因为后边的那个数可能是负数，如果是负数就等于该位置不存水，所以直接+0
                ans += Math.max(0,lmax - nums[l]) ;
                lmax = Math.max(lmax , nums[l]) ;  // 与左指针选出最大值
                l++ ;
            } else {
                ans += Math.max(0,rmax - nums[r]) ;
                rmax = Math.max(rmax , nums[r--]) ;
            }
        }
        return ans ;
    }

}
