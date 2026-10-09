package Graph_图与拓扑排序;

// 字典序最小的拓扑排序
// 要求返回所有正确的拓扑排序中 字典序最小 的结果
// 建图请使用链式前向星方式，因为比赛平台用其他建图方式会卡空间
// 测试链接 : https://www.luogu.com.cn/problem/U107394


import java.io.*;
import java.util.Arrays;

public class Code03_TopoSortStaticLuogu {

    public static int MAXN = 100001 ;
    public static int MAXM = 100001 ;

    public static int[] head = new int[MAXN] ;
    public static int[] next = new int[MAXM] ;
    public static int[] to = new int[MAXN] ;
    public static int cnt ;

    // 小根堆
    public static int[] heap = new int[MAXN] ;
    public static int heapSize ;

    public static int[] indegree = new int[MAXN] ;

    // 收集拓扑排序的结果
    public static int[] ans = new int[MAXN];
    public static int n , m ;

    // 清理脏数据
    public static void build(int n){
        cnt = 1 ;
        heapSize = 0 ;
        Arrays.fill(head , 0 , n+1 , 0);
        Arrays.fill(indegree , 0 , n+1 , 0);
    }

    public static void addEdge(int f , int t){
        next[cnt] = head[f] ;
        to[cnt] = t ;
        head[f] = cnt++ ;
    }

    // 向小根堆中加入数字
    public static void push(int num){
        int i = heapSize++ ;
        heap[i] = num ;
        while (heap[i] < heap[(i-1) / 2]) {
            swap(i , (i-1) / 2);
            i = (i-1) / 2 ;
        }
    }

    // 小根堆 弹出 最小值
    public static int pop(){
        int ans = heap[0] ;
        heap[0] = heap[--heapSize] ;
        int i = 0 ;
        int l = 1 ;
        while (l < heapSize) {
            int best = l + 1 < heapSize && heap[l+1] < heap[l] ? l+1 : l ;
            best = heap[best] < heap[i] ? best : i ;
            if (best == i) {
                break;
            }
            swap(best , i);
            i = best ;
            l = i * 2 + 1 ;
        }
        return ans ;
    }


    // 判断小根堆是否为空
    public static boolean isEmpty() {
        return heapSize == 0;
    }

    // 交换堆上两个位置的数字
    public static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build(n);
            for (int i = 0, from, to; i < m; i++) {
                in.nextToken();
                from = (int) in.nval;
                in.nextToken();
                to = (int) in.nval;
                addEdge(from, to);
                indegree[to]++;
            }
            topoSort();
            for (int i = 0; i < n - 1; i++) {
                out.print(ans[i] + " ");
            }
            out.println(ans[n - 1]);
        }
        out.flush();
        out.close();
        br.close();
        }
    public static void topoSort() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                push(i);
            }
        }
        int fill = 0;
        while (!isEmpty()) {
            int cur = pop();
            ans[fill++] = cur;
            // 用链式前向星的方式，遍历cur的相邻节点
            for (int ei = head[cur]; ei != 0; ei = next[ei]) {
                if (--indegree[to[ei]] == 0) {
                    push(to[ei]);
                }
            }
        }
    }
}
