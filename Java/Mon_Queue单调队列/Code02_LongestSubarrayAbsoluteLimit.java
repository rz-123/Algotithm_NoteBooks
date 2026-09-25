package Mon_Queue单调队列;

// 绝对差不超过限制的最长连续子数组
// 给你一个整数数组 nums ，和一个表示限制的整数 limit
// 请你返回最长连续子数组的长度
// 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit
// 如果不存在满足条件的子数组，则返回 0
// 测试链接 : https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

public class Code02_LongestSubarrayAbsoluteLimit {
    public static int MAXN = 100001;

    // 窗口内最大值的更新结构（单调队列）
    public static int[] maxDeque = new int[MAXN];

    // 窗口内最小值的更新结构（单调队列）
    public static int[] minDeque = new int[MAXN];

    public static int maxh, maxt, minh, mint; // 两个单调队列中头和尾

    public static int[] arr;

    public static int longestSubarray(int[] nums , int limit){
        maxh = maxt = minh = mint = 0 ;
        arr = nums ; // 传入的数组赋值给全局变量数组arr，后边的方法就不用传了，直接用
        int n = arr.length ;
        int ans = 0 ; // 存放距离的变量，最终返回
        for (int l = 0 , r = 0 ; l < n ; l++){
            // r位置的数永远是窗口的的下一个数
            // 所以 如果 r没超n，并且 加上r位置的数符合<=limit的条件
            while (r < n && ok(limit , nums[r])){
                push(r++); // r 加入队列，r往右扩一位
            }
            // while出来之后，[L,R)的范围是以L为开头 符合条件的最大范围
            ans = Math.max(ans , r-l) ; // 统计距离
            pop(l); // 两个单调队列的头结点弹出
        }
        return ans ;
    }

    // 判断 如果加入number，窗口中的最大值 - 最小值的绝对差是否 <= limit
    public static boolean ok(int limit , int number){
        // 最大值：如果队列不为空：最大值队列的头结点maxh 与 要进来的number 选最大值
        int max = maxh < maxt ? Math.max(arr[maxDeque[maxh]] , number) : number ;
        // 最小值：如果队列不为空：最小值队列的头结点minh 与 要进来的number 选最小值
        int min = minh < mint ? Math.min(arr[minDeque[minh]] , number) : number ;
        return max - min <= limit ;
    }

    // r位置的数字加入窗口，两个单调栈的结构进行调整
    public static void push (int r){
        // 如果要加入的数 大于等于 最大值队列的尾部的数，那么尾部的数弹出，
        // 一直弹到符合条件，在加入r
        while (maxh < maxt && arr[maxDeque[maxt-1]] <= arr[r]){
            maxt -- ;
        }
        maxDeque[maxt++] = r ; // 正常 压入队列

        // 保证最小值始终在头结点
        while (minh < mint && arr[minDeque[mint - 1]] >= arr[r]) {
            mint-- ;
        }
        minDeque[mint++] = r;
    }

    // 弹出l位置的，单调队列的h进行左移
    public static void pop(int l){
        // 要保证队列内有数，并且队列中的头结点下标 等于 位置l
        if (maxh < maxt && maxDeque[maxh] == l) {
            maxh ++ ;
        }
        if (minh < mint && minDeque[minh] == l) {
            minh ++ ;
        }
    }
}
