package Graph_图与拓扑排序;


// * 链式前向星建图 静态
//* https://www.nowcoder.com/practice/88f7e156ca7d43a1a535f619cd3f495c

import java.io.*;
import java.util.Arrays;

public class Code02_TopoSortStaticNowcoder {

    public static int MAXN = 200001 ;
    public static int MAXM = 200001 ;
    // 链式前向星的三个数组
    public static int[] head = new int[MAXN] ;
    public static int[] next = new int[MAXN] ;
    public static int[] to = new int[MAXN] ;
    public static int cnt ;

    // 队列
    public static int[] queue = new int[MAXN] ;
    public static int l , r ;

    // 拓扑排序，入度表
    public static int[] indegree = new int[MAXN];

    // 收集拓扑排序结果
    public static int[] ans = new int[MAXN] ;

    public static int n , m ;

    // 各个数组清空
    public static void build(int n){
        cnt = 1 ;
        Arrays.fill(head , 0 , n+1 , 0);
        Arrays.fill(indegree , 0 , n + 1 , 0);
    }

    // 建图
    public static void addEdge(int f , int t){
        next[cnt] = head[f]; // f点位置头边号赋值给相应边号的值是下一条边号
        to[cnt] = t ; // 去往t点
        head[f] = cnt++ ; // 设置f点位置的头边号位cnt，然后 cnt++
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer on = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (on.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) on.nval ;
            on.nextToken() ;
            m = (int) on.nval ;
            build(n); // 清空数组
            for (int i = 0 , from , to ; i < m; i++) {
                on.nextToken() ;
                from = (int) on.nval ;
                on.nextToken() ;
                to = (int) on.nval ;
                addEdge(from , to);
                indegree[to]++ ;
            }
            if (!topSort()){
                out.println(-1);
            } else {
                for (int i = 0; i < n - 1; i++) {
                    out.println(ans[i] + " ");
                }
                out.println(ans[n - 1]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static boolean topSort(){
        l = r = 0 ;
        for (int i = 1 ; i <= n ; i++) {
            if (indegree[i] == 0){
                queue[r++] = i ;
            }
        }
        int fill = 0 ;
        while (l < r) {
            int cur = queue[l++];
            ans[fill++] = cur ;
            for (int ei = head[cur] ; ei != 0 ; ei = next[ei]) {
                if (--indegree[to[ei]] == 0) {
                    queue[r++] = to[ei];
                }
            }
        }
        return fill == n ;
    }

}
