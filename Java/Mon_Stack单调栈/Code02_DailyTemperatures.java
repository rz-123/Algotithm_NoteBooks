package Mon_Stack单调栈;

// 每日温度
// 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer
// 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后
// 如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 测试链接 : https://leetcode.cn/problems/daily-temperatures/

public class Code02_DailyTemperatures {

    public static int MAX = 100001 ;
    public static int[] stack = new int[MAX] ;
    public static int r ;

    public static int[] dailyTemperatures(int[] nums) {
        int n = nums.length ;
        int[] ans = new int[n] ; // 最终返回的数组
        r = 0 ; // 栈清空
        for (int i = 0 , cur; i < n; i++) {
            // 当要加入栈的元素 大于 栈顶元素时，将栈顶元素弹出
            // 也就是 小于等于 都 压入栈
            while (r > 0 && nums[stack[r-1]] < nums[i]){
                cur = stack[--r]; // 弹出栈顶元素
                ans[cur] = i - cur ; // 俩下标相减
            }
            stack[r++] = i ;
        }
        return ans ;
    }
}
