package Linked_Stack_Queue链表_栈_队列;


import java.util.ArrayList;

// 用栈实现，要求每次弹出数据都是该栈中的数据最小值
public class MinStack {

    class MinStack01{
        public final int MAX = 8001 ;

        public int[] data ; // 正常栈
        public int[] min ;  // 存放最小数据的栈
        int size ;
        // 构造方法
        public MinStack01() {
            data = new int[MAX];
            min = new int[MAX];
            size = 0;
        }
        // 添加数据，在俩栈同时添加，
        public void push(int x){
            // 先给正常栈中添加数据
            data[size] = x ;

            // 如果栈为空就添加数据，或者  当前数小于目前在最小栈栈顶的数
            if (size == 0 || x <= min[size-1]){
                min[size] = x ;
            }else {
                // 如果新添加的数不小于当前数，就把前一个位置的数复制一次
                min[size] = min[size -1];
            }
            size ++ ;
        }

        // 查看最小值
        public int getMin(){
            return min[size - 1] ;
        }

        // 正常弹出数据
        public void pop() {
            size--;
        }

        // 查看正常栈顶数据
        public int top() {
            return data[size - 1];
        }
    }


    // 运用ArrayList 链表
    class Stack02{
        private ArrayList<Integer> data;
        private ArrayList<Integer> min;

        public Stack02() {
            data = new ArrayList<>();
            min = new ArrayList<>();
        }

        public void push(int x) {
            data.add(x);
            if (min.isEmpty() || x <= min.get(min.size() - 1)) {
                min.add(x);
            } else {
                min.add(min.get(min.size() - 1));
            }
        }

        public void pop() {
            data.remove(data.size() - 1);
            min.remove(min.size() - 1);
        }

        public int top() {
            return data.get(data.size() - 1);
        }

        public int getMin() {
            return min.get(min.size() - 1);
        }
    }
}
