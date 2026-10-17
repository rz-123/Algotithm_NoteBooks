package Dijkstra_分层最短路;

// Dijkstra算法模版（Leetcode）
// 网络延迟时间
// 有 n 个网络节点，标记为 1 到 n
// 给你一个列表 times，表示信号经过 有向 边的传递时间
// times[i] = (ui, vi, wi)，表示从ui到vi传递信号的时间是wi
// 现在，从某个节点 s 发出一个信号
// 需要多久才能使所有节点都收到信号
// 如果不能使所有节点收到信号，返回 -1
// 测试链接 : https://leetcode.cn/problems/network-delay-time

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Code01_DijkstraLeetcode {

        public static int networkDelayTime(int[][] times , int n , int s){
             // 建图
             ArrayList<ArrayList<int[]>> graph = new ArrayList<>() ;
             for (int i = 0; i <= n; i++) {
                 graph.add(new ArrayList<>()) ;
             }
             for (int[] edge : times) {
                 graph.get(edge[0]).add(new int[] {edge[1] , edge[2]});
             }

             // 源点 到 各个节点的最短路 数组
             int[] distance = new int[n+1] ;
             Arrays.fill(distance , Integer.MAX_VALUE);

             distance[s] = 0 ; // 加入源点 距离源点为0

             boolean[] visited = new boolean[n + 1] ; // 是否进过小根堆

             // 小根堆 按 源点到当前点的距离进行排列 heap[当前节点][源点到当前节点的距离]
             PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a , int[] b) -> a[1]-b[1]) ;
             heap.add(new int[] {s,0}) ; // 加入源点

             // 如果小根堆不为空‘，就继续
             while (!heap.isEmpty()) {
                 int u = heap.poll()[0] ; // 当前点
                 if (visited[u]){
                     continue; // 如果该节点进过小根堆，visited[u]为true，不做任何操作
                 }
                 // 如果没进过，现在就进去
                 visited[u] = true ;
                 // 在图中获取该点的数组列表
                 for (int[] edge : graph.get(u)) {
                     int v = edge[0] ; // 去往的点
                     int w = edge[1] ; // 权重
                     // 如果 v没有加入过小根堆 并且 当前值的最短路加上权重 比 v的最短路小，那么就替换v的最短路。
                     if (!visited[v] && distance[v] > distance[u] + w){
                         distance[v] = distance[u] + w  ; // 当前值 加上 权重 赋值给 v的最短路
                         heap.add(new int[] { v , distance[u] + w}) ; // 将v点加入小根堆
                     }
                 }
             }
             int ans = Integer.MIN_VALUE ;
             for (int i = 1; i <= n; i++) {
                 // 如果最短路数组中有值为初始值，说明不能使所有节点收到信号
                 if (distance[i] == Integer.MAX_VALUE) {
                     return -1 ;
                 }
                 ans = Math.max(ans , distance[i]) ;
             }
             return ans;
         }

    public static int networkDelayTime1(int[][] times, int n, int s) {
        build(n);
        for (int[] edge : times) {
            addEdge(edge[0], edge[1], edge[2]);
        }
        addOrUpdateOrIgnore(s, 0);
        while (!isEmpty()) {
            int u = pop();
            for (int ei = head[u]; ei > 0; ei = next[ei]) {
                addOrUpdateOrIgnore(to[ei], distance[u] + weight[ei]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }

    public static int MAXN = 101;

    public static int MAXM = 6001;

    // 链式前向星
    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXM];

    public static int[] to = new int[MAXM];

    public static int[] weight = new int[MAXM];

    public static int cnt;

    // 反向索引堆
    public static int[] heap = new int[MAXN];

    // where[v] = -1，表示v这个节点，从来没有进入过堆
    // where[v] = -2，表示v这个节点，已经弹出过了
    // where[v] = i(>=0)，表示v这个节点，在堆上的i位置
    public static int[] where = new int[MAXN];

    public static int heapSize;

    public static int[] distance = new int[MAXN];

    public static void build(int n) {
        cnt = 1;
        heapSize = 0;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(where, 1, n + 1, -1);
        Arrays.fill(distance, 1, n + 1, Integer.MAX_VALUE);
    }

    // 链式前向星建图
    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    public static void addOrUpdateOrIgnore(int v, int c) {
        if (where[v] == -1) {
            heap[heapSize] = v;
            where[v] = heapSize++;
            distance[v] = c;
            heapInsert(where[v]);
        } else if (where[v] >= 0) {
            distance[v] = Math.min(distance[v], c);
            heapInsert(where[v]);
        }
    }

    public static void heapInsert(int i) {
        while (distance[heap[i]] < distance[heap[(i - 1) / 2]]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0);
        where[ans] = -2;
        return ans;
    }

    public static void heapify(int i) {
        int l = i * 2 + 1;
        while (l < heapSize) {
            int best = l + 1 < heapSize && distance[heap[l + 1]] < distance[heap[l]] ? l + 1 : l;
            best = distance[heap[best]] < distance[heap[i]] ? best : i;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            l = i * 2 + 1;
        }
    }

    public static boolean isEmpty() {
        return heapSize == 0;
    }

    public static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        where[heap[i]] = i;
        where[heap[j]] = j;
    }

}
