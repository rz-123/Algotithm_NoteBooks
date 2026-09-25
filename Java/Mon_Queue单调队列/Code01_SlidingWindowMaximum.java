package Mon_Queue单调队列;

// 滑动窗口最大值（单调队列经典用法模版）
// 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧
// 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
// 返回 滑动窗口中的最大值 。
// 测试链接 : https://leetcode.cn/problems/sliding-window-maximum/

public class Code01_SlidingWindowMaximum {

    public static int MAX = 100001 ;
    public static int[] deque = new int[MAX] ;
    public static int h , t ;

    public static int[] maxSlidingWindow(int[] arr , int k) {
        h = t = 0 ;
        int n = arr.length ;
        // 形成k-1的窗口
        for (int i = 0 ; i < k - 1 ; i++) {
            // 因为该单调队列严格从大到小顺序排列
            // 所以如果要加入队列的数 大于 尾部的数
            while (h < t && arr[deque[t - 1]] <= arr[i]) {
                t--; // 尾部的数弹出
            }
            deque[t++] = i; // 不是大的 就正常压入队列
        }

            int m = n - k + 1 ; // 控制找出最大值的循环次，也就是能找几次
            int[] ans = new int[m] ; // 存放最大值大数组，最后返回
            // 找出最大值
            for (int l = 0 , r = k-1 ; l < m; l++ , r++) {
                // 队列内有数 并且 数组的右边界的数 大于等于 队列的右边界的数
                while (h < t && arr[deque[t-1]] <= arr[r]) {
                    t-- ; // 尾部弹出
                }
                deque[t++] = r ; // 否则 尾部正常加入
                // 收集该窗口的最大值，头部节点
                ans[l] = arr[deque[h]] ;
                // h移到下一个位置
                if (deque[h] == l) {
                    h++;
                }
            }
            return ans ;
    }
}
