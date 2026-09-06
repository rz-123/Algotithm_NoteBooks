package Binary_Tree二叉树16道题;

// 验证平衡二叉树
// 测试链接 : https://leetcode.cn/problems/balanced-binary-tree/
public class Code04_BalancedBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 设置全局变量，初始化为true，默认为平衡二叉树
    public static boolean balance ;

    public static boolean isBalance(TreeNode root){
        balance = true ; // 全局变量默认为平衡二叉树
        height(root) ;
        return balance ; // 返回是否为平衡二叉树

    }
    // 递归方法并且返回树的高度
    public static int height(TreeNode root){
        // 判断如果balance为false或者传进来的节点为0 ，那么就不是平衡二叉树
        if (!balance || root == null){
            return 0 ; // 一旦不是平衡二叉树 返回啥就无所谓了
        }
        int lh = height(root.left) ;
        int rh = height(root.right) ;
        // 如果左右子树的高度超过1，就设为false
        if (Math.abs(lh - rh) > 1){
            balance = false ;
        }
        // 左右子树中选最大高度加头结点
        return Math.max(lh , rh) + 1 ;
    }
}
