package MST_MinimumSpanningTree_最小生成树;

// Prim算法模版（洛谷）
// 动态空间实现
// https://www.luogu.com.cn/problem/P3366

import javax.swing.plaf.ButtonUI;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Code02_PrimDynamic {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer input = new StreamTokenizer(br) ;
        PrintWriter out  = new PrintWriter(new OutputStreamWriter(System.out)) ;

        while (input.nextToken() != StreamTokenizer.TT_EOF) {
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>() ; // 邻接表建图
            int n = (int) input.nval;
            for (int i = 0; i <= n ; i++) {
                graph.add(new ArrayList<>()) ;
            }
            input.nextToken() ;
            int m = (int) input.nval ;
            for (int i = 0 , u , v , w ; i < m ; i++) {
                input.nextToken();
                u = (int) input.nval;
                input.nextToken();
                v = (int) input.nval;
                input.nextToken();
                w = (int) input.nval;
                graph.get(u).add(new int[] { v, w }); // 因为无向图，所以两个点都要添加
                graph.get(v).add(new int[] { u, w });
            }
            // 根据权重创建小根堆 int[] [0]:到达的节点，[1]:到达的花费
            PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a , int[] b) -> a[1] - b[1]) ;
            // 加入小根堆
            for (int[] edge : graph.get((1))) {
                heap.add(edge) ;
            }

            boolean[] set = new boolean[n+1] ; // 哪些节点已经发现过了
            int nodeCnt = 1 ; // 边的数量
            set[1] = true ;
            int ans = 0 ; // 总权重
            while (!heap.isEmpty()) {
                int[] edge = heap.poll() ; // 小根堆弹出节点
                int next = edge[0] ; // 要去的点
                int cost = edge[1] ; // 边的权重
                // 如果没被发现过
                if (!set[next]) {
                    nodeCnt++; // 边数
                    set[next] = true ; // 把该节点在数组中设为true
                    ans += cost ; // 加权重
                    // 把下一个节点再加入小根堆
                    for (int[] e : graph.get(next)) {
                        heap.add(e) ;
                    }
                }
            }
            out.println(nodeCnt == n ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
