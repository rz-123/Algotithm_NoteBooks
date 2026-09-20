package Two_Pointers双指针;

// 寻找重复数
// 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）
// 可知至少存在一个重复的整数。
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
// 测试链接 : https://leetcode.cn/problems/find-the-duplicate-number/

public class Code02_FindTheDuplicateNumber {

    // 入环节点快慢指针：
    // 快指针一次走两步，慢指针一次走一步，他俩相遇时，快指针回到起点，慢指针留在原地，
    // 然后 快慢指针都一步一步的走，直到他俩相遇的节点就是入环节点，也就是数组中重复节点
    public static int findDuplicate(int[] nums){
        // 判断边界
        if (nums == null || nums.length < 2) {
            return -1 ;
        }
        int slow = nums[0] ; // 慢指针：slow 来到0位置上的数的位置，走一步
        int fast = nums[nums[0]] ; // 快指针： fast 来到0位置上的数的位置上的数的位置，走两步
        while (slow != fast) {
            slow = nums[slow] ;
            fast = nums[nums[fast]] ;
        }
        // 相遇之后  快指针回到起点
        fast = 0 ;

        // 这次快慢指针都走一步，当再次相遇时的节点就是重复节点
        while (slow != fast) {
            fast = nums[fast] ;
            slow = nums[slow] ;
        }
        return slow ;
    }
}
