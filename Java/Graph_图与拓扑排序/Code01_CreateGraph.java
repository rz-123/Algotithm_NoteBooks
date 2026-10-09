package Graph_图与拓扑排序;

// 建图的三种方式：邻接矩阵、邻接表、链式前向星


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Code01_CreateGraph {
    public static int MAXN = 11; // 点的最大数量

    public static int MAXM = 21 ; // 边的最大数量

    public static int[][] graph1 = new int[MAXN][MAXN] ; // 邻接矩阵建图

    public static ArrayList<ArrayList<int[]>> graph2 = new ArrayList<>() ; // 邻接表建图

    // 链式前向星建图
    public static int [] head = new int[MAXN] ; // 存放当前的边号，下标为点号
    public static int [] next = new int[MAXM] ; // 存放下一条边号，下标为边号
    public static int [] to = new int[MAXM] ; // 存放下一个点号，下标为边号
    public static int cnt ; // 自动每次加1
    public static int[] weight = new int[MAXM] ; // 权重

    // 矩阵、表 清空
    public static void build(int n){
        // 邻接矩阵
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph1[i][j] = 0 ;
            }
        }
        // 邻接表
        graph2.clear(); // 表清空
        for (int i = 0; i <= n; i++) {
            graph2.add(new ArrayList<>()) ;
        }
        // 链式前向星
        cnt = 1 ;
        Arrays.fill(head , 1 , n + 1 , 0);
    }

    // 链式前向星 加边
    // u号点 前往 v号点  权重为w
    public static void addEdge(int u , int v , int w){
        next[cnt] = head[u]; // u号点的边赋值给next数组中的当前号边 下一条边号
        to[cnt]  = v ; // v号点赋值给to数组的当前边号  去往的点
        weight[cnt] = w ; // 赋值给相应的边的权重
        head[u] = cnt++ ; // 边号赋值给head数组的头号边，然后边号++
    }
    // 三种方式建图 有向带权图
    public static void directGraph(int[][] edges){
        // 邻近矩阵建图
        for (int[] edge : edges) {
            graph1[edge[0]][edge[1]] = edge[2] ;
        }
        // 邻接表建图
        for(int[] edge : edges) {
            graph2.get(edge[0]).add(new int[] { edge[1] , edge[2]}) ;
        }

        // 链式前向星建图
        for (int[] edge : edges) {
            addEdge(edge[0] , edge[1] , edge[2]);
        }
    }

    // 无向带权图(其实就是双向图)
    public static void undirectGraph(int[][] edges){
        // 邻接表
        for (int[] edge : edges) {
            graph1[edge[0]][edge[1]] = edge[2] ;
            graph1[edge[1]][edge[0]] = edge[2] ; //双向赋值
        }
        // 邻接表
        for (int[] edge : edges) {
            graph2.get(edge[0]).add(new int[] {edge[1] , edge[2]}) ;
            graph2.get(edge[1]).add(new int[] {edge[0] , edge[2]}) ;
        }
        // 链式前向星
        for (int[] edge : edges) {
            addEdge(edge[0] , edge[1] , edge[2]);
            addEdge(edge[1] , edge[0] , edge[2]);
        }
    }
    // 图的遍历
    public static void traversal( int n ){
        System.out.println("邻近矩阵的遍历：");
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                System.out.println(graph1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("邻接表遍历 :");
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "(邻居、加权):");
            for (int[] edge : graph2.get(i)) {
                System.out.println("(" + edge[0] + "," + edge[1] + ")");
            }
            System.out.println();
        }
        System.out.println("链式前向星：");
        for (int i = 1 ; i <= n ; i++) {
            System.out.println(i + "(邻居、边权) : ");
            for (int ei = head[i] ; ei > 0 ; ei = next[ei]) {
                System.out.println("(" + to[ei] + "," + weight[ei] + ") ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // 理解了带权图的建立过程，也就理解了不带权图
        // 点的编号为1...n
        // 例子1自己画一下图，有向带权图，然后打印结果
        int n1 = 4;
        int[][] edges1 = { { 1, 3, 6 }, { 4, 3, 4 }, { 2, 4, 2 }, { 1, 2, 7 }, { 2, 3, 5 }, { 3, 1, 1 } };
        build(n1);
        directGraph(edges1);
        traversal(n1);
        System.out.println("==============================");
        // 例子2自己画一下图，无向带权图，然后打印结果
        int n2 = 5;
        int[][] edges2 = { { 3, 5, 4 }, { 4, 1, 1 }, { 3, 4, 2 }, { 5, 2, 4 }, { 2, 3, 7 }, { 1, 5, 5 }, { 4, 2, 6 } };
        build(n2);
        undirectGraph(edges2);
        traversal(n2);
    }
}
