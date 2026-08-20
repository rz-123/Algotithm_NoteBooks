package Linked_Stack_Queue链表_栈_队列;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 栈实现队列
// 队列实现栈
public class Stack_Queue {
    class MyQueue{
        // 栈实现队列，建俩栈 一个负责输入 一个负责输出
        public Stack<Integer> in ;
        public Stack<Integer> out ;

        public MyQueue(){
            in = new Stack<Integer>() ;
            out = new Stack<Integer>() ;
        }

        // 倒数据必备的两个条件：
        // out必须输出空了，in才能往out中倒数据
        // 一旦开始倒数据，in必须倒完
        private void inToOut(){
            // out为空
            if (out.empty()){
                while (!in.empty()){  // in 不为空 开始倒数据
                    out.push(in.pop());
                }
            }
        }

        // 添加数据，填到in栈，然后检查俩栈
        public void push(int vales){
            in.push(vales);
            inToOut();
        }

        // 先检查两栈以及倒数据情况，如何弹出out栈顶数据
        public int pop(){
            inToOut();
            return out.pop();
        }

        // 查看栈顶数据
        public int peek(){
            inToOut();
            return out.peek();
        }
        // 判断是否为空，全空才为空。
        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    // 用队列实现栈
    class MyStack{
        Queue<Integer> queue ;

        public MyStack() {
            queue = new LinkedList<Integer>();
        }

        // 添加数据
        public void push (int x){
            int n = queue.size();  // 先查看当前队列中数据的个数
            queue.offer(x);  // 向队列中添加数据
            for (int i = 0 ; i < n ; i ++){
                // 再接着添加队列弹出的数据
                queue.offer(queue.poll());
            }
        }
        // 弹出数据
        public int pop() {
            return queue.poll();
        }
        // 查看数据
        public int top() {
            return queue.peek();
        }
        // 判断队列是否为空
        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
