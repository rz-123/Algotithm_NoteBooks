package Two_Binary二分答案法;

// 同时运行N台电脑的最长时间
// 你有 n 台电脑。给你整数 n 和一个下标从 0 开始的整数数组 batteries
// 其中第 i 个电池可以让一台电脑 运行 batteries[i] 分钟
// 你想使用这些电池让 全部 n 台电脑 同时 运行。
// 一开始，你可以给每台电脑连接 至多一个电池
// 然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 任意次
// 新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池
// 断开连接和连接新的电池不会花费任何时间。
// 注意，你不能给电池充电。
// 请你返回你可以让 n 台电脑同时运行的 最长 分钟数。
// 测试链接 : https://leetcode.cn/problems/maximum-running-time-of-n-computers/


public class Code05_MaximumRunningTimeOfNComputers {

    // 简单二分 时间复杂度O(n * log(sum))，额外空间复杂度O(1)
    public static long maxRunTime(int num , int[] arr){
        long sum = 0 ;
        for(int x : arr) {
            sum += x ; // 求累加和
        }
        long ans = 0 ; // 最终返回

        for (long l = 0 , r = sum , m ; l <= r;) {
            m = l + ((r-l) >> 1) ; // 0跟累加和的中点
            if (f(arr , num , m)){
                ans = m ;
                l = m + 1 ;
            } else {
                r = m - 1 ;
            }
        }
        return ans ;
    }

    // 传入num台电脑arr电池共同运行time时间，返回能不能做到
    public static boolean f(int[] arr , int num , long time){
        long sum = 0 ;

        for (int x : arr) {
            if (x > time) {
                num -- ; // 台数减1
            } else {
                sum += x ;  // 碎片电池累加和
            }
            if (sum >= (long)num * time) {
                return true ;
            }
        }
        return false ;
    }

    // 二分 + 分析
    public static long maxRunTime2(int num , int[] arr){
        int max = 0 ;
        long sum = 0 ;
        for (int x : arr) {
            max = Math.max(max, x); // 电池的最大值
            sum += x; // 累加和
        }
        if (sum > (long)max * num) {
            // 如果电池的累加和 大于 最大值 乘以 电脑的台数，
            // 说明 最终的供电时间 一定>=max,所以对于供电时间来说 所有电池都得拼接。
            // 那么 寻找最大运行时间就是sum/num
            return sum / num ;
        }
        // 如果小于 ， 那么运行时间就< max , 所以二分的右边界变为数组中最大值
        int ans = 0;
        for (int l = 0, r = max, m; l <= r;) {
            m = l + ((r - l) >> 1);
            if (f2(arr, num, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
    public static boolean f2(int[] arr, int num, int time) {
        // 碎片电量总和
        long sum = 0;
        for (int x : arr) {
            if (x > time) {
                num--;
            } else {
                sum += x;
            }
            if (sum >= (long) num * time) {
                return true;
            }
        }
        return false;
    }
}
