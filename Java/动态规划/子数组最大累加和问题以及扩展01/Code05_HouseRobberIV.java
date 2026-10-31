package 动态规划.子数组最大累加和问题以及扩展01;

// 打家劫舍 IV
// 沿街有一排连续的房屋。每间房屋内都藏有一定的现金
// 现在有一位小偷计划从这些房屋中窃取现金
// 由于相邻的房屋装有相互连通的防盗系统，所以小偷不会窃取相邻的房屋
// 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额
// 给你一个整数数组 nums 表示每间房屋存放的现金金额
// 第i间房屋中放有nums[i]的钱数
// 另给你一个整数k，表示小偷需要窃取至少 k 间房屋
// 返回小偷需要的最小窃取能力值
// 测试链接 : https://leetcode.cn/problems/house-robber-iv/

public class Code05_HouseRobberIV {

    // 二分 + 动态规划
    public static int minCapability(int[] nums , int k){
        int n = nums.length , r = nums[0] , l = nums[0] ;
        for (int i = 1 ; i < n ; i++) {
            r = Math.max(r, nums[i]) ; // 最大值
            l = Math.min(l, nums[i]) ; // 最小值
        }
        int m , ans = 0 ;
        while (l <= r) {
            m = (r + l) / 2 ; // 中点值
            // 如果返回的能力值 >= 传入的盗取房间数，那么就去左边二分
            if (mostRob1(nums , n , m ) >= k){
                ans = m ; // 记录能力值
                r = m - 1 ;
            } else {
                l = m+1 ;
            }
        }
        return ans ;
    }
    // 盗贼能力为ability时，返回盗贼最多盗取多少间房屋
    public static int mostRob1(int[] nums, int n, int ability){
        // 边界
        if (n == 1){
            return nums[0] <= ability ? 1 : 0 ;
        }
        if (n == 2) {
            // 两个只要有一个小于盗贼能力，就返回能盗窃一间房，因为相邻的房间不能偷
            return (nums[0] <= ability || nums[1] <= ability)?1:0 ;
        }
        int[] dp = new int[n] ;
        dp[0] = nums[0] <= ability ? 1 : 0;
        dp[1] = (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;

        for (int i = 2; i < n; i++) {
            // dp[i-1] 不要当前数
            // dp[i-2] 要当前数，需要判断当前数是否小于等于盗贼的能力值，
            dp[i] = Math.max(dp[i-1] , (nums[i] <= ability ? 1 : 0) + dp[i-2]) ;
        }
        return dp[n-1];
    }

    // 空间压缩,用pre，prepre替代dp
    public static int mostRob2(int[] nums, int n, int ability){
        // 边界
        if (n == 1){
            return nums[0] <= ability ? 1 : 0 ;
        }
        if (n == 2) {
            // 两个只要有一个小于盗贼能力，就返回能盗窃一间房，因为相邻的房间不能偷
            return (nums[0] <= ability || nums[1] <= ability)?1:0 ;
        }
//        int[] dp = new int[n] ;
        int prepre = nums[0] <= ability ? 1 : 0; // 前两位
        int pre = (nums[0] <= ability || nums[1] <= ability) ? 1 : 0; // 前一位

        for (int i = 2; i < n; i++) {
            // dp[i-1] 不要当前数
            // dp[i-2] 要当前数，需要判断当前数是否小于等于盗贼的能力值，
            int cur = Math.max(pre , (nums[i] <= ability ? 1 : 0) + prepre) ;
            prepre = pre ;
            pre = cur ; // 移动位置
        }
        return pre ;
    }

    // 抓紧偷
    public static int mostRob3(int[] nums, int n, int ability) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= ability) {
                ans++;
                i++;
            }
        }
        return ans;
    }

}
