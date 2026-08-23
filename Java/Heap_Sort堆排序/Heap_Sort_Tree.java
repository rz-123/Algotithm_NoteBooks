package Heap_Sort堆排序;

import class027.Code01_MergeKSortedLists;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 三道堆排序的题
//  1、合并k个有序链表 https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6
//  2、最大重复线段数  https://leetcode.cn/problems/meeting-rooms-ii/
//  3、数组和减半的最少操作次数  https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
public class Heap_Sort_Tree {

    public static class ListNode {
        public int val;
        public ListNode next;
    }
    // 合并k个有序链表，传入带有k个头结点的数组
    public static ListNode Heap_Sort_One(ArrayList<ListNode> arr) {
        // 利用小根堆 先将数组排序，取出0位置上的数作为大链表的头结点，最后返回
        // PriorityQueue 这个类 ，用于创建完全二叉树，默认创建小根堆，所以后边的"(a , b ) -> a.val-b.val” 不写也中
        PriorityQueue<ListNode> heap = new PriorityQueue<>(( a , b ) -> a.val-b.val );
        for (ListNode h : arr) {
            // 遍历数组中的数，就是所有链表的头结点 存入小根堆
            if (h != null){
                heap.add(h) ;
            }
        }
        // 判断heap是否为空
        if (heap.isEmpty()){
            return null;
        }
        ListNode head = heap.poll() ; // 先弹出一个最小的当做头结点，后边直接返回
        ListNode pre = head ; // pre是大链表的指针，后边所有节点都挂在pre上
        // 判断刚刚弹出的节点的下一个是否为空，不为空就添加到小根堆
        if (pre.next != null) {
            heap.add(pre.next) ;
        }
        //
        while (!heap.isEmpty()){
            // 此时弹出的是小根堆弹出0位置的数排序后又出现在0位置上的数，
            // 大链表中第二小的数，
            // cur 控制 弹出的数所在的链表的指针
            ListNode cur = heap.poll();
            pre.next = cur ; // pre指向cur
            pre = cur ;  // pre来到cur位置，向后走了一位
            if (cur.next != null ) {
                heap.add(cur.next) ; // 向小根堆新增刚才弹出节点的下一个节点
            }
        }
        return head;
    }

    // 2、最大重复线段数
    // 设置全局变量 Max , n ， line 二维数组
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                line[i][0] = (int) in.nval;
                in.nextToken();
                line[i][1] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int Max = 10001 ;  // 最大值
    public static int[][] line = new int[Max][2] ;  // [行数][列数]
    public static int n ; // 实际数组大小
    public static int[] heap = new int[Max] ; // 小根堆数组
    public static int size ;  // 小根堆的大小。
    // 交换数值
    public static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    // 把右边界添加到小根堆方法
    public static void add(int x) {
        heap[size] = x ; // 把新来的x添加到最后位置
        int i = size++ ; // 随后i来到size位置，size+1.
        // 然后新来的数进行节点上浮，与父节点比较
        while(heap[i] < heap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2 ;
        }
    }
    // 小根堆弹出节点方法
    public static void pop(){
        // 最高位0位置与最低位size-1位置交换位置，然后size再-1，最高位弹出
        swap(0 , --size);
        // 新交换上来的数值进行节点下沉，跟左右孩子比较大小
        int i = 0 , l = 1 ; //l为0位置的左孩子
        while (l < size) {
            // l+1为右孩子
            int best = l + 1 < size && heap[l + 1] < heap[l] ? l + 1 : l ;
            if (heap[best] < heap[i]){
                swap(best , i ) ; // 交换位置
                i = best ;  // i 来到beat位置
                l = i * 2 + 1 ; // 再往下找左孩子 循环
            } else {
                break;
            }
        }
    }
    // 总方法：先根据左边界（二维数组的第一个维度[0]）排序，
    // 比较小根堆的0位置跟数组中的左边界，
    // 如果0位置数小于等于左边界，就弹出pop，就说明没有重复线段
    // 然后将该条线段的右边界存入小根堆。
    // 循环到头 看size的值是多少，就是多少个重复线段。
    // ans 随 size 变化而变化，最后得到最大值返回
    public static int compute(){
        // size清空
        size = 0 ;
        // 二维数组 line 以第一个维度（线段的左边界）进行升序排序。
        Arrays.sort(line, 0, n, (a, b) -> a[0] - b[0]);
        int ans = 0 ;
        for (int i = 0; i < n; i++) {
            // 判断是否有重合线段 右边界是否小于下一条线段的左边界
            while(size > 0 && heap[0] <= line[i][0]){
                pop();
            }
            // 将线段的右边界（第二个维度）加到小根堆
            add(line[i][1]);
            ans = Math.max(ans, size) ;
        }
        return ans ;
    }

    // 将数组和减半的最少操作次数
    // 用大根堆，选0位置上的数砍一半
    // 用Double类型实现
    public static int halveArray(int[] nums){
        // 利用大根堆的特质来进行砍半
        // b.compareTo(a) 就是谁大谁在前边，等同于 b-a(但是这个容易移除)
        PriorityQueue<Double> heap = new PriorityQueue<>((a , b) -> b.compareTo(a));
        double sum = 0 ; // 数组累加和
        // 计算数组累加和
        for (int num : nums) {
            heap.add((double) num);
            sum += num;
        }
        sum /= 2 ; // Sum的减半的目标
        int ans = 0 ; // 操作的次数
        // minus 是 具体砍半砍了多少，cur是指针，跟随大根堆最顶端的数值走
        for (double minus = 0,cur ; minus < sum ; ans++ , minus += cur){
            cur = heap.poll() / 2 ;  //大根堆最顶端的数砍半
            heap.add(cur); // 将砍完的数值放回去
        }
        return ans;
    }

    // 用整型实现，就是将所有书后边乘以2的20次方，
    public static int MAXN = 100001;
    public static long[] heapl = new long[MAXN];
    public static int sizel;
    public static int halveArray2(int[] arr){
        sizel = arr.length ;
        long sum = 0 ;
        // 每个数乘以2的20次方，然后加入大根堆,并且计算数组和
        for (int i = sizel -1 ; i >= 0 ; i--){
            heapl[i] = (long) arr[i] << 20 ;  // 乘2的20次方
            sum += heapl[i]; // 计算数组和
            heapify(i); // 变成大根堆，节点下沉的形式
        }
        sum /= 2 ; // 数组和 砍半的值
        int ans = 0 ;
        for (long minus = 0; minus < sum; ans++) {
            heapl[0] /= 2;  // 最大值砍一半
            minus += heapl[0]; // minus记录
            heapify(0);  // 再将砍过后的数放回去，变成大根堆
        }
        return ans ;
    }
    // 节点下沉方法
    public static void heapify(int i) {
        int l = i * 2 + 1;
        while (l < sizel){
            int best = l + 1 < sizel && heapl[l + 1] > heapl[l] ? l + 1 : l ;
            if (heapl[best] > heapl[i]){
                swapl(best, i);
                i = best;
                l = i * 2 + 1;
            } else {
                break;
            }
        }
    }
    public static void swapl(int i, int j) {
        long tmp = heapl[i];
        heapl[i] = heapl[j];
        heapl[j] = tmp;
    }
}
