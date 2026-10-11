package MST_MinimumSpanningTree_最小生成树;

// Kruskal算法模版（洛谷）
// 静态空间实现
// 测试链接 : https://www.luogu.com.cn/problem/P3366
// 时间复杂度O(m * log m) + O(n + m)

import java.io.*;
import java.util.Arrays;

public class Code01_Kruskal {
    public static int MAXN = 5001 ; // 点
    public static int MAXM = 200001 ; // 边

    public static int[] father = new int[MAXN] ; // 并查集
    public static int[] [] edges = new int[MAXM][3] ; // u -> v,权重为w
    public static int n , m ;

    // 并查集初始化
    public static void build(){
        for (int i = 1; i <= n ; i++) {
            father[i] = i ;
        }
    }

    // 寻找i所在集合的代表节点
    public static int find(int i ){
        if (i != father[i]) {
            father[i] = find(father[i]) ; // 递归
        }
        return father[i];
    }

    // 如果x/y是一个集合 返回false
    // 如果x/y不是集合，合并之后返回true
    public static boolean union(int x , int y){
        int fx = find(x) ;
        int fy = find(y) ;
        if (fx != fy) {
            father[fx] = fy ; // 合并集合
            return true ;
        } else {
            return false ;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer input = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (input.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) input.nval ; // 获取点
            input.nextToken() ;
            m = (int) input.nval ; // 获取边
            build(); // 初始化并查集
            // 获取 点1、点2、边的权重
            for (int i = 0; i < m; i++) {
                input.nextToken() ;
                edges[i][0] = (int) input.nval ; // 连接点1
                input.nextToken() ;
                edges[i][1] = (int) input.nval ; // 连接点2
                input.nextToken() ;
                edges[i][2] = (int) input.nval ; // 边的权重
            }
            // 根据边的权重从小到大排列
            Arrays.sort(edges , 0 , m , (int[] a  , int[] b) -> a[2] - b[2]);
            int ans = 0 ; // 权重和
            int edgeCnt = 0 ;  // 边的数量
            for (int[] edge : edges) {
                if (union(edge[0] , edge[1])){
                    edgeCnt++ ; // 边数增加
                    ans += edge[2] ; // 权重增加
                }
            }
            // 最后判断 边数是否等于节点数-1条边（必须得是n-1条边才能保证是全连通的最小生成树），
            // 如果相等就说明是最小生成树，返回收集到的总权重；
            // 如果不相等，就返回"orz"
            out.println(edgeCnt == n - 1 ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
