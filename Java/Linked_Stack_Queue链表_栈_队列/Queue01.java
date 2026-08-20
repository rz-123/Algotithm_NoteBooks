package Linked_Stack_Queue链表_栈_队列;


import java.util.LinkedList;
import java.util.Queue;

// 队列 先进先出、排队
public class Queue01 {
    public static class Queue1 {

        // java中的双向链表LinkedList
        // 单向链表就足够了
        public Queue<Integer> queue = new LinkedList<>();

        // 调用任何方法之前，先调用这个方法来判断队列内是否有东西
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        // 向队列中加入num，加到尾巴
        public void offer(int num) {
            queue.offer(num);
        }

        // 从队列拿，从头拿
        public int poll() {
            return queue.poll();
        }

        // 返回队列头的元素但是不弹出
        public int peek() {
            return queue.peek();
        }

        // 返回目前队列里有几个数
        public int size() {
            return queue.size();
        }

    }


    // 用数组形成队列
    public static class Queue2 {

        public int[] queue;
        public int l;
        public int r;

        // 加入操作的总次数上限是多少，一定要明确
        public Queue2(int n) {
            queue = new int[n];
            l = 0;
            r = 0;
        }

        // 调用任何方法之前，先调用这个方法来判断队列内是否有东西
        public boolean isEmpty() {
            return l == r;
        }

        public void offer(int num) {
            queue[r++] = num;
        }

        public int poll() {
            return queue[l++];
        }

        // ?
        // l...r-1 r
        // [l..r)
        public int head() {
            return queue[l];
        }

        public int tail() {
            return queue[r - 1];
        }
        public int size() {
            return r - l;
        }
    }

}
