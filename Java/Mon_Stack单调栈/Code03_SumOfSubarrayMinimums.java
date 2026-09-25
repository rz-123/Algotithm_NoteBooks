package Mon_Stack单调栈;

// 子数组的最小值之和
// 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
// 由于答案可能很大，答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/sum-of-subarray-minimums/

public class Code03_SumOfSubarrayMinimums {

    public static int MOD = 1000000007;

    public static int MAXN = 30001;

    public static int[] stack = new int[MAXN];

    public static int r;

    public static int sumSubarrayMins(int[] arr){
        long ans = 0 ;
        r = 0 ;

        for (int i = 0 ; i < arr.length ; i ++) {
            // 单调栈：大压小 ，重复值 弹出
            while (r>0 && arr[stack[r-1]] >= arr[i]) {
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r-1]; // 左边最近且小值
                // 弹出来的数 就是 left-1  到 i - 1 的范围里 最小的数
                ans = (ans + (long) (cur - left) * (i - cur) * arr[cur]) % MOD;
            }
            stack[r++] = i ;
        }
        // 清空栈
        while (r>0){
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r-1];
            ans = (ans + (long)(cur-left) * (arr.length - cur) * arr[cur]) % MOD ;
        }
        return (int) ans ;
    }
}
