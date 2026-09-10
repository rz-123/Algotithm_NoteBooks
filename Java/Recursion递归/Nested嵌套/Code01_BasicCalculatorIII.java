package Recursion递归.Nested嵌套;

import java.util.ArrayList;

// 表达式求值：设计计算器，加减乘除功能
// https://www.nowcoder.com/practice/c215ba61c8b1443b996351df929dc4d4
public class Code01_BasicCalculatorIII {

    // 设置全局变量 where,遇到左括号，标记地点，递归从where加1开始
    public static int where ;
    public static int solve(String string){
        where = 0 ;
        return f(string.toCharArray() , 0) ; // 字符串转为数组
    }

    public static int f(char[] chars , int i){
        int cur = 0 ;

        ArrayList<Integer> numbers = new ArrayList<>() ; // 存放数字
        ArrayList<Character> ops = new ArrayList<>() ;  //  存放运算符 +-

        // 判断条件
        while (i < chars.length && chars[i] != ')'){
            // 获取数字
            if (chars[i] >= '0' && chars[i] <= '9'){
                cur = cur * 10 + chars[i++] - '0' ; //
            } else if (chars[i] != '('){ // 如果没碰到括号
                push(numbers , ops , cur , chars[i++]); // 碰到运算符
                cur = 0 ; // 将临时变量清空
            } else { // 碰到左括号（
                cur = f(chars , i+1) ; // 递归
                i = where + 1 ; //
            }
        }
        push(numbers , ops , cur , '+');  // 最后 数字栈还剩一个数，加上
        where = i ;
        return compute(numbers , ops) ;
    }

    // 碰到 + 或 - , 就将符号压入ops栈，将数字压入numbers栈；
    // 碰到 * 或 / ，就先计算出值再压入栈
    // 传入两个栈，cur；数值，op：符号
    public static void push(ArrayList<Integer> numbers, ArrayList<Character> ops, int cur, char op){
        int n = numbers.size() ;
        // 如果数字栈内为空 或 符号栈内最顶端的符号为+或者-，就直接压入
        if (n == 0 || ops.get(n-1) == '+' || ops.get(n-1) == '-'){
            numbers.add(cur) ; // 数字压入数字栈
            ops.add(op) ;  // 符号压入符号栈
        } else { // 否则就是 乘 或 除
            int topNumber = numbers.get(n-1) ; // 获取数字栈中最顶端元素
            Character c = ops.get(n - 1); // 获取最顶端的符号
            if (c == '*'){
                numbers.set(n-1 , topNumber * cur) ;  // 俩数相乘，替换到最顶端的元素
            } else {
                numbers.set(n-1 , topNumber / cur) ;  // 俩数相除，替换到最顶端的元素
            }
            ops.set(n-1 , op);
        }
    }

    // 最终计算结果
    public static int compute(ArrayList<Integer> numbers, ArrayList<Character> ops){
        int n = numbers.size() ; // 计算出该栈的总数
        int ans = numbers.get(0) ;  // 总值

        // 从低到顶，判断符号栈是相加还是相减
        for (int i = 1; i < n; i++) {
            ans += ops.get(i-1) == '+' ? numbers.get(i) : -numbers.get(i) ;
        }
        return ans ; // 完成后返回总值
    }
}
