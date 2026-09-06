package Binary_Tree二叉树16道题;

// 打家劫舍，偷了一个节点，他的孩子不能偷，求能偷的最大是多少
//https://leetcode.cn/problems/house-robber-iii/
public class Code07_HouseRobberIII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int rob (TreeNode root){
        f(root);
        return Math.max(yes,no) ; // 最终结果选最大收益

    }
    // 全局变量，
    // yes:遍历完子树 偷根节点的最大收益
    // no:遍历完子树 不偷根节点的最大收益
    public static int yes , no;

    public static void f(TreeNode root){
        if (root == null) {
            yes = 0 ;
            no = 0 ;
        } else {
            int y = root.val; // 根节点的值
            int n = 0 ; // 不偷根节点 设为0
            f(root.left); // 左子树
            y += no ; // y是偷根节点，那么必须加上子树不偷根节点的收益
            n += Math.max(yes , no ) ; // n 是不偷根节点，所以子树上哪个收益多加哪个
            f(root.right); // 右子树
            y += no ;
            n += Math.max(yes , no);
            yes = y ;
            no = n ;
        }
    }
}
