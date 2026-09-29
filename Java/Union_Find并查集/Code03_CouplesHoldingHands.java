package Union_Find并查集;

// 情侣牵手
// n对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手
// 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的ID
// 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)
// 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起
// 每次交换可选择任意两人，让他们站起来交换座位
// 测试链接 : https://leetcode.cn/problems/couples-holding-hands/

public class Code03_CouplesHoldingHands {

    public static int minSwapsCouples(int[] row){
        int n = row.length ;
        build(n/2); // 情侣有两个人，所以 集合数为n/2
        for (int i = 0; i < n; i+=2) {
            // 挨个合并集合，
            union(row[i] / 2 , row[i+1] / 2);
        }
        return n / 2 - sets ; // 最终返回操作的次数   减去 总集合数 减去 合并完的集合数
    }

    public static int MAXN = 31;

    public static int[] father = new int[MAXN];

    public static int sets; // 集合数

    public static void build( int m ){
        for (int i = 0; i < m; i++) {
            father[i] = i ;
        }
        sets = m ; // 初始化 集合数等于m (传入的数组长度 除以 2)
    }

    // 查看代表节点
    public static int find(int i ){
        if (i != father[i]) {
            father[i] = find(father[i]) ;
        }
        return father[i];
    }

    // 合并集合
    public static void union(int x ,int y){
        if (find(x) != find(y)){
            father[find(x)] = find(y) ; // 挂节点 x的代表节点 挂到 y 的代表节点上
            sets -- ; // 合并之后 集合减1
        }
    }
}
