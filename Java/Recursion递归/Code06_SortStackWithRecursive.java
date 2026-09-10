package Recursion递归;

import java.util.IllegalFormatCodePointException;
import java.util.Stack;

// 递归排序一个无序栈，大的沉到底。
public class Code06_SortStackWithRecursive {

    // 主方法
    public static void sort(Stack<Integer> stack){
        int deep = deep(stack) ; // 返回栈的深度
        while (deep > 0) {
            int max = max(stack , deep);  // 返回该栈中的最大值
            int k = times(stack , deep , max) ;  // 返回该最大值有几个
            down(stack , deep , max ,k); // 将所有最大值沉底
            deep -= k ; // 深度减去最大值的数量，循环
        }
    }

    // 返回栈的深度
    public static int deep(Stack<Integer> stack){
        if (stack.isEmpty()){
            return 0 ;
        }
        int num = stack.pop(); // 弹出一个元素
        int deep = deep(stack) + 1 ; // 当递归到栈内为空返回时，每一层加一
        stack.push(num) ; // 将弹出的元素重新压入
        return deep ;  // 最终返回层数
    }

    // 返回栈中最大值
    public static int max(Stack<Integer> stack , int deep){
        if (deep == 0) {
            return Integer.MIN_VALUE ; // 递归到底时，返回整数最小值
        }
        int num = stack.pop() ; // 弹出元素
        int restMax = max(stack , deep - 1) ; // 递归每一层深度减1
        int max = Math.max(num , restMax) ; // 递归到底之后，返回的过程中 在num跟下一层的数据选出最大值，赋值给max
        stack.push(num) ;
        return max ; // 最终返回最大值
    }

    // 栈中最大值有几个
    public static int times(Stack<Integer> stack , int deep , int max){
        if (deep == 0){
            return 0 ;
        }
        int num = stack.pop(); // 弹出元素
        int restTimes = times(stack , deep-1 , max) ; // 递归
        int times = restTimes + (num == max ? 1 : 0) ;  // 递归到底后返回判断 弹出的元素是否等于最大值，等于就加1
        stack.push(num) ; // 将元素压入栈
        return times ;  // 最终返回最大值的数量
    }

    // 传入栈、栈的深度、最大值、最大值的数量
    // 要求将所有最大值沉入栈底，其他元素顺序不变
    public static  void down(Stack<Integer> stack , int deep , int max , int k) {
        // 如果递归到栈空，就将k个max压入栈
        if (deep == 0) {
            for (int i = 0; i < k; i++) {
                stack.push(max);
            }
        } else {
            int num = stack.pop();
            down(stack, deep - 1, max, k); // 递归
            if (num != max) {  // 因为上边的代码max已经压入栈底，所以往上返回的时候不需要在压最大值
                stack.push(num);
            }
        }
    }

    // 创建随机栈
    public static Stack<Integer> randomStack(int n , int k){
        Stack<Integer> stack = new Stack<>() ;
        for (int i = 0; i < n; i++) {
            stack.add((int) (Math.random() * k));
        }
        return stack ;
    }
}
