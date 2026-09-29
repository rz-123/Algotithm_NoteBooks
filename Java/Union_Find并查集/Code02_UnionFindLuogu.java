package Union_Find并查集;

// 并查集模版(洛谷)
// 本实现用递归函数实现路径压缩，而且省掉了小挂大的优化，一般情况下可以省略
// 测试链接 : https://www.luogu.com.cn/problem/P3367

import java.io.*;

public class Code02_UnionFindLuogu {

    public static int MAXN = 200001;

    public static int[] father = new int[MAXN];

    public static int n;

    public static void build(){
        for (int i = 0; i <= n; i++) {
            father[i] = i ;
        }
    }

    public static int find(int x) {
        if (x != father[x]){
            // 递归到代表节点，然后原路返回，沿路各个节点都挂到代表节点上
            father[x] = find(father[x]) ;
        }
        return father[x];
    }
    public static boolean isSameSet(int x , int y){
        return find(x) == find(y) ;
    }
    // 合并集合
    public static void union(int x , int y){
        father[find(x)] = find(y) ; // x的代表节点 挂到 y的代表节点上
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build();
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int z = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (z == 1) {
                    union(x, y);
                } else {
                    out.println(isSameSet(x, y) ? "Y" : "N");
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }

}
