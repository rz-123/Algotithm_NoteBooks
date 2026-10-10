package Graph_图与拓扑排序;

// 最大食物链计数
// a -> b，代表a在食物链中被b捕食
// 给定一个有向无环图，返回
// 这个图中从最初级动物到最顶级捕食者的食物链有几条
// 测试链接 : https://www.luogu.com.cn/problem/P4017

import java.io.*;
import java.util.Arrays;

public class Code01_FoodLines {


    public static int MAXN = 5001;

    public static int MAXM = 500001;

    public static int MOD = 80112002;

    public static int[] head = new int[MAXN] ;
    public static int[] next = new int[MAXM] ;
    public static int[] to = new int[MAXM] ;
    public static int cnt ;

    public static int[] queue = new int[MAXN] ; // 队列
    public static int[] indegree = new int[MAXN] ; // 入度表
    public static int[] lines = new int[MAXN] ; // 节点向下推的的数组
    public static int n , m ;

    // 清空
    public static void build(int n) {
        cnt = 1;
        Arrays.fill(indegree, 0, n + 1, 0);
        Arrays.fill(lines, 0, n + 1, 0);
        Arrays.fill(head, 0, n + 1, 0);
    }

    // 创建边
    public static void addEdge(int u , int v)  {
        next[cnt] = head[u];
        to[cnt] = v ;
        head[u] = cnt++ ;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer input = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (input.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) input.nval ;
            input.nextToken() ;
            m = (int) input.nval ;
            build(n);

            for (int i = 0 , u , v; i < m ; i++ ) {
                input.nextToken() ;
                u = (int) input.nval ;
                input.nextToken() ;
                v = (int) input.nval ;
                addEdge(u,v);
                indegree[v]++ ;
            }
            out.println(ways());
        }
        out.flush();
        out.close();
        br.close();
    }


    // 进队列
    public static int ways(){
        int l = 0 , r = 0 ;
        for (int i = 1 ; i <= n ; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i ; // 入度为0的进队列
                lines[i] = 1 ; // 传递的信息
            }
        }

        int ans = 0 ;
        while (l<r) {
            int u = queue[l++];
            // 当没有边了，到头了,统计数量
            if (head[u] == 0) {
                ans = (ans + lines[u]) % MOD ;
            } else {
                // 向下推信息
                for (int ei = head[u] , v; ei > 0 ; ei = next[ei]) {
                    v = to[ei]; // 获取去往的点
                    lines[v] = (lines[v] + lines[u]) % MOD ; // 前一个点的小加上该点的信息 模除 MOD
                    if (--indegree[v] == 0) { // 如果入度为0 ，再次进队列
                        queue[r++] = v ;
                    }
                }
            }
        }
        return ans ;
    }
}
