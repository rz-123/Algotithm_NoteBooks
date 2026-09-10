package Recursion递归;

import java.util.Enumeration;
import java.util.Stack;

// 用递归逆序一个栈。时间复杂度O(n^2)
public class Code05_ReverseStackWithRecursive {

    // 主方法
    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int num = bottomOut(stack); // 不断获取栈底元素
        reverse(stack); // 递归
        stack.push(num) ;  // 完成后压入栈
    }

    // 栈底的元素移除掉，上边的元素落下来，最终返回栈底元素
    public static int bottomOut(Stack<Integer> stack){
        int ans = stack.pop(); // 弹出栈顶元素
        if (stack.isEmpty()){
            return ans ;
        } else {
            int last = bottomOut(stack) ;
            stack.push(ans) ;  // 将上边的元素落下来
            return last ; // 返回栈底元素
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }


}
