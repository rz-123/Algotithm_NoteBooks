package Binary_Tree二叉树16道题;

// 测试链接 : https://leetcode.cn/problems/maximum-width-of-binary-tree/
// 二叉树的最大特殊宽度，null也计入长度
public class Code03_WidthOfBinaryTree1 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
    // 非空节点也算，最左非空节点跟最右非空节点间隔多少宽度
    public static int MAX = 3001 ;
    public static TreeNode[] nq  = new TreeNode[MAX] ; // 存放队列数组
    public static long[] lq = new long[MAX] ; // 存放编号的数组
    public static int l , r ;

    public static int widthOfBinaryTree(TreeNode root) {
        int ans= 1 ; // 返回的最大宽度
        l = r = 0 ;
        nq[r] = root ;
        lq[r++] = 1 ; // 编号初始化为1，后边根据编号来获取节点的最左孩子跟最右孩子
        while (l < r) {
            int size = r - l ;
            // 更新ans
            ans = Math.max(ans , (int) (lq[r-1]-lq[l] + 1)) ;
            for (int i = 0 ; i<size ; i ++){
                TreeNode treeNode = nq[l]; // 获取节点
                long l1 = lq[l++]; // 获取该节点的编号
                if (treeNode.left != null){
                    nq[r] = treeNode.left ; // 存入左孩子
                    lq[r++] = l1 * 2 ; // 得到该节点左孩子的编号存入lq
                }
                if (treeNode.right != null){
                    nq[r] = treeNode.right ;
                    lq[r++] = l1 * 2 + 1 ;
                }
            }
        }
        return ans ;
    }
}
