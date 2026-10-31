package 动态规划.子数组最大累加和问题以及扩展01;

// 环形数组中不能选相邻元素的最大累加和
// 给定一个数组nums，长度为n
// nums是一个环形数组，下标0和下标n-1是连在一起的
// 可以随意选择数字，但是不能选择相邻的数字
// 返回能得到的最大累加和
// 测试链接 : https://leetcode.cn/problems/house-robber-ii/

public class Code04_HouseRobberII {

    // 要0位置上的数;不要0位置的数
    public static int rob(int[] nums){
        int n = nums.length ;
        if (n == 1) {
            return nums[0] ;
        }
        // 不要0位置的数，从1开始到n-1,
        // 要0位置上的数，从2开始到n-2，因为不能取连续数字，又因为是环形，前后相连。
        return Math.max(best(nums , 1 , n-1) , nums[0] + best(nums , 2 , n-2)) ;
    }
    // 不选相邻数字，返回最大累加和
    public static int best(int[] nums , int l , int r){
        if (l>r){
            return 0;
        }
        if (l==r){
            return nums[l] ;
        }
        if (l + 1 == r){
            return Math.max(nums[l],nums[r]);
        }
        int prepre = nums[l]; // 前两位数
        int pre = Math.max(prepre, nums[l + 1]); // 前一位数
        for (int i = l + 2, cur; i <= r; i++) {
            cur = Math.max(pre, nums[i] + Math.max(0, prepre));
            prepre = pre;
            pre = cur;
        }
        return pre;
    }
}
