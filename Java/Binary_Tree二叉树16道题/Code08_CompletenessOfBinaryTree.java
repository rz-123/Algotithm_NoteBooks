package Binary_Tree二叉树16道题;

// 验证完全二叉树
// 完全二叉树：除了最后一层的其它层都是满树，最后一层即时不满也要靠左
// https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class Code08_CompletenessOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 设置队列数组
    public static TreeNode[] queue = new TreeNode[101];
    public static int l , r ;
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null){
            return true ;
        }
        l = r = 0 ;
        queue[r++] = root ;
        boolean leaf = false ; // 初始化为false，不是完全二叉树
        while (l < r) {
            root = queue[l++]; // 从左边弹出队列
            // 判断是否为完全二叉树
            if ((root.left == null && root.right != null)
                    || // 后边这句是已经进入叶子层阶段，但是发现还有左右孩子，那么就不是完全二叉树
                    (leaf && (root.left != null || root.right != null))){
                return false ;
            }
            if (root.left != null){
                queue[r++] = root.left ;
            }
            if (root.right != null){
                queue[r++] = root.right ;
            }
            if (root.left == null || root.right == null){
                leaf = true ; // 到叶子结点了
            }
        }
        return true ;
    }
}
