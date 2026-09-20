package Two_Pointers双指针;

// 按奇偶排序数组II
// 给定一个非负整数数组 nums。nums 中一半整数是奇数 ，一半整数是偶数
// 对数组进行排序，以便当 nums[i] 为奇数时，i也是奇数
// 当 nums[i] 为偶数时， i 也是 偶数
// 你可以返回 任何满足上述条件的数组作为答案
// 测试链接 : https://leetcode.cn/problems/sort-array-by-parity-ii/

// 按奇偶排序数组II
// 给定一个非负整数数组 nums。nums 中一半整数是奇数 ，一半整数是偶数
// 对数组进行排序，以便当 nums[i] 为奇数时，i也是奇数
// 当 nums[i] 为偶数时， i 也是 偶数
// 你可以返回 任何满足上述条件的数组作为答案
// 测试链接 : https://leetcode.cn/problems/sort-array-by-parity-ii/

public class Code01_SortArrayByParityII {

    //时间复杂度O(n),额外空间复杂度O(1)
    public static int[] sortArrayByParityII(int[] nums){
        int n = nums.length ;
        for (int odd = 1 , even = 0 ; odd < n && even < n;) {
            // 如果最后一位是奇数，就交换
            if ((nums[n-1] & 1) == 1) {
                swap(nums , odd, n-1); // 最后一位与奇数位的数交换
                odd += 2 ; // odd 指针移到下一个奇数位
            } else {
                swap(nums , even , n-1); // 否则最后一位就是偶数，那么就跟偶数位交换
                even += 2 ;
            }
        }
        return nums ;
    }

    public static void swap(int[] nums , int x, int y){
        int tmp = nums[x] ;
        nums[x] = nums[y] ;
        nums[y] = tmp ;
    }

}
