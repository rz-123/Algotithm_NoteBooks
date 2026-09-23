package Two_Binary二分答案法;

// 分割数组的最大值(画匠问题)
// 给定一个非负整数数组 nums 和一个整数 m
// 你需要将这个数组分成 m 个非空的连续子数组。
// 设计一个算法使得这 m 个子数组各自和的最大值最小。
// 测试链接 : https://leetcode.cn/problems/split-array-largest-sum/

public class Code02_SplitArrayLargestSum {


    public static int splitArray(int[] nums , int k){
        long sum = 0 ;
        for(int num : nums){
            sum += num ;
        }
        long ans = 0 ;

        for ( long l = 0 , r = sum , m ; l <= r ; ) {
            m = l+((r-l)>>1); // 中点
            if (f(nums , m) <= k) {
                ans = m ;
                r = m - 1 ;
            } else {
                l = m + 1 ;
            }
        }
        return (int) ans ;
    }

    public static int f(int[] nums , long mod){
        int ans = 1 ; // 初始为1部分
        int sum = 0 ;

        for (int num : nums) {
            if (num > mod){
                return Integer.MAX_VALUE ;  // 如果一个数直接大于中点的累加和，直接返回整数最大值
            }
            // 如果 累加和>mod
            if (sum + num > mod){
                ans ++ ;  // 所需部分加1
                sum = num ; //
            } else {
                sum += num ; // 如果不足 就接着加num
            }
        }
        return ans;
    }


}
