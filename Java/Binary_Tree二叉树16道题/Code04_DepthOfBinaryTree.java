package Binary_Tree二叉树16道题;

// 最大深度，最小深度 （根节点到叶子节点最长距离跟最短距离）
// https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
// https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
public class Code04_DepthOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int maDepth(TreeNode root){
        // 最后加1，是因为还有root节点
        return root == null ? 0 : Math.max(maDepth(root.left),maDepth(root.right)) + 1 ;
    }

    // 最小深度
    public int minDepth(TreeNode root){
        if (root == null){
            return 0 ;
        }
        if (root.left == null && root.right == null) {
            return 1 ; // 当前只有一个root节点
        }else {
        // 初始化左右俩深度，
        int l = Integer.MAX_VALUE ;
        int r = Integer.MAX_VALUE ;
        if (root.left != null) {
            l = minDepth(root.left) ;
        }
        if (root.right != null) {
            r = minDepth(root.right) ;
        }
        return Math.min(l,r)+1;
        }
    }
}
