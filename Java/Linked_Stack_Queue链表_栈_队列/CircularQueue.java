package Linked_Stack_Queue链表_栈_队列;

import java.util.ArrayDeque;

// 循环队列 与 双端循环队列
public class CircularQueue {

    // Java官方用数组生成的双端循环队列
    ArrayDeque<Integer> deque = new ArrayDeque<>();

    // 构建循环队列，先进先出循环
    class MyCircularQueue {

        // 创建数组以及变量
        public int[] queue;
        public int l, r, size, limit;

        // 构造方法给数组以及变量赋值
        public MyCircularQueue(int k) {
            queue = new int[k];
            l = r = size = 0;
            limit = k; // 队列最大长度不能超过k
        }

        // 判断队列是否满了
        public boolean isFull() {
            return size == limit;
        }

        // 判断队列是否空了
        public boolean isEmpty() {
            return size == 0;
        }

        // 如果队列满了返回false
        // 如果队列没满，从右边界加入数据，返回true
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            } else {
                queue[r] = value; // 将数据复制到最右边界
                // 判断当前的r的位置，如果r是最后一位赋值之后就回到0
                // 如果不是最后一位正常，r来到r+1位置
                r = r == limit - 1 ? 0 : (r + 1);
                size++;
                return true;
            }
        }

        // 弹出数据，从头部弹出，也就是从左边界弹出
        // 为空，返回false
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            } else {
                int m = queue[l]; // 左边界节点的数据

                // 判断当前l的位置
                l = l == limit - 1 ? 0 : (l + 1);
                size--;
                return true;

            }
        }

        // 查看数据，从左边界查看不弹出
        // 返回队列头部的数字（不弹出），如果没有数返回-1
        public int Front() {
            if (isEmpty()) {
                return -1;
            } else {
                return queue[l];
            }
        }

        // 返回队尾元素
        public int Rear() {
            if (isEmpty()) {
                return -1;
            } else {
                int last = r == 0 ? (limit - 1) : (r - 1);
                return queue[last];
            }
        }
    }

    // 双端 循环队列

    class MyCircularQueue02{

        // 设变量
        public int l , r, size , limit ;
        public int[] deque02 ;
        public MyCircularQueue02(int k) {
            deque02 = new int[k];
            l = r = size = 0;
            limit = k;
        }


        // 从头部添加数据
        public boolean insertFront(int value){
            if (isFull()){
                return false ;
            } else {
                if (isEmpty()){
                    l = r = 0 ;
                    deque02[0] = value ;
                }else {
                    // 如果l == 0，此时l在左边界头部，那么l-1位置就是limit-1,在尾部
                    l = l == 0 ? (limit - 1) : (l - 1) ;
                    deque02[l] = value ;
                }
                size ++ ;
                return true ;
            }
        }

        // 从尾部添加数据
        public boolean insertLast(int value){
            if (isFull()){
                return false ;
            }else {
                if (isEmpty()){
                    l = r = 0 ;
                    deque02[0] = value ;
                } else {
                    r = r == (limit-1) ? 0 : (r + 1) ;
                    deque02[r] = value ;
                }
                size++ ;
                return true ;
            }
        }

        // 从头部弹出数据
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            } else {
                l = (l == limit - 1) ? 0 : (l + 1);
                size--;
                return true;
            }
        }

        // 从尾部弹出数据
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            } else {
                r = r == 0 ? (limit - 1) : (r - 1);
                size--;
                return true;
            }
        }

        // 从头部查看数据
        public int getFront() {
            if (isEmpty()) {
                return -1;
            } else {
                return deque02[l];
            }
        }
        // 从尾部查看数据
        public int getRear() {
            if (isEmpty()) {
                return -1;
            } else {
                return deque02[r];
            }
        }
        // 判断是否为空
        public boolean isEmpty(){
            return size == 0 ;
        }

        // 判断是否未满
        public  boolean isFull(){
            return size == limit ;
        }
    }
}