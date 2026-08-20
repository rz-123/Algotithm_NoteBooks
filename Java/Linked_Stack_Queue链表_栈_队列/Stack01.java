package Linked_Stack_Queue链表_栈_队列;


import java.net.Inet4Address;
import java.util.Stack;

// 栈 先进后出
public class Stack01 {

    // 用自带的栈
    public static class StackStack{
        public Stack<Integer> stack = new Stack<>() ;
        // 执行任何操作时先检查栈是否为空
        public boolean isEmpty(){return stack.isEmpty() ;}
        // 添加数据
        public int push(int num){return stack.push(num);}
        // 弹出数据
        public int pop(){return stack.pop();}
        // 查看数据
        public int peek(){return stack.peek();}
        // 栈大小
        public int size(){return stack.size();}
    }

    // 用数组实现栈
    public static class StackArr{
        // 创建成员变量
        public int[] stack ;
        public int size ;

        public StackArr(int n){
            stack = new int[n];
            size = 0 ;
        }
        // 判断栈是否为空
        public boolean isEmpty(){
            return size == 0 ;
        }
        public void push(int num) {
            stack[size++] = num;  // num赋值在size位置，size来到size+1位置
        }

        public int pop() {
            return stack[--size];  // 先来到size-1位置，弹出该位置的数据
        }

        public int peek() {
            return stack[size - 1]; // 单纯返回size-1位置
        }
        public int size() {
            return size;
        }
    }

}
