package Two_Binary二分答案法;



// 完成旅途的最少时间(题目6的在线测试)
// 有同学找到了在线测试链接，和课上讲的题目6几乎是一个意思，但是有细微差别
// 实现的代码，除了一些变量需要改成long类型之外，仅有两处关键逻辑不同，都打上了注释
// 除此之外，和课上讲的题目6的实现，再无区别
// 可以仔细阅读如下测试链接里的题目，重点关注此题和题目6，在题意上的差别
// 测试链接 : https://leetcode.cn/problems/minimum-time-to-complete-trips/

public class Code06_WaitingTime2 {

    // 还是找出最小值，然后确定二分的右边界为最小时间 * 需要完成的旅途数目

    public static long minimumTime(int[] arr , int w){
        int min = Integer.MAX_VALUE ;
        for (int x : arr) {
            min = Math.min(min , x) ;
        }
        long ans = 0 ;
        for (long l = 0 , r = (long)min * w , m ; l <=r ; ) {
            m = l + ((r-l) >> 1);
            if (f(arr , m) >= w){
                ans = m ;
                r = m - 1 ;
            } else {
                l = m + 1 ;
            }
        }
        return ans ;
    }
    // 给定中点时间，问该时间段多少辆车能完成旅途
    public static long f(int[] arr , long m) {
        long ans = 0 ;
        for (int x : arr){
            ans += (m/x) ;
        }
        return ans ;
    }
}
