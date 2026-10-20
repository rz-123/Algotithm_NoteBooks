package Dijkstra_分层最短路.A星_Floyd_Bellman_Ford_SPFA;

// Bellman-Ford + SPFA优化模版（洛谷）
// 给定n个点的有向图，请求出图中是否存在从顶点1出发能到达的负环
// 负环的定义是：一条边权之和为负数的回路
// 测试链接 : https://www.luogu.com.cn/problem/P3385

import javax.swing.plaf.ButtonUI;
import java.io.*;
import java.util.Arrays;

public class Code04_SPFA {

    public static int MAXN = 2001 ;
    public static int MAXM = 6001 ;

    // 链式前向星
    public static int[] head = new int[MAXN] ;
    public static int[] next = new int[MAXM] ;
    public static int[] to = new int[MAXM] ;
    public static int[] weight = new int[MAXM] ;
    public static int cnt ;

    // SPFA
    public static int MAXQ = 4000001 ;
    public static int[] distance = new int[MAXN] ; // 源点到各个节点的距离表
    public static int[] updateCnt = new int[MAXN] ;  // 节点被松弛的次数
    public static int[] queue = new int[MAXQ] ; // 被松弛的点进入队列
    public static int l , r ; // 控制队列
    public static boolean[] enter = new boolean[MAXN] ; // 节点是否已经在队列中

    public static void build( int n ){
        cnt = 1 ;
        l = r = 0 ;
        Arrays.fill(head , 1 , n + 1 , 0);
        Arrays.fill(enter , 1 , n + 1 , false);
        Arrays.fill(distance , 1 , n + 1 , Integer.MAX_VALUE);
        Arrays.fill(updateCnt , 1 , n + 1 , 0);
    }

    // 建图
    public static void addEdge(int u , int v , int w){
        next[cnt] = head[u];
        to[cnt] = v ;
        weight[cnt] = w ;
        head[u] = cnt++ ;
    }

    // 判断是否出现负环，有负环 返回 true ， 无负环 返回 false
    public static boolean spfa( int n ){
        // 源点 到 源点距离 为0
        distance[1] = 0 ;
        updateCnt[1] ++ ; // 松弛次数+1
        queue[r++] = 1  ; // 进队列
        enter[1] = true ; // 是否在队列
        // 还有被松弛的点
        while ( l < r ){
            int u = queue[l++]; // 弹出一个数
            enter[u] = false ; // 是否在队列中，为false
            for (int ei = head[u] , v , w ; ei > 0 ; ei = next[ei]) {
                v = to[ei]; // 去往的点
                w = weight[ei]; // 权重

                // 如果 当前点的最短路 + 权重 小于 下一个点的最短路
                // 那么 就将 当前点的最短路 + 权重 赋值给 下一个点的对短路
                if (distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    // 如果下一个点 不在队列中
                    if (!enter[v]) {
                        if ( ++ updateCnt[v] > n - 1) {
                            return true ;
                        }
                        // 加入队列
                        queue[r++] = v ;
                        enter[v] = true ;
                    }
                }
            }

        }
        return false ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int cases = (int) in.nval;
        for (int i = 0, n, m; i < cases; i++) {
            in.nextToken();
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build(n);
            for (int j = 0, u, v, w; j < m; j++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;
                if (w >= 0) {
                    addEdge(u, v, w);
                    addEdge(v, u, w);
                } else {
                    addEdge(u, v, w);
                }
            }
            out.println(spfa(n) ? "YES" : "NO");
        }
        out.flush();
        out.close();
        br.close();
    }

}
