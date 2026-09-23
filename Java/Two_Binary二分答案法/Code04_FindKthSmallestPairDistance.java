package Two_Binary二分答案法;

import java.util.Arrays;

// 找出第K小的数对距离
// 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
// 给你一个整数数组 nums 和一个整数 k
// 数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length
// 返回 所有数对距离中 第 k 小的数对距离。
// 测试链接 : https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
public class Code04_FindKthSmallestPairDistance {

    // 主程序
    public static int smallestDistancePair(int[] nums , int k){
        int n = nums.length ;
        Arrays.sort(nums); // 排序

        int ans = 0 ;
        int  l = 0 , r = nums[n-1] - nums[0] , m ;
        while (l <= r){
            m = l + ((r-l) >> 1);
            if (f(nums , m) >= k){
                ans = m ;
                r = m -1 ;
            } else {
                l = m + 1 ;
            }
        }
        return ans ;
    }


    public static int  f(int[] arr , int mod){
        int ans = 0 ;
        for (int l = 0 , r = 0 ; l < arr.length ; l++) {
            // 只要 后一位 减去 当前位的差值 <= mod，r 就一直往后移
            // 必须是 while：r 要一路推到"满足 arr[r]-arr[l] <= mod 的最远位置"
            while (r + 1 < arr.length && arr[r+1] - arr[l] <= mod) {
                r++ ;
            }
            // r-l 有几个 就有几对（以 l 为左端点，右端点可选 l+1 ~ r）
            ans += r-l ;
        }
        return ans ;
    }
}
