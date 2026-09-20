package Two_Pointers双指针;

// 缺失的第一个正数
// 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
// 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
// 测试链接 : https://leetcode.cn/problems/first-missing-positive/

public class Code07_FirstMissingPositive {

    // 时间复杂度O(n)，额外空间复杂度O(1)
    public static int firstMissingPositive(int[] arr){
        int l = 0;  // 永远盯着L位置的数看
        int r = arr.length ; // 垃圾区

        while (l < r) {
            // 如果刚好等于，那么L向后移一位
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                swap(arr , l , --r) ;  // 送到垃圾区，垃圾区扩充
            } else {
                swap(arr , l , arr[l] - 1);
            }
        }
        return l + 1 ;
    }
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
