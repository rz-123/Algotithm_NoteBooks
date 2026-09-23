package Two_Binary二分答案法;

// 计算等位时间
// 给定一个数组arr长度为n，表示n个服务员，每服务一个人的时间
// 给定一个正数m，表示有m个人等位，如果你是刚来的人，请问你需要等多久？
// 假设m远远大于n，比如n <= 10^3, m <= 10^9，该怎么做是最优解？

import java.io.PrintWriter;
import java.util.PriorityQueue;

public class Code06_WaitingTime {

    // 堆排序
    public static int waitingTime1(int[] arr , int m){
        // 创建小根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a ,int[] b) -> (a[0] - b[0])) ;
        for (int i = 0 ; i < arr.length ; i++){
            heap.add(new int[] {0 , arr[i]}) ; //  数组中每个数加入小根堆
        }

        // 服务员依次服务客人
        for (int i = 0; i < m; i++) {
            int[] cur = heap.poll();  // 弹出最上边的
            cur[0] += cur[1] ; // 服务员加上自己的服务时间
            heap.add(cur) ; // 服务完 再加入堆
        }
        // 前边的人都服务完后，看小根堆的最顶端的数的0位置就是我需要等待的时间
        return heap.peek()[0] ;
    }


    // 二分
    public static int waitingTime2(int[] arr , int w){
        int min = Integer.MAX_VALUE ;
        for (int x : arr) {
            min = Math.min(min , x) ;
        }
        int ans = 0 ;
        for (int l = 0 , r = min * w , m ; l <= r;) {
            m = l + ((r-l) >>1) ;
            // 如果返回的客人数大于等于 传入的客人数加我自己，就往左侧找
            if (f(arr , m) >= w+1) {
                ans = m ;
                r = m - 1;
            } else {
                l = m + 1 ;
            }
        }
        return ans ;
    }

     // 每个服务员工作时间time，问该时间能接待多少客人，
    public static int f(int[] arr  , int time){
        int ans = 0 ;
        for (int num : arr) {
            ans += (time / num) + 1 ;
        }
        return ans ;
    }
    // 对数器测试
    public static void main(String[] args) {
        System.out.println("测试开始");
        int N = 50;
        int V = 30;
        int M = 3000;
        int testTime = 20000;
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int m = (int) (Math.random() * M);
            int ans1 = waitingTime1(arr, m);
            int ans2 = waitingTime2(arr, m);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    // 对数器测试
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }
}
