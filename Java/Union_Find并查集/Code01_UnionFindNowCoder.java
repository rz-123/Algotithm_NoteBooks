package Union_Find并查集;

// 并查集模版（路径压缩 + 小挂大）

import java.io.*;

public class Code01_UnionFindNowCoder {

    public static int Max = 1000001 ;
    public static int[] father = new int[Max] ; // 集合数组
    public static int[] size = new int[Max] ; // 集合大小数组
    public static int[] stack = new int[Max] ; // 进行路径压缩时路过节点存入栈内
    public static int n ; // 传进的数组大小

    // 初始化 两个数组
    public static void build(){
        for (int i = 0; i <= n ; i++) {
            father[i] = i ; // 全部自己指向自己
            size[i] = 1 ; // 大小全部设为1
        }
    }

    // 查看存入的数所在集合中的代表节点是什么
    public static int find (int i){
        int size = 0 ;
        // 如果当前节点不是代表节点，那么就一直往上找，并且将沿途的节点存入栈内
        while (i != father[i]){
            stack[size++] = i ;  // 存入将i栈内
            i = father[i]; // 将上一级节点赋值给i，直到代表节点，跳出while
        }
        // 路径压缩
        while (size > 0){
            father[stack[--size]] = i;
        }
        return i ; // 现在i为代表节点
    }

    // 判断 俩数 在不在同一个集合中
    public static boolean isSameSet(int x , int y){
        return find(x) == find(y) ; // 就是判断俩数是不是同属一个代表节点
    }

    // 俩集合合并
    public static void union(int x ,int y){
        // 找出俩数的集合中各自的代表节点
        int fx = find(x) ;
        int fy = find(y) ;
        // 如果代表节点不一样，才要合并；如果一样，就不用合并
        if (fx != fy) {
            // 小 挂 大
            if (size[fx] < size[fy]){
                father[fx] = fy ; // 小 挂 大 ： fx的上一个节点挂载y的代表节点上
            } else {
                father[fy] = fx ;
            }
            size[fx] += size[fy]; // 俩集合的数量相加
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval ;
            build();
            in.nextToken() ;
            int m = (int) in.nval ;
            for (int i = 0 ; i < m ; i++){
                in.nextToken() ;
                int op = (int) in.nval ;
                in.nextToken() ;
                int x = (int) in.nval ;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                } else {
                    union(x, y);
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
